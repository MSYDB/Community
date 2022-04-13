package com.community.admin.util;

/**
 * @Classname StringUtil
 * @Description 字符串处理工具类
 * @Date 2021/10/10 15:55
 * @Created by thx
 */
public class StringUtil {
    public static boolean isOrNotEmpty(String s) {
        if (s == null || s.trim().equals("")) {
            return true;
        }
        return false;
    }
}
