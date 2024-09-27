package com.quan.communityhelpuserCenterManager.service.impl.Correlations;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpModel.domain.RolePermission;
import com.quan.communityhelpuserCenterManager.mapper.Correlation.RolePermissionMapper;
import com.quan.communityhelpuserCenterManager.service.inter.Correlations.Role_PermissionService;
import com.quan.communityhelpuserCenterManager.service.inter.User.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quan
 * @description 针对表【role_permission】的数据库操作Service实现
 * @createDate 2024-08-22 09:17:19
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
        implements Role_PermissionService {

    @Autowired
    private PermissionService permissionService;


    @Override
    public boolean addRole_Permission(Long roleId, Long permissionId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        rolePermission.setPermissionId(permissionId);
        return this.save(rolePermission);
    }

    @Override
    public boolean deleteRole_Permission(Long roleId, Long permissionId) {
        return this.removeById(new QueryWrapper<RolePermission>()
                .eq("role_id", roleId)
                .eq("permission_id", permissionId)
        );
    }

    /**
     * 根据角色id获取权限id
     *
     * @param roleId 角色id
     * @return 角色的多个权限
     */
    @Override
    public Long[] getPermissionsByRoleId(Long roleId) {
        List<RolePermission> Permissions = this.list(new QueryWrapper<RolePermission>().eq("roleId", roleId));
        return Permissions.stream().map(RolePermission::getPermissionId).distinct().toArray(Long[]::new);
    }

    /**
     * 根据权限id获取角色id
     * @param permissionId 权限id
     * @return
     */
    @Override
    public Long[] getRoleIdsByPermissionId(Long permissionId) {
        List<RolePermission> roles = this.list(new QueryWrapper<RolePermission>().eq("permissionId", permissionId));
        return roles.stream().map(RolePermission::getRoleId).distinct().toArray(Long[]::new);
    }
}




