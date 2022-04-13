package com.community.admin.personalmessage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.wholesituation.entity.SysUser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname PersonalService
 * @Description 个人中心 service
 * @Date 2021/10/14 9:54
 * @Created by thx
 */
public interface PersonalService extends IService<SysUser> {
    ResultJson updatePersonalMessage(SysUser user, String code);

    ResultJson updatePassword(String oldPassword, String newPassword, String newPasswordAgain, HttpServletRequest request);

    ResultJson checkPersonalMessage(HttpServletRequest request);

    ResultJson uploadMsg(HttpServletRequest request, MultipartFile photo);
}
