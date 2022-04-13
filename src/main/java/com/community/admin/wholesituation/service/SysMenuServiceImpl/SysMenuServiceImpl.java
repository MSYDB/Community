package com.community.admin.wholesituation.service.SysMenuServiceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.wholesituation.entity.SysMenu;
import com.community.admin.wholesituation.mapper.SysMenuMapper;
import com.community.admin.wholesituation.service.SysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: SysMenuServiceImpl
 * @description:
 * @author: lxt
 * @create: 2021-09-15
 **/
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {


    /**
     * 获取树形的菜单
     *
     * @return
     */
    @Override
    public List<SysMenu> listSysMenuWithTree() {
        // 获取所有菜单
        List<SysMenu> sysMenuList = list(new LambdaQueryWrapper<SysMenu>()
                .eq(SysMenu::getMenuType, GlobalConstant.MENU_TYPE_ME)
        );
        // 一级菜单  =>  jdk8 流操作
        List<SysMenu> firstMenuList = sysMenuList.stream()
                // 过滤出一级菜单
                .filter(s -> s.getMenuParentId().equals(GlobalConstant.MENU_TOP))
                // 返回list集合形式
                .collect(Collectors.toList());
        // 遍历一级菜单 构造菜单树
        for (SysMenu sysMenu : firstMenuList) {
            getChildrens(sysMenu, sysMenuList);
        }
        return firstMenuList;
    }

    /**
     * 获取子菜单
     *
     * @param sysMenu
     * @param sysMenuList
     */
    private SysMenu getChildrens(SysMenu sysMenu, List<SysMenu> sysMenuList) {
        for (SysMenu menu : sysMenuList) {
            if (menu.getMenuParentId().equals(sysMenu.getId())) {
                sysMenu.getChildrenList().add(getChildrens(menu, sysMenuList));
            }
        }
        return sysMenu;
    }
}
