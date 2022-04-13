package com.community.admin.blog.controller;


import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.blog.entity.BlogComment;
import com.community.admin.blog.entity.UserBlogSupport;
import com.community.admin.blog.service.UserBlogSupportService;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户点赞记录表(UserBlogSupport)表控制层
 *
 * @author lx
 * @since 2021-10-16 15:58:07
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "blog")
public class UserBlogSupportController {

    @Resource
    private UserBlogSupportService supportService;

    @SelfLog(module = "blog", type = GlobalConstant.INSERT, name = "点赞博客")
    @AuthString("blogThumbUp")
    @RequestMapping("blogThumbUp")
    public ResultJson blogThumbUp(UserBlogSupport support) {
        return supportService.blogThumbUp(support);
    }

    @SelfLog(module = "blog", type = GlobalConstant.DELETE, name = "取消点赞")
    @AuthString("deleteThumbUp")
    @RequestMapping("deleteThumbUp")
    public ResultJson deleteThumbUp(Long blogId) {
        return supportService.deleteThumbUp(blogId);
    }

    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "展示已点赞的博客")
    @AuthString("listAllThumbUpBlog")
    @RequestMapping("listAllThumbUpBlog")
    public ResultJson listAllThumbUpBlog(Integer pageNo, Integer pageSize) {
        return supportService.listAllThumbUpBlog(pageNo, pageSize);
    }

    @SelfLog(module = "blog", type = GlobalConstant.INSERT, name = "评论点赞")
    @AuthString("commentThumbUp")
    @RequestMapping("commentThumbUp")
    public ResultJson commentThumbUp(BlogComment comment) {
        return supportService.commentThumbUp(comment);
    }

    @SelfLog(module = "blog", type = GlobalConstant.DELETE, name = "取消评论点赞")
    @AuthString("delCommentThumbUp")
    @RequestMapping("delCommentThumbUp")
    public ResultJson delCommentThumbUp(BlogComment comment) {
        return supportService.delCommentThumbUp(comment);
    }

}