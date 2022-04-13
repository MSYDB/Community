package com.community.admin.rankingList.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.community.admin.blog.entity.Blog;

/**
 * TODO 每日推荐的mapper
 *
 * @author DB
 * <br>CreateDate 2021/10/13 23:02
 */
public interface RecommendMapper extends BaseMapper<Blog> {
    IPage<Blog> EveryDayRecommend(IPage<Blog> page);
}
