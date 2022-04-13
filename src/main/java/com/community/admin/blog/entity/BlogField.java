package com.community.admin.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 博客领域表(BlogField)表实体类
 *
 * @author lx
 * @since 2021-10-15 21:42:39
 */
@Data
public class BlogField {
    @TableId(type = IdType.AUTO)
    private Long id;
    //领域名称
    @TableField(value = "field_name")
    private String fieldName;
    //描述
    private String description;
    //创建时间
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}