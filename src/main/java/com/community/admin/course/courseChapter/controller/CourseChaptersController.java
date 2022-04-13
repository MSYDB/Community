package com.community.admin.course.courseChapter.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.courseChapter.entity.CourseChapters;
import com.community.admin.course.courseChapter.service.CourseChaptersService;
import com.community.admin.course.service.CourseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
//徐明超

/**
 * 课程详细章节表(CourseChapters)表控制层
 *
 * @author xmc
 * @since 2021-10-15 12:41:14
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "courseChapters")
public class CourseChaptersController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CourseChaptersService courseChaptersService;
    @Resource
    private HttpServletRequest request;
    @Resource
    private CourseService courseService;

    /**
     * 分页查询所有数据
     *
     * @param page           分页对象
     * @param courseChapters 查询实体
     * @return 所有数据
     */
    @RequestMapping("listAll")
    @SelfLog(module = "courseChapter", name = "展示课程章节")
    @Transactional
    @AuthString("listAllCourseChapters")
    public ResultJson selectAll(@RequestParam(defaultValue = "1") Long pageNo,
                                @RequestParam(defaultValue = "5") Long pageSize,
                                @RequestParam(required = false) Long id,
                                @RequestParam(required = false) Long courseId,
                                @RequestParam(required = false) Long uploadUserId,
                                @RequestParam(required = false) String chapterTitle,
                                @RequestParam(required = false) Long orderNum) {
        return courseChaptersService.listAll1(pageNo, pageSize, id, courseId, uploadUserId,
                chapterTitle, orderNum);

    }


    /**
     * 新增数据
     *
     * @param courseChapters 实体对象
     * @return 新增结果
     */
    @RequestMapping("insert")
    @SelfLog(module = "courseChapter", name = "插入课程章节")

    @AuthString("insertCourseChapters")
    public ResultJson insert(CourseChapters courseChapters) {
        return ResultJson.success("添加成功", this.courseChaptersService.save(courseChapters));
    }

    /**
     * 修改数据
     *
     * @param courseChapters 实体对象
     * @return 修改结果
     */
    @RequestMapping("update")
    @SelfLog(module = "courseChapter", name = "更新课程章节")

    @AuthString("updateCourseChapters")
    public ResultJson update(CourseChapters courseChapters) {
        return ResultJson.success(this.courseChaptersService.updateById(courseChapters));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping("delete")
    @SelfLog(module = "courseChapter", name = "删除课程章节")

    @AuthString("deleteCourseChapters")
    public ResultJson delete(@RequestParam("idList") List<Long> idList) {
        return ResultJson.success("删除成功", this.courseChaptersService.removeByIds(idList));
    }
}

