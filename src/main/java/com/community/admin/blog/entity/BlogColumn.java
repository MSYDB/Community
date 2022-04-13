package com.community.admin.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 博客栏目表(BlogColumn)表实体类
 *
 * @author lx
 * @since 2021-10-20 14:34:27
 */
@Data
public class BlogColumn {
    @TableId(type = IdType.AUTO)
    private Long id;
    //博客栏目名称
    @TableField(value = "column_name")
    private String columnName;
    //栏目描述
    private String description;
    //创建时间
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //用户id
    @TableField(value = "user_id")
    private Long userId;

}