package com.community.admin.course.courseType.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.community.admin.course.courseType.entity.CourseType;
import org.apache.ibatis.annotations.Param;
//徐明超

/**
 * 课程类别表(CourseType)表数据库访问层
 *
 * @author xmc
 * @since 2021-10-15 12:50:30
 */
public interface CourseTypeDao extends BaseMapper<CourseType> {
    IPage<CourseType> listAll(IPage<CourseType> page, @Param(Constants.WRAPPER) QueryWrapper<CourseType> queryWrapper);
}