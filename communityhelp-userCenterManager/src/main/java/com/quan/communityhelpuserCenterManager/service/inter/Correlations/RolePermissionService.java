package com.quan.communityhelpuserCenterManager.service.inter.Correlations;


import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.communityhelpModel.domain.Permission;
import com.quan.communityhelpModel.domain.RolePermission;

/**
* @author quan
* @description 针对表【role_permission】的数据库操作Service
* @createDate 2024-08-22 09:17:19
*/
public interface RolePermissionService extends IService<RolePermission> {
    boolean addRole_Permission(Long roleId, Long permissionId);
    boolean deleteRole_Permission(Long roleId, Long permissionId);
    /**
     * 查询一个角色的多个权限
     * @param roleId 角色id
     * @return 角色的多个权限
     */
    Permission[] getRolePermissionsByRoleId(Long roleId);

}
