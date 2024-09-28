package com.quan.communityhelpuserCenterManager.service.impl.User;

import com.quan.communityhelpModel.domain.Permission;
import com.quan.communityhelpuserCenterManager.mapper.User.PermissionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PermissionServiceImplTest {

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @Mock
    private PermissionMapper permissionMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddPermission_HappyPath() {
        Permission permission = new Permission();
        permission.setPermissionName("Test Permission");

        when(permissionMapper.insert(permission)).thenReturn(1); // 模拟插入成功
        assertTrue(permissionService.addPermission(permission));
    }

    @Test
    public void testDeletePermission_HappyPath() {
        when(permissionMapper.deleteById(1L)).thenReturn(1); // 模拟删除成功
        assertTrue(permissionService.deletePermission(1L));
    }

    @Test
    public void testUpdatePermission_HappyPath() {
        Permission permission = new Permission();
        permission.setPermissionId(1L);
        permission.setPermissionName("Updated Permission");

        when(permissionMapper.updateById(permission)).thenReturn(1); // 模拟更新成功
        assertTrue(permissionService.updatePermission(permission));
    }

    @Test
    public void testGetPermissionsByRoleId_HappyPath() {
        Long roleId = 1L;
        Permission[] expectedPermissions = { new Permission(), new Permission() };

        when(permissionMapper.selectList(any())).thenReturn(Arrays.asList(expectedPermissions)); // 模拟获取权限列表

        Permission[] result = permissionService.getPermissionsByRoleId(roleId);
        assertArrayEquals(expectedPermissions, result);
    }
}
