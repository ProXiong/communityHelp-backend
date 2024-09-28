package com.quan.communityhelpuserCenterManager.service.impl.User;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quan.communityhelpCommon.common.ErrorCode;
import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpModel.domain.User;
import com.quan.communityhelpuserCenterManager.mapper.User.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;  // 注入要测试的类

    @Mock
    UserMapper userMapper; // 模拟 UserMapper

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // 初始化Mockito注解
    }

    @Test
    void userRegister() {
        // 账户和密码参数为空，期望抛出异常
        Exception exception1 = assertThrows(BusinessException.class, () -> {
            userService.userRegister("", "");
        });
        assertEquals("用户名或密码不能为空", exception1.getMessage());

        // 账户为空，期望抛出异常
        Exception exception2 = assertThrows(BusinessException.class, () -> {
            userService.userRegister("", "123456");
        });
        assertEquals("用户名或密码不能为空", exception2.getMessage());

        // 密码为空，期望抛出异常
        Exception exception3 = assertThrows(BusinessException.class, () -> {
            userService.userRegister("test", "");
        });
        assertEquals("用户名或密码不能为空", exception3.getMessage());

        // 模拟账户长度大于4和密码都不为空，进行正常注册
        when(userMapper.insert(any(User.class))).thenReturn(1); // 模拟插入成功
        Long userId = userService.userRegister("test33", "123456");
        assertNotNull(userId);
        assertTrue(userId > 0);

        // 账户低于4位数，期望抛出异常
        Exception exception5 = assertThrows(BusinessException.class, () -> {
            userService.userRegister("132", "123456");
        });
        assertEquals("用户账号过短", exception5.getMessage());
    }

    @Test
    void userLogin() {
        // 参数为空，期望抛出异常
        Exception exception1 = assertThrows(BusinessException.class, () -> {
            userService.userLogin("", "", new MockHttpServletRequest());
        });
        assertEquals("用户名或密码不能为空", exception1.getMessage());

        // 账户不存在，期望抛出异常
        Exception exception2 = assertThrows(BusinessException.class, () -> {
            userService.userLogin("nonExistentUser", "123456", new MockHttpServletRequest());
        });
        assertEquals("账号不存在", exception2.getMessage());

        // 进行正常登录，模拟返回的用户对象
        User user = new User();
        user.setUserId(1L);
        user.setUserAccount("testUser");
        user.setUserPassword("hashedPassword"); // 假设这是已经加密的密码

        when(userMapper.selectOne(any(QueryWrapper.class))).thenReturn(user); // 模拟查找用户

        HttpServletRequest request = new MockHttpServletRequest();
        User loggedInUser = userService.userLogin("testUser", "password", request); // 模拟登录
        assertNotNull(loggedInUser);
        assertEquals("testUser", loggedInUser.getUserAccount());
        assertEquals(user.getUserId(), loggedInUser.getUserId());
    }

    @Test
    void userLogout() {
        HttpServletRequest request = new MockHttpServletRequest();
        request.getSession().setAttribute("USER_LOGIN_STATE", new User());

        int result = userService.userLogout(request);
        assertEquals(1, result);
        assertNull(request.getSession().getAttribute("USER_LOGIN_STATE"));
    }

    @Test
    void getLoginUser() {
        HttpServletRequest request = new MockHttpServletRequest();
        User user = new User();
        user.setUserId(1L);
        request.getSession().setAttribute("USER_LOGIN_STATE", user);

        when(userMapper.selectById(1L)).thenReturn(user); // 模拟根据ID查找用户

        User currentUser = userService.getLoginUser(request);
        assertNotNull(currentUser);
        assertEquals(user.getUserId(), currentUser.getUserId());
    }

    @Test
    void getLoginUser_NotLoggedIn() {
        HttpServletRequest request = new MockHttpServletRequest();

        BusinessException thrown = assertThrows(BusinessException.class, () -> {
            userService.getLoginUser(request);
        });
        assertEquals(ErrorCode.NOT_LOGIN_ERROR, thrown.getCode());
    }
}
