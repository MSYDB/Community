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
 * @Classname SysOperateLog
 * @Description 操作日志信息
 * @Date 2021/10/12 16:16
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(pattern = "yyyy-mm-dd hh:MM:ss")
public class SysOperateLog {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:MM:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date operateTime;
    //    操作模块
    private String operateModule;
    //    操作类型
    private Integer operateType;
    private String operateName;


    public SysOperateLog(Long userId, String operateModule, Integer operateType, String operateName) {
        this.userId = userId;
        this.operateModule = operateModule;
        this.operateType = operateType;
        this.operateName = operateName;
    }
}
