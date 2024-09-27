package com.quan.communityhelpuserCenterManager.service.impl.Correlations;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpModel.domain.UserRole;
import com.quan.communityhelpuserCenterManager.mapper.Correlation.UserRoleMapper;
import com.quan.communityhelpuserCenterManager.service.inter.Correlations.User_RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quan
 * @description 针对表【user_role】的数据库操作Service实现
 * @createDate 2024-08-22 09:17:35
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
        implements User_RoleService {
    @Override
    public boolean addUser_Role(Long userId, Long roleId) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        return this.save(userRole);
    }

    @Override
    public boolean deleteUser_Role(Long userId, Long roleId) {
        return this.remove(new QueryWrapper<UserRole>()
                .eq("user_id", userId)
                .eq("role_id", roleId)
        );
    }

    /**
     * 根据用户id获取角色id
     *
     * @param userId
     * @return
     */
    @Override
    public Long[] getRoleIdsByUserId(Long userId) {
        List<UserRole> userRoles = this.list(new QueryWrapper<UserRole>().eq("userId", userId));
        return userRoles.stream().map(UserRole::getRoleId).toArray(Long[]::new);
    }

    @Override
    public Long[] getUserIdsByRoleId(Long roleId) {
        List<UserRole> userRoles = this.list(new QueryWrapper<UserRole>().eq("roleId", roleId));
        return userRoles.stream().map(UserRole::getUserId).toArray(Long[]::new);
    }
}


