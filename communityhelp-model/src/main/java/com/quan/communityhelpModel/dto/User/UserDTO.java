package com.quan.communityhelpModel.dto.User;

import lombok.Data;

/**
 * @className: UserDTO
 * @author: quan
 * @date: 2024:08:01
 * @Task:
 */
@Data
public class UserDTO {

    private Integer userId;

    //nickName
    private String userName;

    private String userAccount;

    private String userPassword;

    private String avatarUrl;


    private String gender;


    private String phone;
   // @Email(message = "邮箱格式不正确")
    private String email;

    // public  UserToUserDTO (){
    //
    // }

}
