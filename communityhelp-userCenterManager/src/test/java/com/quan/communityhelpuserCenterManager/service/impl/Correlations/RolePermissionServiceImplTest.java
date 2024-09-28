package com.quan.communityhelpuserCenterManager.service.impl.Correlations;

import com.quan.communityhelpModel.domain.RolePermission;
import com.quan.communityhelpuserCenterManager.mapper.Correlation.RolePermissionMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RolePermissionServiceImplTest {

    @InjectMocks
    private RolePermissionServiceImpl rolePermissionService;

    @Mock
    private RolePermissionMapper rolePermissionMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // 测试成功添加角色权限
    @Test
    void testAddRolePermission() {
        Long roleId = 1L;
        Long permissionId = 2L;

        when(rolePermissionMapper.insert(any(RolePermission.class))).thenReturn(1);

        Assertions.assertTrue(rolePermissionService.addRole_Permission(roleId, permissionId));

        verify(rolePermissionMapper, times(1)).insert(any(RolePermission.class));
    }

    // 测试删除存在的角色权限
    @Test
    void testDeleteRolePermissionExists() {
        Long roleId = 1L;
        Long permissionId = 2L;

        when(rolePermissionMapper.delete(any())).thenReturn(1);

        Assertions.assertTrue(rolePermissionService.deleteRole_Permission(roleId, permissionId));

        verify(rolePermissionMapper, times(1)).delete(any());
    }

    // 测试删除不存在的角色权限
    @Test
    void testDeleteRolePermissionNotExists() {
        Long roleId = 1L;
        Long permissionId = 2L;

        when(rolePermissionMapper.delete(any())).thenReturn(0);

        Assertions.assertFalse(rolePermissionService.deleteRole_Permission(roleId, permissionId));

        verify(rolePermissionMapper, times(1)).delete(any());
    }

    // 测试根据角色id获取权限id
    @Test
    void testGetPermissionsByRoleId() {
        Long roleId = 1L;
        RolePermission rp1 = new RolePermission();
        rp1.setPermissionId(1L);
        RolePermission rp2 = new RolePermission();
        rp2.setPermissionId(2L);

        when(rolePermissionMapper.selectList(any())).thenReturn(Arrays.asList(rp1, rp2));

        Long[] permissions = rolePermissionService.getPermissionsByRoleId(roleId);
        Assertions.assertArrayEquals(new Long[]{1L, 2L}, permissions);
    }

    // 测试根据角色id获取没有权限的情况
    @Test
    void testGetPermissionsByRoleIdNoPermissions() {
        Long roleId = 1L;

        when(rolePermissionMapper.selectList(any())).thenReturn(Collections.emptyList());

        Long[] permissions = rolePermissionService.getPermissionsByRoleId(roleId);
        Assertions.assertArrayEquals(new Long[]{}, permissions);
    }

    // 测试根据权限id获取角色id
    @Test
    void testGetRoleIdsByPermissionId() {
        Long permissionId = 1L;
        RolePermission rp1 = new RolePermission();
        rp1.setRoleId(1L);
        RolePermission rp2 = new RolePermission();
        rp2.setRoleId(2L);

        when(rolePermissionMapper.selectList(any())).thenReturn(Arrays.asList(rp1, rp2));

        Long[] roleIds = rolePermissionService.getRoleIdsByPermissionId(permissionId);
        Assertions.assertArrayEquals(new Long[]{1L, 2L}, roleIds);
    }

    // 测试根据权限id获取没有角色的情况
    @Test
    void testGetRoleIdsByPermissionIdNoRoles() {
        Long permissionId = 1L;

        when(rolePermissionMapper.selectList(any())).thenReturn(Collections.emptyList());

        Long[] roleIds = rolePermissionService.getRoleIdsByPermissionId(permissionId);
        Assertions.assertArrayEquals(new Long[]{}, roleIds);
    }
}
