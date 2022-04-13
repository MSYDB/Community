package com.community.admin.course.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.community.admin.course.entity.Course;
import org.apache.ibatis.annotations.Param;
//徐明超

/**
 * 课程(Course)表数据库访问层
 *
 * @author xmc
 * @since 2021-10-15 12:19:02
 */
public interface CourseDao extends BaseMapper<Course> {
    IPage<Course> listAll(IPage<Course> page, @Param(Constants.WRAPPER) QueryWrapper<Course> queryWrapper);

}