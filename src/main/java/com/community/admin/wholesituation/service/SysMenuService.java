package com.community.admin.wholesituation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.wholesituation.entity.SysMenu;

import java.util.List;

public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> listSysMenuWithTree();
}
