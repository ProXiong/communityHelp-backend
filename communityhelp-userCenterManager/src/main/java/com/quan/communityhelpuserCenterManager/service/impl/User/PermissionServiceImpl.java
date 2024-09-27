package com.quan.communityhelpuserCenterManager.service.impl.User;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpCommon.common.ErrorCode;
import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpModel.domain.Permission;
import com.quan.communityhelpuserCenterManager.mapper.User.PermissionMapper;
import com.quan.communityhelpuserCenterManager.service.impl.Correlations.RolePermissionServiceImpl;
import com.quan.communityhelpuserCenterManager.service.inter.Correlations.Role_PermissionService;
import com.quan.communityhelpuserCenterManager.service.inter.User.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @author quan
 * @description 针对表【permission】的数据库操作Service实现
 * @createDate 2024-08-22 09:17:26
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
        implements PermissionService {

    @Override
    public boolean addPermission(Permission permission) {
        return this.save(permission);
    }

    @Override
    public boolean deletePermission(Long permissionId) {
        return this.removeById(permissionId);
    }

    @Override
    public boolean updatePermission(Permission permission) {
        return this.updateById(permission);
    }

    /**
     * 根据角色id获取权限数组
     *
     * @param roleId 角色id
     * @return Permission[]
     */
    @Override
    public Permission[] getPermissionsByRoleId(Long roleId) {
        //根据角色id查询权限id
        Role_PermissionService role_permissionObj = new RolePermissionServiceImpl();
        Long[] permissions = role_permissionObj.getPermissionsByRoleId(roleId);
        //根据权限id数组查询权限名称数组
        // 1. 判断权限id数组是否为空
        if (permissions == null || permissions.length == 0) {
            return new Permission[0];
        }
        // 2. 根据权限id数组查询权限名称数组
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", permissions);
        Permission[] permissionArr = this.list(queryWrapper).toArray(new Permission[0]);
        return permissionArr;
    }

//        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("roleId", roleId);
//        Permission[] permissions = this.list(queryWrapper).toArray(new Permission[0]);
//        if (permissions.length == 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR, "该角色没有权限");
//        }
//
//        return permissions;
//    }
}




