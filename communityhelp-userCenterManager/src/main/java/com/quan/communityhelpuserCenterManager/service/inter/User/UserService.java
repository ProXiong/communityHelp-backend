package com.quan.communityhelpuserCenterManager.service.inter.User;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.communityhelpModel.domain.User;
import com.quan.communityhelpModel.vo.UserVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author quan
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-08-22 08:35:07
 */
public interface UserService extends IService<User> {
// region 用户增删改查

    /**
     * 用户注册
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);


    /**
     * 转换User对象为UserVO对象
     */
    UserVO getUserVO(User user);


    //endregion


    // region 管理员相关

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    boolean isAdmin(User user);
    // endregion


    //region 用户信息相关

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获取当前登录用户（允许未登录）
     *
     * @param request
     * @return
     */
    User getLoginUserPermitNull(HttpServletRequest request);


}
