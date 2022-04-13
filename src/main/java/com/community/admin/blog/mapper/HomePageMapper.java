package com.community.admin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface HomePageMapper extends BaseMapper<Blog> {


    Long listAllCount();

    List<Map<String, Object>> homePage(@Param("pageNo") Long pageNo, @Param("pageSize") Long pageSize);

    List<Map<String, Object>> personalHomePage(@Param("pageNo") Long pageNo, @Param("pageSize") Long pageSize,
                                               @Param("year") Long year, @Param("month") Long month,
                                               @Param("columnName") String columnName,
                                               @Param("title") String title, @Param("longuid") Long longuid);

    Long listPersonalCount(@Param("year") Long year, @Param("month") Long month,
                           @Param("columnName") String columnName,
                           @Param("title") String title, @Param("longuid") Long longuid);
}
