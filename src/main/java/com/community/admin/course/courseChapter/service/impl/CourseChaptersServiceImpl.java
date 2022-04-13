package com.community.admin.course.courseChapter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.courseChapter.entity.CourseChapters;
import com.community.admin.course.courseChapter.mapper.CourseChaptersDao;
import com.community.admin.course.courseChapter.service.CourseChaptersService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
//徐明超

/**
 * 课程详细章节表(CourseChapters)表服务实现类
 *
 * @author xmc
 * @since 2021-10-15 12:41:14
 */
@Service("courseChaptersService")
public class CourseChaptersServiceImpl extends ServiceImpl<CourseChaptersDao, CourseChapters> implements CourseChaptersService {

    @Resource
    private CourseChaptersDao courseChaptersDao;

    @Override
    public IPage<CourseChapters> listAll(IPage<CourseChapters> page, QueryWrapper<CourseChapters> queryWrapper) {
        return courseChaptersDao.listAll(page, queryWrapper);
    }

    @Override
    public ResultJson listAll1(Long pageNo, Long pageSize, Long id, Long courseId,
                               Long uploadUserId, String chapterTitle, Long orderNum) {
        QueryWrapper<CourseChapters> queryWrapper = new QueryWrapper<>();
        Page<CourseChapters> page = new Page<>(pageNo, pageSize);
        if (id != null) {
            queryWrapper.eq("id", id);
        }
        if (courseId != null) {
            queryWrapper.eq("course_id", courseId);
        }
        if (uploadUserId != null) {
            queryWrapper.eq("upload_user_id", uploadUserId);
        }
        if (chapterTitle != null) {
            queryWrapper.eq("chapter_title", chapterTitle);
        }
        if (orderNum != null) {
            queryWrapper.eq("order_num", orderNum);
        }
        IPage<CourseChapters> pageInfo = this.listAll(page, queryWrapper);
        return ResultJson.success(PageWeb.build(pageInfo));
    }
}