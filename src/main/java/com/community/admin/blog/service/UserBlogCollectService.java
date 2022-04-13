package com.community.admin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.blog.entity.UserBlogCollect;
import com.community.admin.common.web.ResultJson;

/**
 * 用户收藏记录表(UserBlogCollect)表服务接口
 *
 * @author lx
 * @since 2021-10-16 15:57:28
 */
public interface UserBlogCollectService extends IService<UserBlogCollect> {

    ResultJson listAllCollectBlog(Integer pageNo, Integer pageSize);


    ResultJson blogCollect(UserBlogCollect collect);

    ResultJson deleteCollect(Long blogId);
}