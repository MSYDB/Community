package com.community.admin.wholesituation.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @Classname SysUser
 * @Description 用户实体类
 * @Date 2021/10/10 14:53
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    private String password;

    private String nickName;

    private String realName;

    private Integer sex;

    private Integer age;

    private String headPhoto;
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;

    private Integer vipStatus;

    private Integer studyMoney;

    private Integer studyIntegration;
    private Integer improveInformation;
    //  自动填充内容
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(exist = false)
    // 权限字符串
    private List<String> authList;
    //  令牌
    @TableField(exist = false)
    private String token;


}
