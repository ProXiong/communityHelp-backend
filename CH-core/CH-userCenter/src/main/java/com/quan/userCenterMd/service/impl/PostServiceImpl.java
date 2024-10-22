package com.quan.userCenterMd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.model.domain.Post;
import com.quan.userCenterMd.mapper.PostMapper;
import com.quan.userCenterMd.service.PostService;
import org.springframework.stereotype.Service;

/**
* @author quan
* @description 针对表【post_tb】的数据库操作Service实现
* @createDate 2024-10-21 22:42:37
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService {

}




