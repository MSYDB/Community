package com.community.admin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.blog.entity.BlogComment;
import com.community.admin.blog.entity.UserBlogSupport;
import com.community.admin.common.web.ResultJson;

/**
 * 用户点赞记录表(UserBlogSupport)表服务接口
 *
 * @author lx
 * @since 2021-10-16 15:58:07
 */
public interface UserBlogSupportService extends IService<UserBlogSupport> {

    ResultJson listAllThumbUpBlog(Integer pageNo, Integer pageSize);

    ResultJson blogThumbUp(UserBlogSupport support);

    ResultJson deleteThumbUp(Long blogId);

    ResultJson commentThumbUp(BlogComment comment);

    ResultJson delCommentThumbUp(BlogComment comment);
}