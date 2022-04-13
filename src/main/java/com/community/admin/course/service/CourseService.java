package com.community.admin.course.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.entity.Course;
import org.springframework.web.bind.annotation.RequestParam;
//徐明超

/**
 * 课程(Course)表服务接口
 *
 * @author xmc
 * @since 2021-10-15 12:19:02
 */
public interface CourseService extends IService<Course> {
    IPage<Course> listAll(IPage<Course> page, QueryWrapper<Course> queryWrapper);

    ResultJson listAll(@RequestParam(defaultValue = "1") Long pageNo, @RequestParam(defaultValue = "5") Long pageSize,
                       @RequestParam(required = false) Long id, @RequestParam(required = false) Long foundUserId,
                       @RequestParam(required = false) String courseName, @RequestParam(required = false) Long courseTypeId,
                       @RequestParam(required = false) String description, @RequestParam(required = false) Long lowPrice,
                       @RequestParam(required = false) Long highPrice);

}