package com.quan.communityhelpuserCenterManager.service.inter.User;


import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.communityhelpModel.domain.Role;

/**
 * @author quan
 * @description 针对表【role】的数据库操作Service
 * @createDate 2024-08-22 09:17:23
 */
public interface RoleService extends IService<Role> {
    /**
     * @param role 角色对象
     * @return boolean
     * @description 新增角色
     */
    boolean addRole(Role role);

    /**
     * @param roleId 角色id
     * @return boolean
     * @description 删除角色
     */
    boolean deleteRole(Long roleId);

    /**
     * @param role 角色对象
     * @return boolean
     * @description 更新角色
     */
    boolean updateRole(Role role);

    /**
     * @param roleId 角色id
     * @return Role
     * @description 根据角色id查询角色信息
     */
    Role getRoleById(Long roleId);

    /**
     * @return Role[]
     * @description 获取所有角色信息
     */
    Role[] getAllRoles();


}
