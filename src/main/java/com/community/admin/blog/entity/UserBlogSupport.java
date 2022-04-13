package com.community.admin.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * 用户点赞记录表(UserBlogSupport)表实体类
 *
 * @author lx
 * @since 2021-10-16 15:58:07
 */
@Data
public class UserBlogSupport {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "user_id")
    private Long userId;
    @TableField(value = "blog_id")
    private Long blogId;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
}