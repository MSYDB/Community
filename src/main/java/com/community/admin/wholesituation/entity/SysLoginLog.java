package com.community.admin.wholesituation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname SysLoginLog
 * @Description 登录日志信息
 * @Date 2021/10/12 16:16
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(pattern = "yyyy-mm-dd hh:MM:ss")
public class SysLoginLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-mm-dd hh:MM:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date loginTime;
    private String loginIp;

    public SysLoginLog(Long userId, String loginIp) {
        this.userId = userId;
        this.loginIp = loginIp;
    }
}
