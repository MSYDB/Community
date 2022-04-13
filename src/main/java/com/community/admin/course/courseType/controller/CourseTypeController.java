package com.community.admin.course.courseType.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.courseType.entity.CourseType;
import com.community.admin.course.courseType.service.CourseTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
//徐明超
//徐明超

/**
 * 课程类别表(CourseType)表控制层
 *
 * @author xmc
 * @since 2021-10-15 12:50:30
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "courseType")
public class CourseTypeController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CourseTypeService courseTypeService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param courseType 查询实体
     * @return 所有数据
     */
    @RequestMapping("listAll")
    @SelfLog(module = "courseType", name = "展示课程类型")

    @AuthString("listAllCourseType")
    public ResultJson selectAll(@RequestParam(defaultValue = "1") Long pageNo, @RequestParam(defaultValue = "5") Long pageSize,
                                @RequestParam(required = false) Long id,
                                @RequestParam(required = false) String typeName,
                                @RequestParam(required = false) String description) {
        return this.courseTypeService.listAll1(pageNo, pageSize, id
                , typeName, description);

    }


    /**
     * 新增数据
     *
     * @param courseType 实体对象
     * @return 新增结果
     */
    @RequestMapping("insert")
    @SelfLog(module = "courseType", name = "插入课程类型")

    @AuthString("insertCourseType")
    public ResultJson insert(CourseType courseType) {
        return ResultJson.success("添加成功", this.courseTypeService.save(courseType));
    }

    /**
     * 修改数据
     *
     * @param courseType 实体对象
     * @return 修改结果
     */
    @RequestMapping("update")
    @SelfLog(module = "courseType", name = "更新课程类型")

    @AuthString("updateCourseType")
    public ResultJson update(CourseType courseType) {

        return ResultJson.success(this.courseTypeService.updateById(courseType));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping("delete")
    @SelfLog(module = "courseType", name = "删除课程类型")

    @AuthString("deleteCourseType")
    public ResultJson delete(@RequestParam("idList") List<Long> idList) {

        return ResultJson.success("删除成功", this.courseTypeService.removeByIds(idList));
    }
}