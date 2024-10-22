package com.quan.userCenterMd.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quan.model.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author quan
* @description 针对表【user_info_tb】的数据库操作Mapper
* @createDate 2024-10-21 22:33:28
* @Entity userCenterMd.domain.UserInfo
*/
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}




