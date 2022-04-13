package com.community.admin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.blog.entity.BlogColumn;
import com.community.admin.common.web.ResultJson;

/**
 * 博客栏目表(BlogColumn)表服务接口
 *
 * @author lx
 * @since 2021-10-20 14:34:27
 */
public interface BlogColumnService extends IService<BlogColumn> {

    ResultJson listAllColumn(Integer pageNo, Integer pageSize);

    ResultJson listColumnContent(Integer pageNo, Integer pageSize, Long columnId);

    ResultJson updateColumn(BlogColumn blogColumn);

    ResultJson insertColumn(BlogColumn blogColumn);
}