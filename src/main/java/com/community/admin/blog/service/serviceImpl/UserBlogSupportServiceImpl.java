package com.community.admin.blog.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.blog.entity.Blog;
import com.community.admin.blog.entity.BlogComment;
import com.community.admin.blog.entity.UserBlogSupport;
import com.community.admin.blog.mapper.BlogCommentMapper;
import com.community.admin.blog.mapper.BlogMapper;
import com.community.admin.blog.mapper.UserBlogSupportMapper;
import com.community.admin.blog.service.UserBlogSupportService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户点赞记录表(UserBlogSupport)表服务实现类
 *
 * @author lx
 * @since 2021-10-16 15:58:07
 */
@Transactional
@Service("userBlogSupportService")
public class UserBlogSupportServiceImpl extends ServiceImpl<UserBlogSupportMapper, UserBlogSupport> implements UserBlogSupportService {
    @Resource
    private UserBlogSupportMapper supportMapper;
    @Resource
    private HttpServletRequest request;
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BlogCommentMapper commentMapper;

    @Override
    public ResultJson listAllThumbUpBlog(Integer pageNo, Integer pageSize) {
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        List<Blog> list = blogMapper.supportBlogList(Long.valueOf(uid));
        if (list.size() != 0) {
            return ResultJson.success("查询成功！", list);
        } else {
            return ResultJson.failure("查询失败！", null);
        }
    }

    @Override
    public ResultJson blogThumbUp(UserBlogSupport support) {
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        support.setUserId(Long.valueOf(uid));
        if (supportMapper.insert(support) == 1) {
            supportManage(support.getBlogId());
            return ResultJson.success("点赞成功！", null);
        } else {
            return ResultJson.failure("点赞失败！", null);
        }
    }

    private void supportManage(Long blogId) {
        Blog blog = new Blog();
        Blog blog1 = blogMapper.selectById(blogId);
        blog.setId(blog1.getId());
        blog.setApproveCount(blog1.getApproveCount() + 1);
        blog.setPageViewCount(blog1.getPageViewCount());
        blog.setCommentCount(blog1.getCommentCount());
        blog.setCollectCount(blog1.getCollectCount());
        blog.setStatus(blog1.getStatus());
        blog.setIsTop(blog1.getIsTop());
        blogMapper.updateById(blog);
    }

    private void delSupportManage(Long blogId) {
        Blog blog = new Blog();
        Blog blog1 = blogMapper.selectById(blogId);
        blog.setId(blog1.getId());
        blog.setPageViewCount(blog1.getPageViewCount());
        blog.setCommentCount(blog1.getCommentCount());
        blog.setCollectCount(blog1.getCollectCount());
        blog.setStatus(blog1.getStatus());
        blog.setApproveCount(blog1.getApproveCount() - 1);
        blog.setIsTop(blog1.getIsTop());
        blogMapper.updateById(blog);
    }

    @Override
    public ResultJson deleteThumbUp(Long blogId) {
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        QueryWrapper<UserBlogSupport> wrapper = new QueryWrapper<>();
        wrapper.eq("blog_id", blogId).eq("user_id", Long.valueOf(uid));
        if (supportMapper.delete(wrapper) == 1) {
            delSupportManage(blogId);
            return ResultJson.success("取消成功！", null);
        } else {
            return ResultJson.failure("取消失败！", null);
        }
    }

    @Override
    public ResultJson commentThumbUp(BlogComment comment) {
        BlogComment comment1 = commentMapper.selectById(comment.getId());
        comment.setApproveCount(comment1.getApproveCount() + 1);
        thumbUpManage(comment, comment1);
        return ResultJson.success("点赞成功！", null);
    }


    private void thumbUpManage(BlogComment comment, BlogComment comment1) {

        comment.setBlogId(comment1.getBlogId());
        comment.setCommentUserId(comment.getCommentUserId());
        comment.setParentId(comment1.getParentId());
        commentMapper.updateById(comment);
    }

    @Override
    public ResultJson delCommentThumbUp(BlogComment comment) {
        BlogComment comment1 = commentMapper.selectById(comment.getId());
        comment.setApproveCount(comment1.getApproveCount() - 1);
        thumbUpManage(comment, comment1);
        return ResultJson.success("取消成功！", null);
    }
}