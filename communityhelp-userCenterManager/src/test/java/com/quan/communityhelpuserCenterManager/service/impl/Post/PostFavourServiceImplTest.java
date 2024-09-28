package com.quan.communityhelpuserCenterManager.service.impl.Post;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quan.communityhelpCommon.common.ErrorCode;
import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpModel.domain.Post;
import com.quan.communityhelpModel.domain.User;
import com.quan.communityhelpuserCenterManager.service.inter.Post.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostFavourServiceImplTest {

    private PostFavourServiceImpl postFavourService;
    private PostService postService;
    private User loginUser;
    private Post post;

    @BeforeEach
    public void setUp() {
        postService = mock(PostService.class);
        postFavourService = new PostFavourServiceImpl(postService);

        // 设置测试用户
        loginUser = new User();
        loginUser.setUserId(1L);

        // 设置测试帖子
        post = new Post();
        post.setId(1L);
    }

    // 测试正常情况下的收藏
    @Test
    public void testDoPostFavour_HappyPath() {
        when(postService.getById(1L)).thenReturn(post);
        when(postFavourService.doPostFavourInner(anyLong(), anyLong())).thenReturn(1);

        int result = postFavourService.doPostFavour(1L, loginUser);
        assertEquals(1, result);
    }

    // 测试收藏不存在的帖子
    @Test
    public void testDoPostFavour_PostNotFound() {
        when(postService.getById(1L)).thenReturn(null);

        Exception exception = assertThrows(BusinessException.class, () -> {
            postFavourService.doPostFavour(1L, loginUser);
        });
        assertEquals(ErrorCode.NOT_FOUND_ERROR, exception.getMessage());
    }

    // 测试已收藏帖子的取消收藏
    @Test
    public void testDoPostFavour_AlreadyFavorited() {
        when(postService.getById(1L)).thenReturn(post);
        when(postFavourService.doPostFavourInner(anyLong(), anyLong())).thenReturn(-1);

        int result = postFavourService.doPostFavour(1L, loginUser);
        assertEquals(-1, result);
    }

    // 测试取消收藏失败的情况
    @Test
    public void testDoPostFavour_RemoveFavourFail() {
        when(postService.getById(1L)).thenReturn(post);
        when(postFavourService.doPostFavourInner(anyLong(), anyLong())).thenThrow(new BusinessException(ErrorCode.SYSTEM_ERROR));

        Exception exception = assertThrows(BusinessException.class, () -> {
            postFavourService.doPostFavour(1L, loginUser);
        });
        assertEquals(ErrorCode.SYSTEM_ERROR, exception.getMessage());
    }

    // 测试用户ID无效的分页收藏查询
    @Test
    public void testListFavourPostByPage_InvalidUserId() {
        Page<Post> page = postFavourService.listFavourPostByPage(new Page<>(), null, -1L);
        assertNotNull(page);
        assertTrue(page.getRecords().isEmpty());
    }

    // 测试用户ID有效的分页收藏查询
    @Test
    public void testListFavourPostByPage_ValidUserId() {
        when(postFavourService.getBaseMapper().listFavourPostByPage(any(), any(), anyLong()))
                .thenReturn(new Page<Post>());

        Page<Post> page = postFavourService.listFavourPostByPage(new Page<Post>(), null, 1L);
        assertNotNull(page);
    }
}
