package com.quan.communityhelpuserCenterManager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.quan.communityhelpCommon.common.BaseResponse;
import com.quan.communityhelpCommon.common.ErrorCode;
import com.quan.communityhelpCommon.common.ResultUtils;
import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpModel.request.UserQueryRequest;
import com.quan.communityhelpModel.domain.User;
import com.quan.communityhelpModel.request.UserLoginRequest;
import com.quan.communityhelpModel.request.UserRegisterRequest;

import com.quan.communityhelpModel.vo.UserVO;
import com.quan.communityhelpuserCenterManager.service.inter.User.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.quan.communityhelpuserCenterManager.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户接口
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@RestController // restful风格
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<String> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        long l = userService.userRegister(userRegisterRequest.getUserAccount(), userRegisterRequest.getUserPassword());
        return ResultUtils.success("注册成功");
    }

    /**
     *
     * @param userLoginRequest
     * @param request
     * @return
     * @description: 用户登录
     */
    @PostMapping("/login")
    public BaseResponse<UserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        UserVO userVO = userService.getUserVO(user);
        return ResultUtils.success(userVO);
    }


    /**
     * 用户退出登录
     * 根据session中的用户信息，注销用户
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtils.success(result);
    }


    /**
     * 获取当前用户
     *
     * @param request
     * @return
     */
    @GetMapping("/current")
    public BaseResponse<UserVO> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {

            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        long userId = currentUser.getUserId();
        // TODO 校验用户是否合法
        User user = userService.getById(userId);
        UserVO userVO = userService.getUserVO(user);
        return ResultUtils.success(userVO);
    }

    /**
     * 根据用户名搜索用户
     *
     * @param userName
     * @param request
     * @return
     */
    @GetMapping("/search")
    public BaseResponse<List<UserVO>> searchUsers(String userName, HttpServletRequest request) {
        // if (!isAdmin(request)) {// xxx 校验是否为管理员方法不对
        //     throw new BusinessException(ErrorCode.NO_AUTH, "缺少管理员权限");
        // }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.like("userName", userName);
        }
        List<User> userList = userService.list(queryWrapper);
        List<UserVO> list = userList.stream().map(user -> userService.getUserVO(user)).collect(Collectors.toList
        ());
        return ResultUtils.success(list);// todo 安全用户信息
    }

    /**
     * 获取用户列表
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list")
    public BaseResponse<List<UserVO>> listUser(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
        List<User> userList = userService.list(queryWrapper);
        List<UserVO> userVOList = userList.stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(userVOList);
    }



    /**
     * 分页获取用户列表
     *
     * @param userQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<UserVO>> listUserByPage(UserQueryRequest userQueryRequest, HttpServletRequest request) {
        long current = 1;
        long size = 10;
        User userQuery = new User();
        if (userQueryRequest != null) {
            BeanUtils.copyProperties(userQueryRequest, userQuery);
            current = userQueryRequest.getCurrent();
            size = userQueryRequest.getPageSize();
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(userQuery);
        Page<User> userPage = userService.page(new Page<>(current, size), queryWrapper);
        Page<UserVO> userVOPage = new PageDTO<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
        List<UserVO> userVOList = userPage.getRecords().stream().map(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return userVO;
        }).collect(Collectors.toList());
        userVOPage.setRecords(userVOList);
        return ResultUtils.success(userVOPage);
    }

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    private BaseResponse<Boolean> isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        boolean isAdmin = userService.isAdmin(request);
        return ResultUtils.success(isAdmin);
    }
}
