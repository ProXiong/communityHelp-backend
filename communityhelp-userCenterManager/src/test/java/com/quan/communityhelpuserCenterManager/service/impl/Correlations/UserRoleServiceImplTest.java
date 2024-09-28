package com.quan.communityhelpuserCenterManager.service.impl.Correlations;

import com.quan.communityhelpModel.domain.UserRole;
import com.quan.communityhelpuserCenterManager.mapper.Correlation.UserRoleMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserRoleServiceImplTest {

    private final UserRoleMapper userRoleMapper = Mockito.mock(UserRoleMapper.class);
    private final UserRoleServiceImpl userRoleService = new UserRoleServiceImpl();

    // 测试正常添加用户角色
    @Test
    public void testAddUser_Role_HappyPath() {
        Long userId = 1L;
        Long roleId = 1L;

        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);

        when(userRoleMapper.insert(any(UserRole.class))).thenReturn(1); // 模拟插入返回1

        assertTrue(userRoleService.addUser_Role(userId, roleId));
    }

    // 测试添加用户角色时，传入的用户ID或角色ID为null
    @Test
    public void testAddUser_Role_NullUserId() {
        Long userId = null;
        Long roleId = 1L;

        assertThrows(IllegalArgumentException.class, () -> {
            userRoleService.addUser_Role(userId, roleId);
        });
    }

    @Test
    public void testAddUser_Role_NullRoleId() {
        Long userId = 1L;
        Long roleId = null;

        assertThrows(IllegalArgumentException.class, () -> {
            userRoleService.addUser_Role(userId, roleId);
        });
    }

    // 测试正常删除用户角色
    @Test
    public void testDeleteUser_Role_HappyPath() {
        Long userId = 1L;
        Long roleId = 1L;

        when(userRoleMapper.delete(any())).thenReturn(1); // 模拟删除返回1

        assertTrue(userRoleService.deleteUser_Role(userId, roleId));
    }

    // 测试删除用户角色时，角色不存在
    @Test
    public void testDeleteUser_Role_NotFound() {
        Long userId = 1L;
        Long roleId = 1L;

        when(userRoleMapper.delete(any())).thenReturn(0); // 模拟删除返回0

        assertFalse(userRoleService.deleteUser_Role(userId, roleId));
    }

    // 测试根据用户ID获取角色IDs
    @Test
    public void testGetRoleIdsByUserId_HappyPath() {
        Long userId = 1L;
        Long[] expectedRoleIds = {1L, 2L};

        UserRole userRole1 = new UserRole();
        userRole1.setRoleId(1L);
        UserRole userRole2 = new UserRole();
        userRole2.setRoleId(2L);

        when(userRoleMapper.selectList(any())).thenReturn(List.of(userRole1, userRole2));

        Long[] actualRoleIds = userRoleService.getRoleIdsByUserId(userId);
        assertArrayEquals(expectedRoleIds, actualRoleIds);
    }

    // 测试根据用户ID获取角色时，未找到角色
    @Test
    public void testGetRoleIdsByUserId_NoRoles() {
        Long userId = 1L;

        when(userRoleMapper.selectList(any())).thenReturn(List.of()); // 模拟没有角色

        Long[] actualRoleIds = userRoleService.getRoleIdsByUserId(userId);
        assertArrayEquals(new Long[]{}, actualRoleIds);
    }

    // 测试根据角色ID获取用户IDs
    @Test
    public void testGetUserIdsByRoleId_HappyPath() {
        Long roleId = 1L;
        Long[] expectedUserIds = {1L, 2L};

        UserRole userRole1 = new UserRole();
        userRole1.setUserId(1L);
        UserRole userRole2 = new UserRole();
        userRole2.setUserId(2L);

        when(userRoleMapper.selectList(any())).thenReturn(List.of(userRole1, userRole2));

        Long[] actualUserIds = userRoleService.getUserIdsByRoleId(roleId);
        assertArrayEquals(expectedUserIds, actualUserIds);
    }

    // 测试根据角色ID获取用户时，未找到用户
    @Test
    public void testGetUserIdsByRoleId_NoUsers() {
        Long roleId = 1L;

        when(userRoleMapper.selectList(any())).thenReturn(List.of()); // 模拟没有用户

        Long[] actualUserIds = userRoleService.getUserIdsByRoleId(roleId);
        assertArrayEquals(new Long[]{}, actualUserIds);
    }
}
