package com.quan.communityhelpuserCenterManager.service.inter.User;


import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.communityhelpModel.domain.Tag;

import java.util.List;

/**
 * @author quan
 * @description 针对表【tag(标签)】的数据库操作Service
 * @createDate 2024-09-22 16:26:29
 */
public interface TagService extends IService<Tag> {
    // 添加标签
    boolean addTag(Tag tag);

    // 删除标签
    boolean deleteTag(Long tagId);

    // 更新标签
    boolean updateTag(Tag tag);

    // 根据 ID 查询标签
    Tag getTagById(Long tagId);

    // 获取所有标签
    List<Tag> getAllTags();

    // // 根据条件查询标签（可扩展）
    // List<Tag> getTagsByCondition(/* 条件参数 */);
}
