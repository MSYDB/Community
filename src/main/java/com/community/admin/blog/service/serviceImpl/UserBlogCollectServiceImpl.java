package com.community.admin.blog.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.blog.entity.Blog;
import com.community.admin.blog.entity.UserBlogCollect;
import com.community.admin.blog.mapper.BlogMapper;
import com.community.admin.blog.mapper.UserBlogCollectMapper;
import com.community.admin.blog.service.UserBlogCollectService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户收藏记录表(UserBlogCollect)表服务实现类
 *
 * @author lx
 * @since 2021-10-16 15:57:28
 */
@Transactional
@Service("userBlogCollectService")
public class UserBlogCollectServiceImpl extends ServiceImpl<UserBlogCollectMapper, UserBlogCollect> implements UserBlogCollectService {

    @Resource
    private UserBlogCollectMapper collectMapper;
    @Resource
    private HttpServletRequest request;
    @Resource
    private BlogMapper blogMapper;

    @Override
    public ResultJson listAllCollectBlog(Integer pageNo, Integer pageSize) {
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        List<Blog> list = blogMapper.collectBlogList(Long.valueOf(uid));
        if (list.size() != 0) {
            return ResultJson.success("查询成功！", list);
        } else {
            return ResultJson.failure("查询失败！", null);
        }
    }

    @Override
    public ResultJson blogCollect(UserBlogCollect collect) {
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        collect.setUserId(Long.valueOf(uid));
        if (collectMapper.insert(collect) == 1) {
            collectManage(collect.getBlogId());
            return ResultJson.success("收藏成功！", null);
        } else {
            return ResultJson.failure("收藏失败！", null);
        }
    }

    private void collectManage(Long blogId) {
        Blog blog = new Blog();
        Blog blog1 = blogMapper.selectById(blogId);
        blog.setId(blog1.getId());

        blog.setPageViewCount(blog1.getPageViewCount());
        blog.setCommentCount(blog1.getCommentCount());
        blog.setStatus(blog1.getStatus());
        blog.setApproveCount(blog1.getApproveCount());
        blog.setCollectCount(blog1.getCollectCount() + 1);
        blog.setIsTop(blog1.getIsTop());

        blogMapper.updateById(blog);
    }

    @Override
    public ResultJson deleteCollect(Long blogId) {
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        QueryWrapper<UserBlogCollect> wrapper = new QueryWrapper<>();
        wrapper.eq("blog_id", blogId).eq("user_id", Long.valueOf(uid));
        if (collectMapper.delete(wrapper) == 1) {
            delCollectManage(blogId);
            return ResultJson.success("取消成功！", null);
        } else {
            return ResultJson.failure("取消失败！", null);
        }
    }

    private void delCollectManage(Long blogId) {
        Blog blog = new Blog();
        Blog blog1 = blogMapper.selectById(blogId);
        blog.setId(blog1.getId());
        blog.setPageViewCount(blog1.getPageViewCount());
        blog.setCommentCount(blog1.getCommentCount());
        blog.setStatus(blog1.getStatus());
        blog.setApproveCount(blog1.getApproveCount());
        blog.setCollectCount(blog1.getCollectCount() - 1);
        blog.setIsTop(blog1.getIsTop());
        blogMapper.updateById(blog);
    }
}