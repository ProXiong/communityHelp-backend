package com.quan.userCenterMd.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quan.model.domain.Post;
import org.apache.ibatis.annotations.Mapper;

/**
* @author quan
* @description 针对表【post_tb】的数据库操作Mapper
* @createDate 2024-10-21 22:33:52
* @Entity userCenterMd.domain.Post
*/
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}




