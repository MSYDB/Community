package com.community.admin.course.refCourseUser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.course.entity.Course;
import com.community.admin.course.refCourseUser.entity.RefCourseUser;
import com.community.admin.course.refCourseUser.mapper.RefCourseUserDao;
import com.community.admin.course.refCourseUser.service.RefCourseUserService;
import com.community.admin.util.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 课程与用户关联表(RefCourseUser)表服务实现类
 *
 * @author xmc
 * @since 2021-10-19 21:55:03
 */
@Service("refCourseUserService")
public class RefCourseUserServiceImpl extends ServiceImpl<RefCourseUserDao, RefCourseUser> implements RefCourseUserService {
    @Resource
    private RefCourseUserDao refCourseUserDao;
    @Resource
    private HttpServletRequest httpServletRequest;

    @Override
    public List<RefCourseUser> selectByUserId(Long userId) {
        return refCourseUserDao.selectByUserId(userId);
    }

    @Transactional
    @Override
    public List<Course> listAll(Long pageNo, Long pageSize) {
        String token = httpServletRequest.getHeader("token");
        Long userId = Long.valueOf(JwtUtil.getAudience(token));

        return refCourseUserDao.listAll(userId, (pageNo - 1) * pageSize, pageSize);
    }

    @Transactional
    @Override
    public String update(List<Long> courseIdList) {
        String token = httpServletRequest.getHeader("token");
        Long userId = Long.valueOf(JwtUtil.getAudience(token));
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        refCourseUserDao.delete(queryWrapper);
        for (Long courseId : courseIdList) {
            RefCourseUser refCourseUser = new RefCourseUser(courseId, userId);
            refCourseUserDao.insert(refCourseUser);
        }
        return "更新成功";
    }


}