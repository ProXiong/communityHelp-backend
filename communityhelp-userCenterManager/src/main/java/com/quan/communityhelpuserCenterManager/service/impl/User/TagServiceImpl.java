package com.quan.communityhelpuserCenterManager.service.impl.User;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.quan.communityhelpModel.domain.Tag;
import com.quan.communityhelpuserCenterManager.mapper.User.TagMapper;
import com.quan.communityhelpuserCenterManager.service.inter.User.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author quan
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2024-09-22 16:26:29
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService {

    @Override
    /**
     * 添加标签
     * @param tag 标签对象
     */
    public boolean addTag(Tag tag) {

        return this.save(tag);
    }

    @Override
    /**
     * 删除标签
     * @param tagId 标签id
     */
    public boolean deleteTag(Long tagId) {
        return this.removeById(tagId);
    }

    @Override
    /**
     * 更新标签
     * @param tag 标签对象
     */
    public boolean updateTag(Tag tag) {
        return updateById(tag);
    }

    @Override
    /**
     * 根据标签id获取标签
     */
    public Tag getTagById(Long tagId) {
        return this.getById(tagId);
    }

    @Override
    /**
     * 获取所有标签
     */
    public List<Tag> getAllTags() {
        return this.list();
    }
}




