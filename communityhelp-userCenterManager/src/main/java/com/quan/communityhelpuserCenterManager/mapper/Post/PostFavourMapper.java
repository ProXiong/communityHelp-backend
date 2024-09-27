package com.quan.communityhelpuserCenterManager.mapper.Post;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quan.communityhelpModel.domain.Post;
import com.quan.communityhelpModel.domain.PostFavour;
import org.apache.ibatis.annotations.Param;

/**
 * @author quan
 * @description 针对表【post_favour(帖子收藏)】的数据库操作Mapper
 * @createDate 2024-09-20 10:45:47
 * @Entity generator.domain.PostFavour
 */
public interface PostFavourMapper extends BaseMapper<PostFavour> {
    /**
     * 分页查询收藏帖子列表
     *
     * @param page
     * @param queryWrapper
     * @param favourUserId
     * @return
     */
    Page<Post> listFavourPostByPage(IPage<Post> page, @Param(Constants.WRAPPER) Wrapper<Post> queryWrapper,
                                    long favourUserId);
}




