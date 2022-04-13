package com.community.admin.rankingList.service.serviceImpl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.rankingList.mapper.AuthorRecommendMapper;
import com.community.admin.rankingList.service.AuthorRecommendService;
import com.community.admin.wholesituation.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO 作者推荐
 *
 * @author DB
 * <br>CreateDate 2021/10/14 18:21
 */
@Service
public class AuthorRecommendServiceImpl extends ServiceImpl<AuthorRecommendMapper, SysUser>
        implements AuthorRecommendService {

    @Autowired
    private AuthorRecommendMapper authorRecommendMapper;

    @Override
    public IPage<SysUser> authorRecommend(Integer pageNo, Integer pageSize, Long fieldId) {
        IPage<SysUser> page = new Page<>(pageNo, pageSize);
        return authorRecommendMapper.authorRecommend(page, fieldId);
    }
}
