package com.community.admin.login.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.wholesituation.entity.SysUser;

/**
 * @Classname LoginService
 * @Description 登录
 * @Date 2021/10/12 18:18
 * @Created by thx
 */
public interface LoginService extends IService<SysUser> {
    ResultJson<SysUser> loginByPassword(String userName, String password);

    ResultJson<SysUser> loginByPhone(String phone, String code);

    ResultJson register(SysUser user, String code);

    ResultJson forgetPassword(String phone, String code, String password);
}
