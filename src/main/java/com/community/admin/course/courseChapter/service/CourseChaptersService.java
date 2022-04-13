package com.community.admin.course.courseChapter.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.courseChapter.entity.CourseChapters;
import org.springframework.web.bind.annotation.RequestParam;
//徐明超

/**
 * 课程详细章节表(CourseChapters)表服务接口
 *
 * @author xmc
 * @since 2021-10-15 12:41:14
 */
public interface CourseChaptersService extends IService<CourseChapters> {
    IPage<CourseChapters> listAll(IPage<CourseChapters> page, QueryWrapper<CourseChapters> queryWrapper);

    ResultJson listAll1(@RequestParam(defaultValue = "1") Long pageNo,
                        @RequestParam(defaultValue = "5") Long pageSize,
                        @RequestParam(required = false) Long id,
                        @RequestParam(required = false) Long courseId,
                        @RequestParam(required = false) Long uploadUserId,
                        @RequestParam(required = false) String chapterTitle,
                        @RequestParam(required = false) Long orderNum);
}