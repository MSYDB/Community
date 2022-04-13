package com.community.admin.wholesituation.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @Classname SysMenu
 * @Description 菜单信息
 * @Date 2021/10/12 16:16
 * @Created by thx
 */
@Data
@TableName("sys_menu")
@Accessors(chain = true)
public class SysMenu {
    private Long id;
    //资源名称
    private String menuName;
    //权限
    private String menuPermission;
    //对应url
    private String menuPath;
    //父级菜单
    private Long menuParentId;
    //菜单类型0菜单，1栏目类型
    private Integer menuType;
    //排序字段：值越小 顺序越靠前
    private Integer orderNum;
    //创建时间
    private LocalDateTime createTime;
    //最后更新时间
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private Set<SysMenu> childrenList = new HashSet<>();
}
