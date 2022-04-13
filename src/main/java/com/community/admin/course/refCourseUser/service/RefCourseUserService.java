package com.community.admin.course.refCourseUser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.course.entity.Course;
import com.community.admin.course.refCourseUser.entity.RefCourseUser;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 课程与用户关联表(RefCourseUser)表服务接口
 *
 * @author xmc
 * @since 2021-10-19 21:55:03
 */
public interface RefCourseUserService extends IService<RefCourseUser> {
    List<RefCourseUser> selectByUserId(Long userId);

    List<Course> listAll(Long pageNo, Long pageSize);

    String update(@RequestParam(required = true) List<Long> courseIdList);

}