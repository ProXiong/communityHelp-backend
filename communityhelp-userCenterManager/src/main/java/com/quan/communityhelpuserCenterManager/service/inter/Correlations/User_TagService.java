package com.quan.communityhelpuserCenterManager.service.inter.Correlations;


import com.baomidou.mybatisplus.extension.service.IService;
import com.quan.communityhelpModel.domain.UserTag;

/**
 * @author quan
 * @description 针对表【user_tag(用户标签关联表)】的数据库操作Service
 * @createDate 2024-09-22 16:26:37
 */
public interface User_TagService extends IService<UserTag> {
    /**
     * 添加关联关系
     *
     * @param userId 用户id
     * @param tagId  标签id
     * @return 是否成功
     */
    boolean addUser_Tag(Long userId, Long tagId);

    /**
     * 删除关联关系
     *
     * @param userId 用户id
     * @param tagId  标签id
     * @return 是否成功
     */
    boolean deleteUser_Tag(Long userId, Long tagId);

    /**
     * 根据用户id获取标签id
     *
     * @param userId 用户id
     * @return 标签id数组
     */
    Long[] getTagIdsByUserId(Long userId);

    /**
     * 根据标签id获取用户id
     *
     * @param tagId 标签id
     * @return 用户id数组
     */
    Long[] getUserIdsByTagId(Long tagId);

}
