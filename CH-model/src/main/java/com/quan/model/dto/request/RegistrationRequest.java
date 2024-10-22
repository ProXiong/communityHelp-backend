package com.quan.model.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 通用的注册请求类
 */
public abstract class RegistrationRequest implements Serializable {

	private static final long serialVersionUID = 6618310845402502884L;
	/**
	 * 用户账号
	 */
	@NotBlank(message = "用户账号不能为空")
	@Length(min = 5, max = 20, message = "用户账号长度必须在5-20之间")
	@Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "用户账号只能包含字母、数字和下划线")
	private String account;

	/**
	 * 用户密码
	 */
	@NotBlank(message = "用户密码不能为空")
	@Length(min = 5, max = 20, message = "用户密码长度必须在5-20之间")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$", message = "密码必须包含至少一个数字、一个小写字母、一个大写字母和一个特殊字符，且长度至少为8位")
	 String password;
}


