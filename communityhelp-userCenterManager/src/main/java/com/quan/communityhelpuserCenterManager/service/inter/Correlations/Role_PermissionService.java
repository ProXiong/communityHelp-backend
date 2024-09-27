package com.quan.communityhelpuserCenterManager.service.inter.Correlations;


import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.communityhelpModel.domain.RolePermission;

/**
 * @author quan
 * @description 针对表【role_permission】的数据库操作Service
 * @createDate 2024-08-22 09:17:19
 */
public interface Role_PermissionService extends IService<RolePermission> {
    boolean addRole_Permission(Long roleId, Long permissionId);

    boolean deleteRole_Permission(Long roleId, Long permissionId);

    /**
     * 根据角色id获取权限id
     *
     * @param roleId 角色id
     * @return 角色的多个权限
     */
    Long[] getPermissionsByRoleId(Long roleId);

    /**
     * 根据权限id获取角色id
     *
     * @param permissionId 权限id
     * @return 权限的多个角色
     */
    Long[] getRoleIdsByPermissionId(Long permissionId);

}
