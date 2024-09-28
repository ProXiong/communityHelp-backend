package com.quan.communityhelpuserCenterManager.service.impl.Post;

import com.quan.communityhelpModel.domain.Post;
import com.quan.communityhelpCommon.exception.BusinessException;
import com.quan.communityhelpuserCenterManager.service.impl.Post.PostServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PostServiceImplTest {

    private final PostServiceImpl postService = new PostServiceImpl();

    // 正常路径测试
    @Test
    void testValidPost_HappyPath() {
        Post post = new Post();
        post.setTitle("有效标题");
        post.setContent("有效内容");
        post.setTags("标签1,标签2");
        assertDoesNotThrow(() -> postService.validPost(post, true));
    }

    // 边界测试：标题为空
    @Test
    void testValidPost_TitleIsEmpty() {
        Post post = new Post();
        post.setTitle("");
        post.setContent("有效内容");
        post.setTags("标签1,标签2");
        BusinessException exception = assertThrows(BusinessException.class, () -> postService.validPost(post, true));
        assertEquals("参数错误", exception.getMessage());
    }

    // 边界测试：内容过长
    @Test
    void testValidPost_ContentTooLong() {
        Post post = new Post();
        post.setTitle("有效标题");
        post.setContent("过长内容".repeat(8192)); // 模拟过长内容
        post.setTags("标签1,标签2");
        BusinessException exception = assertThrows(BusinessException.class, () -> postService.validPost(post, true));
        assertEquals("内容过长", exception.getMessage());
    }

    // 边界测试：标签为空
    @Test
    void testValidPost_TagsIsEmpty() {
        Post post = new Post();
        post.setTitle("有效标题");
        post.setContent("有效内容");
        post.setTags("");
        assertDoesNotThrow(() -> postService.validPost(post, true));
    }

    // 边界测试：标题过长
    @Test
    void testValidPost_TitleTooLong() {
        Post post = new Post();
        post.setTitle("过长标题".repeat(10)); // 模拟过长标题
        post.setContent("有效内容");
        post.setTags("标签1,标签2");
        BusinessException exception = assertThrows(BusinessException.class, () -> postService.validPost(post, true));
        assertEquals("标题过长", exception.getMessage());
    }

    // 边界测试：post 为空
    @Test
    void testValidPost_NullPost() {
        BusinessException exception = assertThrows(BusinessException.class, () -> postService.validPost(null, true));
        assertEquals("参数错误", exception.getMessage());
    }
}
