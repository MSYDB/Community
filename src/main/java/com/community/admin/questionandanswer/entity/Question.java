package com.community.admin.questionandanswer.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName Question
 * @Description TODO
 * @Author 提问
 * @Date 2021/10/13 12:13
 * @Version 1.0
 **/
@Data
@TableName("question")
public class Question {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long questionUserId;
    private String questionContent;
    private Integer status;
    private String reason;
    private Integer topFlag;

    private Integer questionPageviewCount;

    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
