package com.quan.communityhelpuserCenterManager.service.impl.Correlations;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpModel.domain.Role;
import com.quan.communityhelpModel.domain.UserRole;
import com.quan.communityhelpuserCenterManager.mapper.User.RoleMapper;
import com.quan.communityhelpuserCenterManager.mapper.Correlation.UserRoleMapper;
import com.quan.communityhelpuserCenterManager.service.inter.Correlations.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author quan
 * @description 针对表【user_role】的数据库操作Service实现
 * @createDate 2024-08-22 09:17:35
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
        implements UserRoleService {
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

    @Autowired
    RoleMapper roleMapper;

    /**
     * 根据用户获取角色信息
     *
     * @param userId
     * @return
     */
    @Override
    public Role[] getUserRoleInfos(Long userId) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("roleId", "SELECT roleId FROM User_Role WHERE userId = " + userId);
        List<Role> roles = roleMapper.selectList(queryWrapper);
        return roles.toArray(new Role[0]);
    }
}




