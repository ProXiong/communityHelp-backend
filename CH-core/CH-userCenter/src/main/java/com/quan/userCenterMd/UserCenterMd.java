package com.quan.userCenterMd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author quan
 */
@SpringBootApplication
// @MapperScan(value = "com.quan.userCenterMd.mapper")
// @ComponentScan(basePackages = {"com.quan.Common.config", "com.quan"})
public class UserCenterMd {
    public static void main(String[] args) {
        SpringApplication.run(UserCenterMd.class, args);
    }
}