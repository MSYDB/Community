package com.community.admin.wholesituation.entity.vo;


import com.community.admin.wholesituation.entity.SysMenu;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @className: TreeNode
 * @description:
 * @author: lxt
 * @create: 2021-09-15
 **/
@Data
public class TreeNodeVo {

    private Long id;
    private Long parentId;
    private String name;
    private String icon;
    private String url;
    private String perms;
    private Integer type;
    private Integer orderNum;
    private List<TreeNodeVo> children = new ArrayList<>();


    public static List<TreeNodeVo> build(List<SysMenu> sysMenuList) {
        List<TreeNodeVo> treeNodeList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            treeNodeList.add(build(sysMenu));
        }
        return treeNodeList;
    }

    public static TreeNodeVo build(SysMenu sysMenu) {
        TreeNodeVo treeNode = new TreeNodeVo(sysMenu.getId(), sysMenu.getMenuParentId()
                , sysMenu.getMenuName(), sysMenu.getMenuPath(), sysMenu.getMenuPermission(), sysMenu.getMenuType(), sysMenu.getOrderNum());
        Set<SysMenu> resourceList = sysMenu.getChildrenList();
        if (!CollectionUtils.isEmpty(resourceList)) {
            for (SysMenu SysMenu : resourceList) {
                treeNode.getChildren().add(build(SysMenu));
            }
        }
        return treeNode;
    }

    public TreeNodeVo(Long id, Long parentId, String name, String url, String perms, Integer type, Integer orderNum) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.url = url;
        this.perms = perms;
        this.type = type;
        this.orderNum = orderNum;
    }

}
