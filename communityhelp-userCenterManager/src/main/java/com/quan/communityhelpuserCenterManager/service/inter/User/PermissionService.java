package com.quan.communityhelpuserCenterManager.service.inter.User;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.communityhelpModel.domain.Permission;

/**
 * @author quan
 * @description 针对表【permission】的数据库操作Service
 * @createDate 2024-08-22 09:17:26
 */
public interface PermissionService extends IService<Permission> {
    /**
     * @description: 添加权限
     * @param permission 权限对象
     * @return: boolean
     *
     */
    boolean addPermission(Permission permission);

    /**
     * @description: 删除权限
     * @param permissionId 权限id
     * @return: boolean
     */
     boolean deletePermission(Long permissionId);


    /**
     * @description: 更新权限
     * @param permission 权限对象
     * @return: boolean
     */
    boolean updatePermission(Permission permission);



    /**
     * 根据角色id查询权限
     * @param roleId 角色id
     * @return 权限列表
     */
    Permission[] getPermissionsByRoleId(Long roleId);

}
