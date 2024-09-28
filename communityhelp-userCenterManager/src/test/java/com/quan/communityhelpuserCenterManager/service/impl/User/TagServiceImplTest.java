package com.quan.communityhelpuserCenterManager.service.impl.User;

import com.quan.communityhelpModel.domain.Tag;
import com.quan.communityhelpuserCenterManager.mapper.User.TagMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TagServiceImplTest {

    @InjectMocks
    private TagServiceImpl tagService;

    @Mock
    private TagMapper tagMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddTag_HappyPath() {
        Tag tag = new Tag();
        tag.setTagId(1L);
        tag.setTagName("测试标签");

        when(tagMapper.insert(tag)).thenReturn(1); // 模拟插入成功
        assertTrue(tagService.addTag(tag));
    }

    @Test
    void testDeleteTag_HappyPath() {
        when(tagMapper.deleteById(1L)).thenReturn(1); // 模拟删除成功
        assertTrue(tagService.deleteTag(1L));
    }

    @Test
    void testUpdateTag_HappyPath() {
        Tag tag = new Tag();
        tag.setTagId(1L);
        tag.setTagName("更新的标签");

        when(tagMapper.updateById(tag)).thenReturn(1); // 模拟更新成功
        assertTrue(tagService.updateTag(tag));
    }

    @Test
    void testGetTagById_HappyPath() {
        Long tagId = 1L;
        Tag tag = new Tag();
        tag.setTagId(tagId);
        tag.setTagName("测试标签");

        when(tagMapper.selectById(tagId)).thenReturn(tag); // 模拟根据ID获取标签
        Tag result = tagService.getTagById(tagId);
        assertEquals(tag, result);
    }

    @Test
    void testGetAllTags_HappyPath() {
        Tag tag1 = new Tag();
        tag1.setTagId(1L);
        tag1.setTagName("标签1");

        Tag tag2 = new Tag();
        tag2.setTagId(2L);
        tag2.setTagName("标签2");

        List<Tag> tags = Arrays.asList(tag1, tag2);
        when(tagMapper.selectList(null)).thenReturn(tags); // 模拟获取所有标签

        List<Tag> result = tagService.getAllTags();
        assertEquals(2, result.size());
        assertTrue(result.contains(tag1));
        assertTrue(result.contains(tag2));
    }

    @Test
    void testAddTag_NullTag() {
        assertFalse(tagService.addTag(null)); // 验证返回值应为 false
    }

    @Test
    void testDeleteTag_NullId() {
        assertFalse(tagService.deleteTag(null)); // 验证返回值应为 false
    }

    @Test
    void testUpdateTag_NullTag() {
        assertFalse(tagService.updateTag(null)); // 验证返回值应为 false
    }

    @Test
    void testGetTagById_NullId() {
        assertNull(tagService.getTagById(null)); // 验证返回值应为 null
    }

    @Test
    void testGetAllTags_EmptyList() {
        when(tagMapper.selectList(null)).thenReturn(Collections.emptyList()); // 模拟返回空列表

        List<Tag> result = tagService.getAllTags();
        assertTrue(result.isEmpty()); // 验证返回的标签列表应为空
    }
}
