package com.quan.communityhelpuserCenterManager.service.impl.User;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpCommon.common.ErrorCode;
import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpModel.domain.Permission;
import com.quan.communityhelpuserCenterManager.mapper.User.PermissionMapper;
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

    /**
     * 根据角色id获取权限数组
     * @param roleId 角色id
     * @return Permission[]
     */
    @Override
    public Permission[] getPermissionsByRoleId(Long roleId) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);
        Permission[] permissions = this.list(queryWrapper).toArray(new Permission[0]);
        if ( permissions.length == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"该角色没有权限");
        }

        return permissions;
    }
}




