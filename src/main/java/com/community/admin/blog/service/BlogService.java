package com.community.admin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.blog.entity.Blog;
import com.community.admin.common.web.ResultJson;

import java.util.List;

/**
 * 博客表(Blog)表服务接口
 *
 * @author lx
 * @since 2021-10-14 15:30:59
 */
public interface BlogService extends IService<Blog> {

    ResultJson showData(Long id);

    ResultJson updateBlog(Blog blog, List<String> fieldList, String columnName);

    ResultJson showBlog(Long id);

    ResultJson releaseArticle(Blog blog, List<String> fieldName, String columnName);
}