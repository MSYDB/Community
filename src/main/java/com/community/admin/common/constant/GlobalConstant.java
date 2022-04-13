package com.community.admin.common.constant;

/**
 * @Classname GlobalConstant
 * @Description 全局常量
 * @Date 2021/10/10 14:45
 * @Created by thx
 */
public class GlobalConstant {
    //    增
    public final static int INSERT = 0;
    //      删
    public static final int DELETE = 1;
    //    改
    public static final int UPDATE = 2;
    //    查
    public final static int SELECT = 3;
    //    接口统一路径
    public final static String SERVER_URL_PREFIX = "/admin/";
    //    token
    public final static String TOKEN = "token";
    //    五分钟过期时间
    public final static int EXPIRE_TIME_SECOND = 300;
    public final static int EXPIRE_TIME_MINUTE = 5;


    public final static int AFFECT_ROWS_SINGLE = 1;

    /**
     * 菜单类型：0  菜单  1  按钮
     */
    public final static int MENU_TYPE_ME = 0;
    public final static int MENU_TYPE_BT = 1;

    /**
     * 顶级菜单ID
     */
    public final static Long MENU_TOP = 1L;


}
