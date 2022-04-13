package com.community.admin.course.courseType.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.courseType.entity.CourseType;
import com.community.admin.course.courseType.mapper.CourseTypeDao;
import com.community.admin.course.courseType.service.CourseTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
//徐明超

/**
 * 课程类别表(CourseType)表服务实现类
 *
 * @author xmc
 * @since 2021-10-15 12:50:30
 */
@Service("courseTypeService")
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeDao, CourseType> implements CourseTypeService {
    @Resource
    private CourseTypeDao courseTypeDao;

    @Override
    public ResultJson listAll1(Long pageNo, Long pageSize, Long id, String typeName, String description) {
        QueryWrapper<CourseType> queryWrapper = new QueryWrapper<>();
        Page<CourseType> page = new Page<>(pageNo, pageSize);
        if (id != null) {
            queryWrapper.eq("id", id);
        }
        if (typeName != null) {
            queryWrapper.eq("typeName", typeName);
        }
        if (description != null) {
            queryWrapper.eq("Description", description);
        }

        IPage<CourseType> pageInfo = this.listAll(page, queryWrapper);
        return ResultJson.success(PageWeb.build(pageInfo));
    }

    @Override
    public IPage<CourseType> listAll(IPage<CourseType> page, QueryWrapper<CourseType> queryWrapper) {
        return courseTypeDao.listAll(page, queryWrapper);
    }
}