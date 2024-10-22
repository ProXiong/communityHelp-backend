package com.quan.userCenterMd.controller;

import com.quan.Common.common.BaseResponse;
import com.quan.Common.common.ErrorCode;
import com.quan.Common.common.ResultUtils;
import com.quan.model.domain.UserInfo;
import com.quan.model.dto.request.login.LoginRegistrationRequest;
import com.quan.model.dto.vo.UserInfoVo;
import com.quan.userCenterMd.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @className: userController
 * @author: quan
 * @date: 2024:10:21
 * @Task:
 */
@Tag(name = "用户中心模块")
@RestController
@RequestMapping("user/userInfo")
public class userController {
    private final UserInfoService userInfoService;

    public userController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    // 管理员登录
    @Operation(summary = "用户登录")
    @PostMapping("/userLogin/{username}/{password}")
    public BaseResponse<Boolean> userLogin(@Valid @RequestBody LoginRegistrationRequest userLoginRequest) {
        UserInfo userInfo = new UserInfo()
                .setAccount(userLoginRequest.getAccount())
                .setPassword(userLoginRequest.getPassword());
        return ResultUtils.success(userInfoService.save(userInfo));
    }

    // 管理员注册
    @Operation(summary = "注册")
    @PostMapping("/userRegister/{username}/{password}")
    public BaseResponse<Boolean> userRegister(
            @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "用户名只能包含字母、数字和下划线")
            @PathVariable("username") String username,
            @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8," +
                    "}$",
                    message = "密码必须包含至少一个数字、一个小写字母、一个大写字母和一个特殊字符，且长度至少为8位")
            @PathVariable("password") String password) {
        return ResultUtils.success(userInfoService.save(new UserInfo()
                .setAccount(username)
                .setPassword(password)));
    }

    // todo 可以使用策略模式,组合模式,策略模式等进行优化
    // todo 现在一般是手机号注册登录,和邮箱注册登录一定程度上保证了用户的唯一性,所以就不写账号密码登录了


    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public UserInfo getUserInfoById(@RequestParam Integer userId) {
        UserInfo userInfo = userInfoService.getById(userId);
        return userInfo;
    }

    // 逻辑删除
    @Operation(summary = "逻辑删除用户信息")
    @DeleteMapping("/deleteUserInfo/{userId}")
    public BaseResponse<Boolean> deleteUserInfo(@PathVariable("userId") Integer userId) {
        return ResultUtils.success(userInfoService.removeById(userId));
    }

    // 修改用户信息
    @Operation(summary = "修改用户信息")
    @PutMapping("/updateUserInfo")
    public BaseResponse<Boolean> updateUserInfo(@NotNull @RequestBody UserInfoVo userInfoVo) {
        // 查看是否有userId,没有的话就返回错误
        if (userInfoVo.getUserId() == null) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR, "用户id不能为空");
        }
        return ResultUtils.success(userInfoService.updateById(new UserInfo()
                .setId(userInfoVo.getUserId())
                .setSex(userInfoVo.getSex())
                .setAccount(userInfoVo.getAccount())
                .setAvatarUrl(userInfoVo.getAvatarUrl())));
    }
}