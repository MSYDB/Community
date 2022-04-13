package com.community.admin.blog.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.blog.entity.Blog;
import com.community.admin.blog.entity.BlogComment;
import com.community.admin.blog.mapper.BlogCommentMapper;
import com.community.admin.blog.mapper.BlogMapper;
import com.community.admin.blog.service.BlogCommentService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.personalmessage.mapper.PersonalMapper;
import com.community.admin.util.JwtUtil;
import com.community.admin.wholesituation.entity.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 博客评论表(BlogComment)表服务实现类
 *
 * @author lx
 * @since 2021-10-15 00:28:18
 */
@Transactional
@Service("blogCommentService")
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {
    @Resource
    private BlogCommentMapper commentMapper;
    @Resource
    private HttpServletRequest request;
    @Resource
    private BlogMapper blogMapper;

    @Override
    public ResultJson replyComment(BlogComment comment) {
        comment.setCommentUserId(Long.valueOf(JwtUtil.getAudience(request.getHeader("token"))));
        comment.setParentId(comment.getId());
        comment.setApproveCount(0);
        BlogComment comment1 = commentMapper.selectById(comment.getId());
        if (commentMapper.insert(comment) == 1) {
            blogManage(comment1);
            return ResultJson.success("回复成功！", null);
        } else {
            return ResultJson.failure("回复失败！", null);
        }
    }

    @Override
    public ResultJson releaseComment(BlogComment comment) {
        comment.setParentId(0L);
        comment.setApproveCount(0);
        comment.setCommentUserId(Long.valueOf(JwtUtil.getAudience(request.getHeader("token"))));

        blogManage(comment);

        if (commentMapper.insert(comment) == 1) {
            return ResultJson.success("评论成功！", null);
        } else {
            return ResultJson.failure("评论失败！", null);
        }
    }

    /**
     * 维持博客数据，并使浏览量增一
     **/
    private void blogManage(BlogComment comment) {
        Blog blog = new Blog();
        Blog blog1 = blogMapper.selectById(comment.getBlogId());

        blog.setAuthorId(blog1.getAuthorId());
        blog.setId(comment.getBlogId());
        blog.setStatus(blog1.getStatus());
        blog.setPageViewCount(blog1.getPageViewCount());
        blog.setCollectCount(blog1.getCollectCount());
        blog.setApproveCount(blog1.getApproveCount());
        blog.setCommentCount(blog1.getCommentCount() + 1);
        blog.setIsTop(blog1.getIsTop());
        blogMapper.updateById(blog);
    }


    @Override
    public ResultJson listAllComment(Integer pageNo, Integer pageSize, Long blogId) {
        List<BlogComment> list = list(new LambdaQueryWrapper<BlogComment>()
                .eq(BlogComment::getBlogId, blogId)
        );
        List<BlogComment> first = list.stream()
                .filter(s -> s.getParentId().equals(0L))
                .collect(Collectors.toList());
        for (BlogComment comment : first) {
            personalMessage(comment);
            getChildren(comment, list);
        }
        if (first.size() != 0) {
            return ResultJson.success("查询成功", first);
        } else {
            return ResultJson.failure("查询失败");
        }
    }

    @Resource
    private PersonalMapper personalMapper;

    private BlogComment getChildren(BlogComment comment, List<BlogComment> list) {

        for (BlogComment blogComment : list) {
            if (blogComment.getParentId().equals(comment.getId())) {
                personalMessage(blogComment);
                comment.getCommentList().add(getChildren(blogComment, list));
            }
        }
        return comment;
    }

    private void personalMessage(BlogComment blogComment) {
        QueryWrapper<SysUser> userWrapper = new QueryWrapper<>();
        userWrapper.eq("id", blogComment.getCommentUserId())
                .select("nick_name", "head_photo");
        SysUser user = personalMapper.selectOne(userWrapper);
        blogComment.setNickName(user.getNickName());
        blogComment.setHeadPhoto(user.getHeadPhoto());
    }
}