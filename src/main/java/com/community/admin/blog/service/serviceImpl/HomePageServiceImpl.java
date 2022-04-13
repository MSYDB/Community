package com.community.admin.blog.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.blog.entity.Blog;
import com.community.admin.blog.mapper.HomePageMapper;
import com.community.admin.blog.service.HomePageService;
import com.community.admin.common.web.PageWeb;
import com.community.admin.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BlogColumnServiceImpl
 * @Description TODO
 * @Author 28067
 * @Date 2021/10/20 17:53
 * @Version 1.0
 **/
@Service
@Transactional
public class HomePageServiceImpl extends ServiceImpl<HomePageMapper, Blog> implements HomePageService {

    @Autowired
    private HomePageMapper homePageMapper;
    @Resource
    private HttpServletRequest request;

    @Override
    public PageWeb homePage(Long pageNo, Long pageSize) {
        List<Map<String, Object>> list = homePageMapper.homePage((pageNo - 1) * pageSize, pageSize);
        PageWeb pageInfo = new PageWeb<>();
        pageInfo.setRecords(list);
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(homePageMapper.listAllCount());
        Long Pages;
        if (homePageMapper.listAllCount() % pageSize == 0) {
            Pages = homePageMapper.listAllCount() / pageSize;
        } else {
            Pages = homePageMapper.listAllCount() / pageSize + 1;
        }
        pageInfo.setTotalPages(Pages);
        return pageInfo;
    }

    @Override
    public PageWeb personalHomePage(Long pageNo, Long pageSize, Long year, Long month, String columnName, String title) {
        String uid = JwtUtil.getAudience(request.getHeader("token"));
        Long longuid = Long.parseLong(uid);
        List<Map<String, Object>> list = homePageMapper.personalHomePage((pageNo - 1) * pageSize, pageSize, year, month, columnName, title, longuid);
        PageWeb pageInfo = new PageWeb<>();
        pageInfo.setRecords(list);
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(homePageMapper.listPersonalCount(year, month, columnName, title, longuid));
        Long Pages;
        if (homePageMapper.listPersonalCount(year, month, columnName, title, longuid) % pageSize == 0) {
            Pages = homePageMapper.listPersonalCount(year, month, columnName, title, longuid) / pageSize;
        } else {
            Pages = homePageMapper.listPersonalCount(year, month, columnName, title, longuid) / pageSize + 1;
        }
        pageInfo.setTotalPages(Pages);
        return pageInfo;
    }

}
