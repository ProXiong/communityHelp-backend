package com.quan.userCenterMd.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quan.model.domain.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
* @author quan
* @description 针对表【permission_tb】的数据库操作Mapper
* @createDate 2024-10-21 22:34:04
* @Entity userCenterMd.domain.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}




