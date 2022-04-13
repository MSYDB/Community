package com.community.admin.rankingList.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.community.admin.wholesituation.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * TODO 作者推荐
 *
 * @author DB
 * <br>CreateDate 2021/10/14 18:08
 */
public interface AuthorRecommendMapper extends BaseMapper<SysUser> {
    IPage<SysUser> authorRecommend(IPage<SysUser> page, @Param("fieldId") Long fieldId);
}
