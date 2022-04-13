package com.community.admin.course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.entity.Course;
import com.community.admin.course.mapper.CourseDao;
import com.community.admin.course.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
//徐明超

/**
 * 课程(Course)表服务实现类
 *
 * @author xmc
 * @since 2021-10-15 12:19:02
 */
@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {
    @Resource
    CourseDao courseDao;

    @Override
    public IPage<Course> listAll(IPage<Course> page, QueryWrapper<Course> queryWrapper) {
        return courseDao.listAll(page, queryWrapper);
    }

    @Override
    public ResultJson listAll(Long pageNo, Long pageSize, Long id, Long foundUserId,
                              String courseName, Long courseTypeId, String description,
                              Long lowPrice, Long highPrice) {
        Page page = new Page(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (id != null) {
            queryWrapper.eq("id", id);
        }
        if (foundUserId != null) {
            queryWrapper.like("found_user_id", foundUserId);

        }
        if (courseName != null) {
            queryWrapper.like("course_name", courseName);
        }
        if (courseTypeId != null) {
            queryWrapper.eq("course_id", courseTypeId);
        }
        if (description != null) {
            queryWrapper.like("description", description);
        }
        if (lowPrice != null && highPrice != null) {
            queryWrapper.between("price", lowPrice, highPrice);

        }
        if (lowPrice != null && highPrice == null) {
            queryWrapper.ge("price", lowPrice);
        }
        if (lowPrice == null && highPrice != null) {
            queryWrapper.le("price", highPrice);
        }
        IPage<Course> pageInfo = this.page(page, queryWrapper);
        return ResultJson.success(PageWeb.build(pageInfo));
    }
}