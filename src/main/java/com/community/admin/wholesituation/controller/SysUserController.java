package com.community.admin.wholesituation.controller;

import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.wholesituation.service.SysUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinmiao
 * @className SysUserController
 * @description TODO
 * @date 2021.10.31 15:48
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "user")
public class SysUserController {
    @Resource
    SysUserService sysUserService;

    @RequestMapping("findPermissions")
    public ResultJson findPermissions(Long id) {
        List<String> AuthList = sysUserService.findPermissions(id);
        return ResultJson.success(AuthList);
    }
}
