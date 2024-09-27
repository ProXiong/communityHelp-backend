package com.quan.communityhelpuserCenterManager.service.impl.User;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpModel.domain.Role;
import com.quan.communityhelpuserCenterManager.mapper.User.RoleMapper;
import com.quan.communityhelpuserCenterManager.service.inter.User.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author quan
 * @description 针对表【role】的数据库操作Service实现
 * @createDate 2024-08-22 09:17:23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {
    @Override
    public boolean addRole(Role role) {
        return this.save(role);
    }

    @Override
    public boolean deleteRole(Long roleId) {
        return this.removeById(roleId);
    }

    @Override
    public boolean updateRole(Role role) {
        return this.updateById(role);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return null;
    }

    @Override
    public Role[] getAllRoles() {
        return new Role[0];
    }
}




