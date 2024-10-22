package com.quan.model.dto.request.registration;

import com.quan.model.dto.request.RegistrationRequest;
import lombok.Data;

@Data
public class AccountRegistrationRequest extends RegistrationRequest {

    private String account;
    private String password;

}