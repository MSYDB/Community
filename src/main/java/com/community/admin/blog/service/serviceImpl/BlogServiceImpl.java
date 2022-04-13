package com.community.admin.blog.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.blog.entity.Blog;
import com.community.admin.blog.entity.BlogColumn;
import com.community.admin.blog.entity.BlogField;
import com.community.admin.blog.mapper.BlogColumnMapper;
import com.community.admin.blog.mapper.BlogFieldMapper;
import com.community.admin.blog.mapper.BlogMapper;
import com.community.admin.blog.service.BlogService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.personalmessage.mapper.PersonalMapper;
import com.community.admin.util.JwtUtil;
import com.community.admin.wholesituation.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 博客表(Blog)表服务实现类
 *
 * @author lx
 * @since 2021-10-14 15:30:59
 */
@Service("blogService")
@Transactional
@Slf4j
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
    @Resource
    private BlogMapper blogMapper;
    @Resource
    private BlogColumnMapper columnMapper;
    @Resource
    private HttpServletRequest request;
    @Resource
    private PersonalMapper personalMapper;
    @Resource
    private BlogFieldMapper fieldMapper;

    @Override
    public ResultJson showData(Long id) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        if (blogMapper.selectOne(wrapper) != null) {
            return ResultJson.success("查询成功", blogMapper.selectOne(wrapper));
        } else {
            return ResultJson.failure("查询失败", null);
        }
    }

    @Override
    public ResultJson releaseArticle(Blog blog, List<String> fieldList, String columnName) {
        if (fieldList.size() != 0) {
            blog.setAuthorId(Long.valueOf(JwtUtil.getAudience(request.getHeader("token"))));
            blog.setIsTop(2);
            if (blog.getStatus() == 1) {
                blog.setReleaseTime(new Date());
            }
            if (blogMapper.insert(blog) == 1) {
                fieldManage(blog, fieldList);
                if (!StringUtils.isEmpty(columnName)) {
                    columnManage(blog, columnName);
                }
                return ResultJson.success("执行成功！", null);
            } else {
                return ResultJson.failure("发表失败！", null);
            }
        } else {
            return ResultJson.failure("标签不可为空！", null);
        }
    }

    /**
     * 栏目判定及操作
     **/
    private void columnManage(Blog blog, String columnName) {
        QueryWrapper<BlogColumn> wrapper = new QueryWrapper<>();
        wrapper.eq("column_name", columnName);
        if (columnMapper.selectOne(wrapper) == null) {
            BlogColumn column = new BlogColumn();
            column.setColumnName(columnName);
            column.setUserId(Long.valueOf(JwtUtil.getAudience(request.getHeader("token"))));
            columnMapper.insert(column);
        }
        Long columnId = columnMapper.selectOne(wrapper).getId();

        if (!StringUtils.isEmpty(blog.getTitle())) {
            QueryWrapper<Blog> blogWrapper = new QueryWrapper<>();
            blogWrapper.eq("title", blog.getTitle());
            Long blogId = blogMapper.selectOne(blogWrapper).getId();
            blogMapper.delRefBlogColumn(blogId);
            blogMapper.RefBlogColumn(blogId, columnId);
        } else {
            blogMapper.delRefBlogColumn(blog.getId());
            blogMapper.RefBlogColumn(blog.getId(), columnId);
        }
    }

    /**
     * 领域判定及操作
     **/
    private void fieldManage(Blog blog, List<String> fieldList) {
        List<Long> idList = new ArrayList<>();
        for (String name : fieldList) {
            QueryWrapper<BlogField> wrapper = new QueryWrapper<>();
            wrapper.eq("field_name", name);
            if (fieldMapper.selectOne(wrapper) == null) {
                BlogField field = new BlogField();
                field.setFieldName(name);
                fieldMapper.insert(field);
            }
            idList.add(selectMessage(name));
        }
        if (!StringUtils.isEmpty(blog.getTitle())) {
            QueryWrapper<Blog> blogWrapper = new QueryWrapper<>();
            blogWrapper.eq("title", blog.getTitle());
            Long blogId = blogMapper.selectOne(blogWrapper).getId();
            blogMapper.delRefBlogField(blogId);
            blogMapper.RefBlogField(blogId, idList);
        } else {
            blogMapper.delRefBlogField(blog.getId());
            blogMapper.RefBlogField(blog.getId(), idList);
        }
    }

    /**
     * 查询领域信息
     **/
    private Long selectMessage(String name) {
        QueryWrapper<BlogField> wrapper = new QueryWrapper<>();
        wrapper.eq("field_name", name);
        return fieldMapper.selectOne(wrapper).getId();
    }

    @Override
    public ResultJson updateBlog(Blog blog, List<String> fieldList, String columnName) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("id", blog.getId());
        Blog blog1 = blogMapper.selectOne(wrapper);
        blog.setAuthorId(blog1.getAuthorId());
        blog.setCollectCount(blog1.getCollectCount());
        blog.setApproveCount(blog1.getApproveCount());
        blog.setCommentCount(blog1.getCommentCount());
        blog.setPageViewCount(blog1.getPageViewCount());
        blog.setIsTop(blog1.getIsTop());
        if (blog.getStatus() == 1) {
            blog.setReleaseTime(new Date());
        }
        if (blogMapper.updateById(blog) == 1) {
            fieldManage(blog, fieldList);
            if (!StringUtils.isEmpty(columnName)) {
                columnManage(blog, columnName);
            }
            return ResultJson.success("更新成功！", null);
        } else {
            return ResultJson.failure("更新失败！", null);
        }
    }

    @Override
    public ResultJson showBlog(Long id) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        Blog data = blogMapper.selectOne(wrapper);
        QueryWrapper<SysUser> userWrapper = new QueryWrapper<>();
        userWrapper.eq("id", data.getAuthorId())
                .select("nick_name", "head_photo");
        SysUser user = personalMapper.selectOne(userWrapper);
        data.setNickName(user.getNickName());
        data.setHeadPhoto(user.getHeadPhoto());
        if (data.getContent() != null) {
            return ResultJson.success("查询成功！", data);
        } else {
            return ResultJson.failure("查询失败！", null);
        }
    }
}