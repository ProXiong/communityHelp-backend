package com.quan.userCenterMd.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.model.domain.UserRole;
import com.quan.userCenterMd.mapper.UserRoleMapper;
import com.quan.userCenterMd.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
* @author quan
* @description 针对表【user_role_tb】的数据库操作Service实现
* @createDate 2024-10-21 22:42:46
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService {

}

// @Service
// public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo>
//         implements UserInfoService {
//
// }




