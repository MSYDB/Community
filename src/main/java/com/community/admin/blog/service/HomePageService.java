package com.community.admin.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.blog.entity.Blog;
import com.community.admin.common.web.PageWeb;

public interface HomePageService extends IService<Blog> {

    PageWeb homePage(Long pageNo, Long pageSize);

    PageWeb personalHomePage(Long pageNo, Long pageSize, Long year, Long month, String columnName, String title);
}
