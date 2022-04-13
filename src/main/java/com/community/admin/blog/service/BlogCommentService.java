package com.community.admin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.blog.entity.BlogComment;
import com.community.admin.common.web.ResultJson;

/**
 * 博客评论表(BlogComment)表服务接口
 *
 * @author lx
 * @since 2021-10-15 00:28:18
 */
public interface BlogCommentService extends IService<BlogComment> {

    ResultJson replyComment(BlogComment comment);

    ResultJson releaseComment(BlogComment comment);

    ResultJson listAllComment(Integer pageNo, Integer pageSize, Long blogId);
}