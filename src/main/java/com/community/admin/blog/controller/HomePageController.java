package com.community.admin.blog.controller;

import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.blog.service.HomePageService;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.PageWeb;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName ColumnController
 * @Description 主页
 * @Author 28067
 * @Date 2021/10/20 17:49
 * @Version 1.0
 **/
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "blog")
public class HomePageController {
    @Resource
    private HomePageService homePageService;


    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "主页")
    @AuthString("homePage")
    @RequestMapping("homePage")
    public PageWeb homePage(Long pageNo, Long pageSize) {
        return homePageService.homePage(pageNo, pageSize);
    }


    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "个人主页")
    @AuthString("personalHomePage")
    @RequestMapping("personalHomePage")
    public PageWeb personalHomePage(Long pageNo, Long pageSize, Long year, Long month, String columnName, String title) {
        return homePageService.personalHomePage(pageNo, pageSize, year, month, columnName, title);
    }
}
