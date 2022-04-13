package com.community.admin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.blog.entity.BlogField;
import com.community.admin.common.web.ResultJson;

/**
 * 博客领域表(BlogField)表服务接口
 *
 * @author lx
 * @since 2021-10-15 21:42:39
 */
public interface BlogFieldService extends IService<BlogField> {

    ResultJson insertField(String field);

    ResultJson displayField(Integer pageNo, Integer pageSize, String fieldName);
}