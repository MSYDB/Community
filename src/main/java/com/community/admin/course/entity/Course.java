package com.community.admin.course.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
//徐明超

/**
 * 课程(Course)表实体类
 *
 * @author xmc
 * @since 2021-10-15 12:19:01
 */
@SuppressWarnings("serial")
@Data
public class Course extends Model<Course> {

    @TableId(type = IdType.AUTO)
    private Long id;
    //课程开设者id
    private Long foundUserId;
    //课程名称
    private String courseName;
    //课程类别id
    private Long courseTypeId;
    //图片
    private String imgPath;
    //课程描述
    private String description;
    //价格
    private Double price;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


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