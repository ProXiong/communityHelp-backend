package com.quan.model.dto.vo;

import lombok.Data;

/**
 * @className: UserInfoVo
 * @author: quan
 * @date: 2024:10:22
 * @Task:
 */
@Data
public class UserInfoVo {
    /**
     * 用户id
     */
    private Integer userId;

    //todo 前端根据这个生成男女icon
    /**
     * 性别 0 男 1 女
     */
    private Integer sex;
    // todo 更具身份证号获取年龄
    /**
     * 账号||昵称
     */
    private String account;

    /**
     * 头像url
     */
    private String avatarUrl;



}
