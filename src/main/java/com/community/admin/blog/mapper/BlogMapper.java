package com.community.admin.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.blog.entity.Blog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BlogMapper extends BaseMapper<Blog> {

    void RefBlogField(@Param("blogId") Long blogId, @Param("idList") List<Long> idList);

    void delRefBlogField(Long blogId);

    void delRefBlogColumn(Long blogId);

    void RefBlogColumn(@Param("blogId") Long blogId, @Param("columnId") Long columnId);

    List<Long> filter(@Param("columnId") Long columnId);

    List<Blog> supportBlogList(Long uid);

    List<Blog> collectBlogList(Long uid);
}
