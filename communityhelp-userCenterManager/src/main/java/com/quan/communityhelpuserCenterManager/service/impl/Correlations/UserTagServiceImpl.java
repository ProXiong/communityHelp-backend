package com.quan.communityhelpuserCenterManager.service.impl.Correlations;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpModel.domain.UserTag;
import com.quan.communityhelpuserCenterManager.mapper.Correlation.UserTagMapper;
import com.quan.communityhelpuserCenterManager.service.inter.Correlations.UserTagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author quan
* @description 针对表【user_tag(用户标签关联表)】的数据库操作Service实现
* @createDate 2024-09-22 16:26:37
*/
@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag>
    implements UserTagService {

    @Override
    public boolean addUser_Tag(Long userId, Long tagId) {
        UserTag userTag = new UserTag();
        userTag.setUserId(userId);
        userTag.setTagId(tagId);
        return this.save(userTag);  // 使用 MyBatis-Plus 提供的 save 方法
    }

    @Override
    public boolean deleteUser_Tag(Long userId, Long tagId) {
        // 构造删除条件
        return this.remove(new QueryWrapper<UserTag>()
                .eq("userId", userId)
                .eq("tagId", tagId));
    }

    @Override
    public Long[] getTagIdsByUserId(Long userId) {
        List<UserTag> userTags = this.list(new QueryWrapper<UserTag>().eq("userId", userId));
        // 提取标签 ID 并转换为数组
        return userTags.stream().map(UserTag::getTagId).toArray(Long[]::new);
    }

    @Override
    public Long[] getUserIdsByTagId(Long tagId) {
        List<UserTag> userTags = this.list(new QueryWrapper<UserTag>().eq("tagId", tagId));
        // 提取用户 ID 并转换为数组
        return userTags.stream().map(UserTag::getUserId).toArray(Long[]::new);
    }
}




