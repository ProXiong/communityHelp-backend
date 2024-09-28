package com.quan.communityhelpuserCenterManager.service.impl.Correlations;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quan.communityhelpModel.domain.UserTag;
import com.quan.communityhelpuserCenterManager.mapper.Correlation.UserTagMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserTagServiceImplTest {

    @Mock
    private UserTagMapper userTagMapper; // 模拟 UserTagMapper

    @InjectMocks
    private UserTagServiceImpl userTagService; // 被测试的服务实现

    // 初始化 Mockito
    public UserTagServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    // 测试正常添加用户标签的情况
    @Test
    public void testAddUser_Tag_HappyPath() {
        Long userId = 1L;
        Long tagId = 1L;

        // 模拟 save 方法返回 true
        when(userTagMapper.insert(any(UserTag.class))).thenReturn(1);

        boolean result = userTagService.addUser_Tag(userId, tagId);
        Assertions.assertTrue(result); // 断言结果为 true
    }

    // 测试删除用户标签的正常情况
    @Test
    public void testDeleteUser_Tag_HappyPath() {
        Long userId = 1L;
        Long tagId = 1L;

        // 模拟删除操作返回 true
        when(userTagMapper.delete(any(QueryWrapper.class))).thenReturn(1);

        boolean result = userTagService.deleteUser_Tag(userId, tagId);
        Assertions.assertTrue(result); // 断言结果为 true
    }

    // 测试获取用户标签 ID 的情况
    @Test
    public void testGetTagIdsByUserId_HappyPath() {
        Long userId = 1L;
        UserTag userTag1 = new UserTag();
        userTag1.setTagId(1L);
        UserTag userTag2 = new UserTag();
        userTag2.setTagId(2L);
        List<UserTag> userTags = Arrays.asList(userTag1, userTag2);

        // 模拟 list 方法返回用户标签列表
        when(userTagMapper.selectList(any(QueryWrapper.class))).thenReturn(userTags);

        Long[] result = userTagService.getTagIdsByUserId(userId);
        Assertions.assertArrayEquals(new Long[]{1L, 2L}, result); // 断言返回的标签 ID 数组
    }

    // 测试用户没有标签的情况
    @Test
    public void testGetTagIdsByUserId_NoTags() {
        Long userId = 1L;

        // 模拟返回空列表
        when(userTagMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.emptyList());

        Long[] result = userTagService.getTagIdsByUserId(userId);
        Assertions.assertArrayEquals(new Long[]{}, result); // 断言返回一个空数组
    }

    // 测试获取用户 ID 的情况
    @Test
    public void testGetUserIdsByTagId_HappyPath() {
        Long tagId = 1L;
        UserTag userTag1 = new UserTag();
        userTag1.setUserId(1L);
        UserTag userTag2 = new UserTag();
        userTag2.setUserId(2L);
        List<UserTag> userTags = Arrays.asList(userTag1, userTag2);

        // 模拟 list 方法返回用户标签列表
        when(userTagMapper.selectList(any(QueryWrapper.class))).thenReturn(userTags);

        Long[] result = userTagService.getUserIdsByTagId(tagId);
        Assertions.assertArrayEquals(new Long[]{1L, 2L}, result); // 断言返回的用户 ID 数组
    }

    // 测试标签没有用户的情况
    @Test
    public void testGetUserIdsByTagId_NoUsers() {
        Long tagId = 1L;

        // 模拟返回空列表
        when(userTagMapper.selectList(any(QueryWrapper.class))).thenReturn(Collections.emptyList());

        Long[] result = userTagService.getUserIdsByTagId(tagId);
        Assertions.assertArrayEquals(new Long[]{}, result); // 断言返回一个空数组
    }
}
