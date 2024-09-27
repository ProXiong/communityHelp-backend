package com.quan.communityhelpuserCenterManager.mapper.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quan.communityhelpModel.domain.Role;

import java.util.List;

/**
 * @author quan
 * @description 针对表【role】的数据库操作Mapper
 * @createDate 2024-08-22 18:04:43
 * @Entity generator.domain.Role
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getRolesByIds(List<Long> roleIds);

}




