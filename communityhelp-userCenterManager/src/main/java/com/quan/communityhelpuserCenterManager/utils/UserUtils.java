package com.quan.communityhelpuserCenterManager.utils;

import com.quan.communityhelpCommon.common.ErrorCode;
import com.quan.communityhelpCommon.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: UserUtils
 * @author: quan
 * @date: 2024:08:05
 * @Task:
 */
public class UserUtils {
    // public static UserDTO getSaftUser(User user) {
    //     //用户脱敏
    //     UserDTO userDTO = new UserDTO();
    //     userDTO.setUserId(user.getUserId());
    //     userDTO.setUserName(user.getUserName());
    //     userDTO.setUserAccount(user.getUserAccount());
    //     userDTO.setUserPassword(user.getUserPassword());
    //     userDTO.setPhone(user.getPhone());
    //     userDTO.setEmail(user.getEmail());
    //     userDTO.setGender(user.getGender());
    //     userDTO.setAvatarUrl(user.getAvatarUrl());
    //     return userDTO;
    // }


    public static void loginVerify(String userAccount, String userPassword) {
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户名或密码不能为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        // 账户不能包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不能包含特殊字符");
        }
    }
}
