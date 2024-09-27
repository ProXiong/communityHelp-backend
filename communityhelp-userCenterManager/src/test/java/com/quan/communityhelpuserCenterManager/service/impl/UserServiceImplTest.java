package com.quan.communityhelpuserCenterManager.service.impl;

import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpuserCenterManager.service.impl.User.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService ;
    @Test
    void userRegister() {
        // 账户和密码参数为空，期望抛出异常
        Exception exception1 = assertThrows(BusinessException.class, () -> {
            userService.userRegister("", "");
        });
        assertEquals("请求参数错误", exception1.getMessage());
        assertEquals("用户名或密码不能为空", ((BusinessException) exception1).getMessage()); // 根据您自定义的异常消息进行验证

        // 账户为空，期望抛出异常
        Exception exception2 = assertThrows(BusinessException.class, () -> {
            userService.userRegister("", "123456");
        });
        assertEquals("请求参数错误", exception1.getMessage());
        assertEquals("用户名或密码不能为空", ((BusinessException) exception1).getMessage());

        // 密码为空，期望抛出异常
        Exception exception3 = assertThrows(BusinessException.class, () -> {
            userService.userRegister("test", "");
        });
        assertEquals("请求参数错误", exception1.getMessage());
        assertEquals("用户名或密码不能为空", ((BusinessException) exception1).getMessage());

        // 账户长度大于4和密码都不为空，进行正常注册，期望返回用户ID
        try {
            Long  userId =userService.userRegister("test33", "123456");
            if(userId>0){
                System.out.println("注册成功，用户ID为："+userId);
            }
        } catch (Exception e) {
            System.out.println("注册失败");
        }
        System.out.println("/n");

        // 账户低于4位数，期望抛出异常
        Exception exception5 = assertThrows(BusinessException.class, () -> {
            userService.userRegister("132", "123456");
        });
        assertEquals("请求参数错误", exception5.getMessage());
        assertEquals("用户账号过短", ((BusinessException) exception5).getMessage());
    }

}