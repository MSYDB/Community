package com.community.admin.blog.controller;


import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.blog.entity.UserBlogCollect;
import com.community.admin.blog.service.UserBlogCollectService;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户收藏记录表(UserBlogCollect)表控制层
 *
 * @author lx
 * @since 2021-10-16 15:57:30
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "blog")
public class UserBlogCollectController {

    @Resource
    private UserBlogCollectService collectService;

    @SelfLog(module = "blog", type = GlobalConstant.INSERT, name = "收藏博客")
    @AuthString("blogCollect")
    @RequestMapping("blogCollect")
    public ResultJson blogCollect(UserBlogCollect collect) {
        return collectService.blogCollect(collect);
    }

    @SelfLog(module = "blog", type = GlobalConstant.DELETE, name = "取消收藏")
    @AuthString("deleteCollect")
    @RequestMapping("deleteCollect")
    public ResultJson deleteCollect(Long blogId) {
        return collectService.deleteCollect(blogId);
    }

    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "展示已收藏的博客")
    @AuthString("listAllCollectBlog")
    @RequestMapping("listAllCollectBlog")
    public ResultJson listAllCollectBlog(Integer pageNo, Integer pageSize) {
        return collectService.listAllCollectBlog(pageNo, pageSize);
    }
}