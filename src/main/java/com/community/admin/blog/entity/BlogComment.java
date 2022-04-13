package com.community.admin.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 博客评论表(BlogComment)表实体类
 *
 * @author lx
 * @since 2021-10-15 00:28:18
 */
@Data
public class BlogComment {
    @TableId(type = IdType.AUTO)
    private Long id;
    //博客id
    @TableField(value = "blog_id")
    private Long blogId;
    //评论者用户id
    @TableField(value = "comment_user_id")
    private Long commentUserId;
    //评论内容
    @TableField(value = "comment_content")
    private String commentContent;
    //被回复的评论id
    @TableField(value = "parent_id")
    private Long parentId;
    //回答被点赞次数
    @TableField(value = "approve_count")
    private Integer approveCount;
    //创建时间
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    //更新时间
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    private Set<BlogComment> commentList = new HashSet<>();
    @TableField(exist = false)
    private String nickName;
    @TableField(exist = false)
    private String headPhoto;

}