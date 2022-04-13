package com.community.admin.rankingList.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.blog.entity.Blog;

/**
 * TODO 每日推荐的service
 *
 * @author DB
 * <br>CreateDate 2021/10/13 22:45
 */
public interface RecommendService extends IService<Blog> {
    IPage<Blog> EveryDayRecommend(Integer pageNo, Integer pageSize);
}
