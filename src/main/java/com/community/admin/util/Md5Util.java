package com.community.admin.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @Classname Md5Util
 * @Description Md5 加密
 * @Date 2021/10/13 16:23
 * @Created by thx
 */
public class Md5Util {
    public static String encode(String msg) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            return Base64.getEncoder().encodeToString(messageDigest.digest(msg.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void main(String[] args) {
//        System.out.println(Md5Util.encode("123"));
    }
    //2ETIPWZa1R4+LJRW3nahQw==2ETIPWZa1R4+LJRW3nahQw==
    //ICy5YqxZB1uWSwcVLSNLcA==
    //ICy5YqxZB1uWSwcVLSNLcA==全局


}
