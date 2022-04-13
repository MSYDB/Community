package com.community.admin.course.courseType.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.courseType.entity.CourseType;
import org.springframework.web.bind.annotation.RequestParam;
//徐明超

/**
 * 课程类别表(CourseType)表服务接口
 *
 * @author xmc
 * @since 2021-10-15 12:50:30
 */
public interface CourseTypeService extends IService<CourseType> {
    ResultJson listAll1(@RequestParam(defaultValue = "1") Long pageNo, @RequestParam(defaultValue = "5") Long pageSize,
                        @RequestParam(required = false) Long id,
                        @RequestParam(required = false) String typeName,
                        @RequestParam(required = false) String description);

    IPage<CourseType> listAll(IPage<CourseType> page, QueryWrapper<CourseType> queryWrapper);
}