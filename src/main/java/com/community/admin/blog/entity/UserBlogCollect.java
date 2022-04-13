package com.community.admin.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 用户收藏记录表(UserBlogCollect)表实体类
 *
 * @author lx
 * @since 2021-10-16 15:57:23
 */
@Data
public class UserBlogCollect {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_id")
    private Long userId;
    @TableField(value = "blog_id")
    private Long blogId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}