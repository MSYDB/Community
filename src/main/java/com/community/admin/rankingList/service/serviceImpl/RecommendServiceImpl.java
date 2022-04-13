package com.community.admin.rankingList.service.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.blog.entity.Blog;
import com.community.admin.rankingList.mapper.RecommendMapper;
import com.community.admin.rankingList.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * TODO  每日推荐
 *
 * @author DB
 * <br>CreateDate 2021/10/13 22:46
 */
@Service("mainPage")
@Slf4j
@Transactional
public class RecommendServiceImpl extends ServiceImpl<RecommendMapper, Blog> implements RecommendService {

    @Autowired
    private RecommendMapper recommendMapper;


    @Override
    public IPage<Blog> EveryDayRecommend(Integer pageNo, Integer pageSize) {
        IPage<Blog> page = new Page<>(pageNo, pageSize);
        return recommendMapper.EveryDayRecommend(page);
    }
}
