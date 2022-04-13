package com.community.admin.course.refCourseUser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.course.entity.Course;
import com.community.admin.course.refCourseUser.entity.RefCourseUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程与用户关联表(RefCourseUser)表数据库访问层
 *
 * @author xmc
 * @since 2021-10-19 21:55:03
 */
public interface RefCourseUserDao extends BaseMapper<RefCourseUser> {
    List<RefCourseUser> selectByUserId(Long userId);

    List<Course> listAll(
            @Param("userId") Long userId,
            @Param("pageNo") Long pageNo,
            @Param("pageSize") Long pageSize);

}