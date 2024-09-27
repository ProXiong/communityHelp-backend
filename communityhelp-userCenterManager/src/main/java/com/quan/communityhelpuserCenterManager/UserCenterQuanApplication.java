package com.quan.communityhelpuserCenterManager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@SpringBootApplication
@MapperScan(value = "com.quan.communityhelpuserCenterManager.mapper")
@ComponentScan(basePackages = {"com.quan.communityhelpCommon.config", "com.quan"})
public class UserCenterQuanApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterQuanApplication.class, args);
    }
}