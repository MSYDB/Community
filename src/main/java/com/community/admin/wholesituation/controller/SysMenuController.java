package com.community.admin.wholesituation.controller;


import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.wholesituation.entity.SysMenu;
import com.community.admin.wholesituation.entity.vo.TreeNodeVo;
import com.community.admin.wholesituation.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @className: SysMenuController
 * @description: 菜单控制类
 * @author: lxt
 * @create: 2021-09-15
 **/
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "menu")
public class SysMenuController {


    @Autowired
    private SysMenuService sysMenuService;


    @RequestMapping("findNavTree")
    public ResultJson findNavTree() {
        List<SysMenu> sysMenuList = sysMenuService.listSysMenuWithTree();
        return ResultJson.success(TreeNodeVo.build(sysMenuList));
    }
}
