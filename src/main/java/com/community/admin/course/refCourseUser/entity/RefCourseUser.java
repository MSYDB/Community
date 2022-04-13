package com.community.admin.course.refCourseUser.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * 课程与用户关联表(RefCourseUser)表实体类
 *
 * @author xmc
 * @since 2021-10-19 21:55:02
 */
@SuppressWarnings("serial")
@Data
public class RefCourseUser extends Model<RefCourseUser> {
    //课程id
    private Long courseId;
    //用户id
    private Long userId;


    public RefCourseUser(Long courseId, Long userId) {
        this.courseId = courseId;
        this.userId = userId;
    }
}