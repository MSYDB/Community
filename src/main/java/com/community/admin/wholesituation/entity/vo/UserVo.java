package com.community.admin.wholesituation.entity.vo;

import com.community.admin.wholesituation.entity.SysUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Classname UserVo
 * @Description 查看个人信息
 * @Date 2021/10/14 18:45
 * @Created by thx
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public class UserVo {
    private Long id;
    private String userName;
    private String nickName;
    private String realName;
    private Integer sex;
    private Integer age;
    private String headPhoto;
    private String phone;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;
    private Integer vipStatus;
    private Integer studyMoney;
    private Integer studyIntegration;
    private Integer improveInformation;

    public UserVo(SysUser user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.nickName = user.getNickName();
        this.realName = user.getRealName();
        this.sex = user.getSex();
        this.age = user.getAge();
        this.headPhoto = user.getHeadPhoto();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.registerTime = user.getRegisterTime();
        this.vipStatus = user.getVipStatus();
        this.studyMoney = user.getStudyMoney();
        this.studyIntegration = user.getStudyIntegration();
        this.improveInformation = user.getImproveInformation();
    }
}
