package com.community.admin.common.constant;

/**
 * @Classname Constant
 * @Description 常量类
 * @Date 2021/10/10 14:32
 * @Created by thx
 */
public class Constant {
    /**
     * 返回状态码
     * 200 : 成功
     * 500 : 失败
     */
    public static final Integer SUCCESS_CODE = 200;

    public static final Integer FAILURE_CODE = 500;

    public static final String SUCCESS_MESSAGE = "操作成功";

    public static final String FAILURE_MESSAGE = "操作失败";

    /**
     * 性别：
     * 0: 男
     * 1: 女
     */
    public static final int MEN = 0;
    public static final int WOMEN = 1;

    /**
     * 是否完善个人信息
     */
    public static final int IMPROVE_INFORMATION_COMPLETED = 1;
    //    未完善
    public static final int IMPROVE_INFORMATION_UNCOMPLETED = 0;


}
