package com.community.admin.blog.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.blog.entity.Blog;
import com.community.admin.blog.entity.BlogColumn;
import com.community.admin.blog.mapper.BlogColumnMapper;
import com.community.admin.blog.mapper.BlogMapper;
import com.community.admin.blog.service.BlogColumnService;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 博客栏目表(BlogColumn)表服务实现类
 *
 * @author lx
 * @since 2021-10-20 14:34:27
 */
@Transactional
@Service("blogColumnService")
public class BlogColumnServiceImpl extends ServiceImpl<BlogColumnMapper, BlogColumn> implements BlogColumnService {
    @Resource
    private BlogColumnMapper columnMapper;
    @Resource
    private HttpServletRequest request;
    @Resource
    private BlogMapper blogMapper;

    @Override
    public ResultJson listAllColumn(Integer pageNo, Integer pageSize) {
        Page<BlogColumn> page = new Page<>(pageNo, pageSize);
        QueryWrapper<BlogColumn> wrapper = new QueryWrapper<>();
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        wrapper.eq("user_id", Long.valueOf(uid));
        IPage<BlogColumn> iPage = columnMapper.selectPage(page, wrapper);
        if (iPage.getRecords() != null) {
            return ResultJson.success("查询成功！", PageWeb.build(iPage));
        } else {
            return ResultJson.failure("查询失败！", null);
        }
    }

    @Override
    public ResultJson listColumnContent(Integer pageNo, Integer pageSize, Long columnId) {
        QueryWrapper<Blog> wrapper = new QueryWrapper<>();
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        wrapper.eq("author_id", Long.valueOf(uid));
        List<Blog> list = blogMapper.selectList(wrapper);
        List<Long> blogIdList = blogMapper.filter(columnId);
        List<Blog> newList = list.stream().filter(s -> blogIdList.contains(s.getId())).collect(Collectors.toList());
        if (newList.size() != 0) {
            return ResultJson.success("查询成功！", newList);
        } else {
            return ResultJson.failure("查询失败！", null);
        }
    }

    @Override
    public ResultJson updateColumn(BlogColumn blogColumn) {
        if (columnMapper.updateById(blogColumn) == 1) {
            return ResultJson.success("修改成功！", null);
        } else {
            return ResultJson.failure("修改失败！", null);
        }
    }

    @Override
    public ResultJson insertColumn(BlogColumn blogColumn) {
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        blogColumn.setUserId(Long.valueOf(uid));
        if (columnMapper.insert(blogColumn) == 1) {
            return ResultJson.success("添加成功！", null);
        } else {
            return ResultJson.failure("添加失败！", null);
        }
    }
}