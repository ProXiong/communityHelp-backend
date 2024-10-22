package com.quan.userCenterMd.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.model.domain.UserInfo;
import com.quan.userCenterMd.mapper.UserInfoMapper;
import com.quan.userCenterMd.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
* @author quan
* @description 针对表【user_info_tb】的数据库操作Service实现
* @createDate 2024-10-21 22:42:44
*/
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
    implements UserInfoService {

}




