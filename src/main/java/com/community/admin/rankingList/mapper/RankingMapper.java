package com.community.admin.rankingList.mapper;

import com.community.admin.blog.entity.Blog;
import com.community.admin.wholesituation.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * TODO
 *
 * @author DB
 * <br>CreateDate 2021/10/17 1:48
 */
public interface RankingMapper {
    List<Double> ranking();

    List<Double> selectId();

    List<Blog> getMessage(@Param("fzr") Double[] fzr);

    List<Blog> paiHang();

    List<SysUser> authorPaiHang(@Param("fieldId") Integer fieldId);

}
