package com.quan.communityhelpuserCenterManager.service.impl.User;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpCommon.common.ErrorCode;
import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpModel.domain.Role;
import com.quan.communityhelpModel.domain.User;
import com.quan.communityhelpModel.vo.UserVO;
import com.quan.communityhelpuserCenterManager.mapper.Correlation.UserRoleMapper;
import com.quan.communityhelpuserCenterManager.mapper.User.RoleMapper;
import com.quan.communityhelpuserCenterManager.mapper.User.UserMapper;
import com.quan.communityhelpuserCenterManager.service.impl.Correlations.UserRoleServiceImpl;
import com.quan.communityhelpuserCenterManager.service.inter.User.UserService;
import com.quan.communityhelpuserCenterManager.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

import static com.quan.communityhelpuserCenterManager.constant.UserConstant.USER_LOGIN_STATE;
import static com.quan.communityhelpuserCenterManager.constant.roleConstant.Defaulter;

/**
 * @author quan
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-08-22 08:35:07
 */

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "quan";
    @Resource
    private UserRoleServiceImpl userRoleServiceImpl;


    /**
     * @method: userRegister
     * @param: userAccount, userPassword
     * @return: long    用户id
     * @description:默认用户注册
     */
    // 管理员注册?

    /**
     * @method: userRegister
     * @param: userAccount, userPassword
     * @return: long
     * @description: 注册
     */
    @Override
    public long userRegister(String userAccount, String userPassword) {
        // 1. 校验
        UserUtils.loginVerify(userAccount, userPassword);
        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
        }
        // 2. 加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 3. 插入数据
        User user = new User();
        // user.setUserAccount(userAccount);
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }
        // 保存用户角色关系
        userRoleServiceImpl.addUser_Role(user.getUserId(), Defaulter);
        return user.getUserId();
    }

    /**
     * @method: userLogin
     * @param: userAccount, userPassword, request
     * @return: com.quan.usercenter_quan.pojo.domain.User
     * @description: 登录
     */
    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 校验
        UserUtils.loginVerify(userAccount, userPassword);
        // 2. 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号不存在");
        }
        // 3.用户脱敏//fixme
        // 4. 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return user;
    }

    /**
     * @method: userLogout
     * @param: request
     * @return: int
     * @description: 注销 //fixme
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户未登录");
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        return userVO;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(USER_LOGIN_STATE);
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户未登录");
        }
        List<Role> userRoles = getUserRoles(user.getUserId());
        return userRoles.contains("admin");
        // todo 一个用户对应多个角色，这里只判断是否有admin角色，不合理
        // 权限还没用?
    }

    @Override
    public boolean isAdmin(User user) {
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Long[] roleIds = userRoleServiceImpl.getRoleIdsByUserId(user.getUserId());
        for (Long role : roleIds) {
            if (role.equals(1l)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getUserId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        //todo 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getUserId();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    /**
     * 根据用户id获取用户角色信息
     *
     * @param userId 用户id
     * @return
     */
    public List<Role> getUserRoles(java.lang.Long userId) {
        // 1. 获取用户对应的 roleIds
        List<java.lang.Long> roleIds = userRoleMapper.getRoleIdsByUserId(userId);

        // 2. 如果没有角色，返回空列表
        if (roleIds.isEmpty()) {
            return Collections.emptyList();
        }

        // 3. 根据 roleIds 获取角色信息
        return roleMapper.getRolesByIds(roleIds);
    }

    /**
     * 获取当前登录用户（允许未登录）
     *
     * @param request
     * @return
     */
    @Override
    public User getLoginUserPermitNull(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getUserId() == null) {
            return null;
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getUserId();
        return this.getById(userId);
    }
}




