package com.quan.communityhelpuserCenterManager.service.impl.Post;

import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.quan.communityhelpModel.domain.Post;
import com.quan.communityhelpModel.domain.PostThumb;
import com.quan.communityhelpModel.domain.User;
import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpuserCenterManager.mapper.Post.PostThumbMapper;
import com.quan.communityhelpuserCenterManager.service.impl.Post.PostThumbServiceImpl;
import com.quan.communityhelpuserCenterManager.service.inter.Post.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostThumbServiceImplTest {

    @InjectMocks
    private PostThumbServiceImpl postThumbService;

    @Mock
    private PostService postService;

    @Mock
    private PostThumbMapper postThumbMapper;

    // 测试类构造器
    public PostThumbServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * 测试正常点赞路径
     */
    @Test
    public void testDoPostThumb_HappyPath() {
        // 准备测试数据
        long postId = 1L;
        User loginUser = new User();
        loginUser.setUserId(1L);
        Post post = new Post();
        post.setId(postId);

        // 模拟行为
        when(postService.getById(postId)).thenReturn(post);
        when(postThumbMapper.selectOne(any())).thenReturn(null); // 用户未点赞
        when(postThumbMapper.insert(any())).thenReturn(1); // 点赞成功
        UpdateChainWrapper<Post> mockUpdateChainWrapper = mock(UpdateChainWrapper.class);
        when(postService.update()).thenReturn(mockUpdateChainWrapper);// 假设更新成功

        // 调用方法
        int result = postThumbService.doPostThumb(postId, loginUser);

        // 验证结果
        assertEquals(1, result); // 期待点赞增加1
    }

    /**
     * 测试用户重复点赞的情况
     */
    @Test
    public void testDoPostThumb_UserAlreadyLiked() {
        // 准备测试数据
        long postId = 1L;
        User loginUser = new User();
        loginUser.setUserId(1L);
        Post post = new Post();
        post.setId(postId);

        // 模拟行为
        when(postService.getById(postId)).thenReturn(post);
        when(postThumbMapper.selectOne(any())).thenReturn(new PostThumb()); // 返回已点赞记录
        when(postThumbMapper.delete(any())).thenReturn(1); // 取消点赞成功
        UpdateChainWrapper<Post> mockUpdateChainWrapper = mock(UpdateChainWrapper.class);
        when(postService.update()).thenReturn(mockUpdateChainWrapper);

        // 调用方法
        int result = postThumbService.doPostThumb(postId, loginUser);

        // 验证结果
        assertEquals(-1, result); // 期待点赞减少1
    }

    /**
     * 测试点赞时帖子不存在的情况
     */
    @Test
    public void testDoPostThumb_PostNotFound() {
        // 准备测试数据
        long postId = 1L;
        User loginUser = new User();
        loginUser.setUserId(1L);

        // 模拟行为
        when(postService.getById(postId)).thenReturn(null); // 帖子不存在

        // 调用方法并验证异常
        assertThrows(BusinessException.class, () -> postThumbService.doPostThumb(postId, loginUser));
    }

    /**
     * 测试点赞SQLException情况
     */
    @Test
    public void testDoPostThumb_SQLException() {
        // 准备测试数据
        long postId = 1L;
        User loginUser = new User();
        loginUser.setUserId(1L);
        Post post = new Post();
        post.setId(postId);

        // 模拟行为
        when(postService.getById(postId)).thenReturn(post);
        when(postThumbMapper.selectOne(any())).thenReturn(null); // 用户未点赞
        when(postThumbMapper.insert(any())).thenThrow(new RuntimeException("Database error")); // 模拟数据库异常

        // 调用方法并验证异常
        assertThrows(BusinessException.class, () -> postThumbService.doPostThumb(postId, loginUser));
    }

    /**
     * 测试用户为空的情况
     */
    @Test
    public void testDoPostThumb_UserNull() {
        long postId = 1L;

        // 调用方法并验证异常
        assertThrows(NullPointerException.class, () -> postThumbService.doPostThumb(postId, null));
    }
}
