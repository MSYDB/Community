package com.community.admin.blog.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.blog.entity.BlogField;
import com.community.admin.blog.mapper.BlogFieldMapper;
import com.community.admin.blog.service.BlogFieldService;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;

/**
 * 博客领域表(BlogField)表服务实现类
 *
 * @author lx
 * @since 2021-10-15 21:42:39
 */
@Slf4j
@Transactional
@Service("blogFieldService")
public class BlogFieldServiceImpl extends ServiceImpl<BlogFieldMapper, BlogField> implements BlogFieldService {
    @Resource
    private BlogFieldMapper fieldMapper;

    @Override
    public ResultJson insertField(String fieldName) {
        QueryWrapper<BlogField> wrapper = new QueryWrapper<>();
        wrapper.eq("field_name", fieldName);
        BlogField field = new BlogField();
        if (fieldMapper.selectOne(wrapper) == null) {
            field.setFieldName(fieldName);
            fieldMapper.insert(field);
            return ResultJson.success("添加成功！", null);
        } else {
            return ResultJson.failure("添加失败！", null);
        }
    }

    @Override
    public ResultJson displayField(Integer pageNo, Integer pageSize, String fieldName) {
        Page<BlogField> page = new Page<>(pageNo, pageSize);
        QueryWrapper<BlogField> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(fieldName)) {
            wrapper.like("field_name", fieldName);
        }
        IPage<BlogField> iPage = fieldMapper.selectPage(page, wrapper);
        if (iPage.getRecords() != null) {
            return ResultJson.success("查询成功！", PageWeb.build(iPage));
        } else {
            return ResultJson.failure("查询失败！", null);
        }
    }
}