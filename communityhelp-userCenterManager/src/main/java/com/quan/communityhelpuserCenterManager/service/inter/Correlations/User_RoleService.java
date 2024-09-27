package com.quan.communityhelpuserCenterManager.service.inter.Correlations;

import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.communityhelpModel.domain.UserRole;

/**
 * @author quan
 * @description 针对表【user_role】的数据库操作Service
 * @createDate 2024-08-22 09:17:35
 */
public interface User_RoleService extends IService<UserRole> {
    boolean addUser_Role(Long userId, Long roleId);

    boolean deleteUser_Role(Long userId, Long roleId);

    /**
     * 根据用户id获取角色id
     */
    Long[] getRoleIdsByUserId(Long userId);

    /**
     * 根据角色id获取用户id
     */
    Long[] getUserIdsByRoleId(Long roleId);

}
