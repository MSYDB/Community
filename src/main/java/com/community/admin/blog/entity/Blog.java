package com.community.admin.blog.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * TODO
 *
 * @author DB
 * <br>CreateDate 2021/10/13 22:48
 */
@Data
public class Blog {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(value = "author_id")
    private Long authorId;

    private String title;
    private String content;
    private int status;

    @TableField(value = "release_time", fill = FieldFill.DEFAULT)
    private Date releaseTime;

    @TableField(value = "pageview_count")
    private int pageViewCount;               //被浏览量

    @TableField(value = "approve_count")
    private int approveCount;                //被点赞量

    @TableField(value = "comment_count")
    private int commentCount;                //被评论量

    @TableField(value = "collect_count")
    private int collectCount;                //被收藏量

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "is_top")
    private int isTop;


    @TableField(exist = false)
    private String nickName;

    @TableField(exist = false)
    private String headPhoto;

}
