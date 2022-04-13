package com.community.admin.blog.controller;

import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.blog.entity.BlogComment;
import com.community.admin.blog.service.BlogCommentService;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ？？？评论量改变？？？
 * <p>
 * Description 评论管理
 * Author  流星
 * Date 2021/10/15 0:35
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "blog")
public class CommentManageController {
    @Resource
    private BlogCommentService commentService;

    @SelfLog(module = "blog", type = GlobalConstant.INSERT, name = "发表评论")
    @AuthString("releaseComment")
    @RequestMapping("releaseComment")
    public ResultJson releaseComment(BlogComment comment) {
        return commentService.releaseComment(comment);
    }

    @SelfLog(module = "blog", type = GlobalConstant.DELETE, name = "删除评论")
    @AuthString("deleteComment")
    @RequestMapping("deleteComment")
    public ResultJson deleteComment(Long id) {
        if (commentService.removeById(id)) {
            return ResultJson.success("删除成功！", null);
        } else {
            return ResultJson.failure("删除失败！", null);
        }
    }

    @SelfLog(module = "blog", type = GlobalConstant.INSERT, name = "回复评论")
    @AuthString("replyComment")
    @RequestMapping("replyComment")
    public ResultJson replyComment(BlogComment comment) {
        return commentService.replyComment(comment);
    }

    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "展示评论")
    @AuthString("listAllComment")
    @RequestMapping("listAllComment")
    public ResultJson listAllComment(Integer pageNo, Integer pageSize, Long blogId) {
        return commentService.listAllComment(pageNo, pageSize, blogId);
    }
}
