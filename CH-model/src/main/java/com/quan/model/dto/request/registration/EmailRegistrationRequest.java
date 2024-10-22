package com.quan.model.dto.request.registration;

import com.quan.model.dto.request.RegistrationRequest;
import lombok.Data;

/**
 * 邮箱注册请求类
 */
@Data
public class EmailRegistrationRequest extends RegistrationRequest {

	   private String email;
	   private String verificationCode;
	   // 省略getter和setter方法
	}