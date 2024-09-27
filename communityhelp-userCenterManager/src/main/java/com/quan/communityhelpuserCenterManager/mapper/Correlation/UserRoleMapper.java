package com.quan.communityhelpuserCenterManager.mapper.Correlation;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quan.communityhelpModel.domain.UserRole;

import java.util.List;

/**
* @author quan
* @description 针对表【user_role】的数据库操作Mapper
* @createDate 2024-08-22 18:04:53
* @Entity generator.domain.UserRole
*/
public interface UserRoleMapper extends BaseMapper<UserRole> {
    List<Long> getRoleIdsByUserId(Long userId);

}




