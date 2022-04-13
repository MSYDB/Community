package com.community.admin.rankingList.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.wholesituation.entity.SysUser;

/**
 * TODO 作者推荐
 *
 * @author DB
 * <br>CreateDate 2021/10/14 18:20
 */
public interface AuthorRecommendService extends IService<SysUser> {
    IPage<SysUser> authorRecommend(Integer pageNo, Integer pageSize, Long fieldId);
}
