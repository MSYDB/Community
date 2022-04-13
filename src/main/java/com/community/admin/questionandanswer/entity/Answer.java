package com.community.admin.questionandanswer.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassName Answer
 * @Description TODO
 * @Author 回答
 * @Date 2021/10/13 12:13
 * @Version 1.0
 **/
@Data
@TableName("question_answer")
public class Answer {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long questionId;
    private Long userId;
    private String answerContent;
    private Long parentId;

    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
