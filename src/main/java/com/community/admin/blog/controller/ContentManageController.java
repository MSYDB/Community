package com.community.admin.blog.controller;

import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.blog.entity.Blog;
import com.community.admin.blog.service.BlogService;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容管理
 * Author:流星
 * Date：2021/10/14 15:19
 **/
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "blog")
public class ContentManageController {
    @Resource
    private BlogService blogService;

    @SelfLog(module = "blog", type = GlobalConstant.INSERT, name = "发布文章")
    @AuthString("releaseArticle")
    @RequestMapping("releaseArticle")
    public ResultJson releaseArticle(Blog blog, @RequestParam(value = "fieldName") List<String> fieldName, String columnName) {
        return blogService.releaseArticle(blog, fieldName, columnName);
    }

    @SelfLog(module = "blog", type = GlobalConstant.DELETE, name = "删除博客")
    @AuthString("deleteBlog")
    @RequestMapping("deleteBlog")
    public ResultJson deleteBlog(Long id) {
        if (blogService.removeById(id)) {
            return ResultJson.success("删除成功！", null);
        } else {
            return ResultJson.failure("删除失败！", null);
        }
    }

    @SelfLog(module = "blog", type = GlobalConstant.UPDATE, name = "修改博客")
    @AuthString("updateBlog")
    @RequestMapping("updateBlog")
    public ResultJson updateBlog(Blog blog, @RequestParam(value = "fieldList") List<String> fieldList, String columnName) {
        return blogService.updateBlog(blog, fieldList, columnName);
    }

    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "查看数据")
    @AuthString("showData")
    @RequestMapping("showData")
    public ResultJson showData(Long id) {
        return blogService.showData(id);
    }

    @SelfLog(module = "blog", type = GlobalConstant.UPDATE, name = "博客置顶")
    @AuthString("isTop")
    @RequestMapping("isTop")
    public ResultJson isTop(Blog blog) {
        if (blogService.updateById(blog)) {
            return ResultJson.success("置顶成功！", null);
        } else {
            return ResultJson.failure("置顶失败！", null);
        }
    }

    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "查看博客")
    @AuthString("showBlog")
    @RequestMapping("showBlog")
    public ResultJson showBlog(Long id) {
        return blogService.showBlog(id);
    }

}
