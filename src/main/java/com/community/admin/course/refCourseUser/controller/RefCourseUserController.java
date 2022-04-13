package com.community.admin.course.refCourseUser.controller;


import com.baomidou.mybatisplus.extension.api.ApiController;
import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.course.refCourseUser.entity.RefCourseUser;
import com.community.admin.course.refCourseUser.service.RefCourseUserService;
import com.community.admin.course.service.CourseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 课程与用户关联表(RefCourseUser)表控制层
 *
 * @author xmc
 * @since 2021-10-19 21:55:03
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "refCourseUser")
public class RefCourseUserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private RefCourseUserService refCourseUserService;
    @Resource
    private CourseService courseService;

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @RequestMapping("listAll")
    @SelfLog(module = "refCourseUser", name = "展示用户课程")
    @Transactional
    @AuthString("listAllrefCourseUser")
    public ResultJson listAll(
            @RequestParam(defaultValue = "1") Long pageNo,
            @RequestParam(defaultValue = "5") Long pageSize
    ) {
        return ResultJson.success(refCourseUserService.listAll(pageNo, pageSize));

//       List<Course>courseList=new ArrayList<>();
//       Page<Course> page =new Page<>(pageNo,pageSize);
//       List<RefCourseUser> refCourseUsers = refCourseUserService.selectByUserId(userId);
//    for(RefCourseUser refCourseUser:refCourseUsers){
//        Course byId = courseService.getById(refCourseUser.getCourseId());
//        courseList.add(byId);
//    }
//       System.out.println("=====");
//       System.out.println(courseList);
//    page.setRecords(courseList);
//   return ResultJson.success("查看成功", PageWeb.build(page));
    }


    /**
     * 新增数据
     *
     * @param refCourseUser 实体对象
     * @return 新增结果
     */
    @RequestMapping("insert")
    @SelfLog(module = "refCourseUser", name = "插入用户课程")
    @Transactional
    @AuthString("insertrefCourseUser")
    public ResultJson insert(RefCourseUser refCourseUser) {

        return ResultJson.success(this.refCourseUserService.save(refCourseUser));
    }

    /**
     * 修改数据
     *
     * @return 修改结果
     */

    @RequestMapping("update")
    @SelfLog(module = "refCourseUser", name = "更新用户课程")
    @Transactional
    @AuthString("updaterefCourseUser")
    public ResultJson update(@RequestParam(required = true) List<Long> courseIdList
    ) {
        return ResultJson.success(refCourseUserService.update(courseIdList));
//        return ResultJson.success(this.refCourseUserService.updateById(refCourseUser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @RequestMapping("delete")
    @SelfLog(module = "refCourseUser", name = "删除用户课程")
    @Transactional
    @AuthString("deleterefCourseUser")
    public ResultJson delete(@RequestParam("idList") List<Long> idList) {
        return ResultJson.success(this.refCourseUserService.removeByIds(idList));
    }
}