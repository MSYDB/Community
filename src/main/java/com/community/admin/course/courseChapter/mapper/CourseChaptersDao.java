package com.community.admin.course.courseChapter.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.community.admin.course.courseChapter.entity.CourseChapters;
import org.apache.ibatis.annotations.Param;
//徐明超

/**
 * 课程详细章节表(CourseChapters)表数据库访问层
 *
 * @author xmc
 * @since 2021-10-15 12:41:13
 */
public interface CourseChaptersDao extends BaseMapper<CourseChapters> {
    IPage<CourseChapters> listAll(IPage<CourseChapters> page, @Param(Constants.WRAPPER) QueryWrapper<CourseChapters> queryWrapper);
}