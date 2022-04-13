package com.community.admin.shop.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 商品评论表(GoodsComment)表实体类
 *
 * @author wangdaoren
 * @since 2021-10-23 16:24:56
 */
@Data
@SuppressWarnings("serial")
public class GoodsComment extends Model<GoodsComment> {

    @TableId(type = IdType.AUTO)
    private Long id;
    //商品id
    private Long goodsId;
    //评论者id
    private Long commentUserId;
    //评论内容
    private String commentContent;
    //被回复的评论id
    private Long parentId;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @TableField(exist = false)
    private Set<GoodsComment> childrenList = new HashSet<>();


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}