package com.quan.userCenterMd.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quan.model.domain.Role;
import org.apache.ibatis.annotations.Mapper;

/**
* @author quan
* @description 针对表【role_tb】的数据库操作Mapper
* @createDate 2024-10-21 22:33:45
* @Entity userCenterMd.domain.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




