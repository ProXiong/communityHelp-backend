package com.quan.communityhelpuserCenterManager.controller;

import com.quan.communityhelpCommon.common.BaseResponse;
import com.quan.communityhelpCommon.common.ResultUtils;
import com.quan.communityhelpModel.domain.Tag;
import com.quan.communityhelpuserCenterManager.service.inter.User.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/tag")
@Slf4j
public class TagController {
    @Autowired
    private TagService tagService;
    /**
     * 获取所有的标签
     */
    @GetMapping("/allTags")
    public BaseResponse<List<Tag>> getAllTags() {
        return ResultUtils.success(tagService.getAllTags());
    }



}
