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
     * 根据角色id查询权限
     * @param roleId 角色id
     * @return 权限列表
     */
    Permission[] getPermissionsByRoleId(Long roleId);

}
