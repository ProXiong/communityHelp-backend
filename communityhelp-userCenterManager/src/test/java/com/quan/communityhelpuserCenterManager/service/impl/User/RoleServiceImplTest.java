package com.quan.communityhelpuserCenterManager.service.impl.User;

import com.quan.communityhelpModel.domain.Role;
import com.quan.communityhelpuserCenterManager.mapper.User.RoleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleMapper roleMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddRole_HappyPath() {
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("Admin");

        when(roleMapper.insert(role)).thenReturn(1); // 模拟插入成功
        assertTrue(roleService.addRole(role));
    }

    @Test
    void testAddRole_NullRole() {
        assertThrows(IllegalArgumentException.class, () -> {
            roleService.addRole(null);
        });
    }

    @Test
    void testDeleteRole_HappyPath() {
        when(roleMapper.deleteById(1L)).thenReturn(1); // 模拟删除成功
        assertTrue(roleService.deleteRole(1L));
    }

    @Test
    void testDeleteRole_NonExistentId() {
        when(roleMapper.deleteById(-1L)).thenReturn(0); // 模拟ID不存在
        assertFalse(roleService.deleteRole(-1L));
    }

    @Test
    void testUpdateRole_HappyPath() {
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("Admin Updated");

        when(roleMapper.updateById(role)).thenReturn(1); // 模拟更新成功
        assertTrue(roleService.updateRole(role));
    }

    @Test
    void testUpdateRole_NullRole() {
        assertThrows(IllegalArgumentException.class, () -> {
            roleService.updateRole(null);
        });
    }

    @Test
    void testGetRoleById_HappyPath() {
        Role role = new Role();
        role.setRoleId(1L);
        when(roleMapper.selectById(1L)).thenReturn(role); // 模拟根据ID获取角色
        assertNotNull(roleService.getRoleById(1L));
    }

    @Test
    void testGetRoleById_NonExistentId() {
        when(roleMapper.selectById(-1L)).thenReturn(null); // 模拟ID不存在
        assertNull(roleService.getRoleById(-1L));
    }

    @Test
    void testGetAllRoles_HappyPath() {
        when(roleMapper.selectList(null)).thenReturn(Collections.emptyList()); // 模拟返回空列表
        Role[] roles = roleService.getAllRoles();
        assertNotNull(roles);
        assertEquals(0, roles.length);
    }
}
