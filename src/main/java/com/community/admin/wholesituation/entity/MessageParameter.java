package com.community.admin.wholesituation.entity;

import lombok.Data;

/**
 * @Classname MessageParameter
 * @Description 短信验证码实体
 * @Date 2021/10/10 16:00
 * @Created by thx
 */
@Data
public class MessageParameter {
    private int appId = 1400569316;

    private String appKey = "bd9c5ed23c04519942538912a568405b";

    private String[] phoneNumbers;

    private int templateId = 1112897;

    private String smsSign = "她与虚空皆是空";
}
