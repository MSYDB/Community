package com.community.admin.course.controller;


import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.entity.Course;
import com.community.admin.course.service.CourseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 课程(Course)表控制层
 *
 * @author xmc
 * @since 2021-10-15 12:19:03
 */


@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "course")
public class CourseController {
    /**
     * 服务对象
     */
    @Resource
    private CourseService courseService;
    @Resource
    private HttpServletRequest request;

    /**
     * 分页查询所有数据
     *
     * @param page   分页对象
     * @param course 查询实体
     * @return 所有数据
     */
    @RequestMapping("listAll")
    @SelfLog(module = "course", name = "展示课程")

    public ResultJson listAll(@RequestParam(defaultValue = "1") Long pageNo, @RequestParam(defaultValue = "5") Long pageSize,
                              @RequestParam(required = false) Long id, @RequestParam(required = false) Long foundUserId,
                              @RequestParam(required = false) String courseName, @RequestParam(required = false) Long courseTypeId,
                              @RequestParam(required = false) String description, @RequestParam(required = false) Long lowPrice,
                              @RequestParam(required = false) Long highPrice) {
        return courseService.listAll(pageNo, pageSize, id, foundUserId,
                courseName, courseTypeId, description, lowPrice, highPrice);

    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
//    @GetMapping("{id}")
//    public R selectOne(@PathVariable Serializable id) {
//        return success(this.courseService.getById(id));
//    }

    /**
     * @param course
     * @return com.baomidou.mybatisplus.extension.api.R
     * @author xumingchao
     * @Description
     */

    @RequestMapping("insert")
    @SelfLog(module = "course", name = "插入课程")
    @AuthString("insertCourse")
    public ResultJson insert(Course course) {

        return ResultJson.success("添加成功", this.courseService.save(course));
    }

    /**
     * 修改数据
     *
     * @param course 实体对象
     * @return 修改结果
     */
    @RequestMapping("update")
    @SelfLog(module = "course", name = "更新课程")
    @AuthString("updateCourse")
    public ResultJson update(Course course) {

        return ResultJson.success(this.courseService.updateById(course));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping("delete")
    @SelfLog(module = "course", name = "删除课程")

    @AuthString("deleteCourse")
    public ResultJson delete(@RequestParam("idList") List<Long> idList) {
        return ResultJson.success("删除成功", this.courseService.removeByIds(idList));
    }
}