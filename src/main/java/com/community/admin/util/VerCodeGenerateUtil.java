package com.community.admin.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @Classname VerCodeGenerateUtil
 * @Description 随机验证码生成工具类
 * @Date 2021/10/10 15:56
 * @Created by thx
 */
public class VerCodeGenerateUtil {
    private static final String SYMBOLS_PHONE = "0123456789";//ABCDEFGHIGKLMNOPQRSTUVWXYZ
    private static final String SYMBOLS_EMAIL = "0123456789ABCDEFGHIGKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new SecureRandom();
    //    生成 6 位数的随机数字(用于短信验证码)

    public static String generateMessageVerCode() {
        char[] numbers = new char[6];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = SYMBOLS_PHONE.charAt(RANDOM.nextInt(SYMBOLS_PHONE.length()));
        }
        return new String(numbers);
    }

    public static String generateEmailVerCode() {
        char[] numbers = new char[6];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = SYMBOLS_EMAIL.charAt(RANDOM.nextInt(SYMBOLS_EMAIL.length()));
        }
        return new String(numbers);
    }

    public static void main(String[] args) {

        System.out.println(generateMessageVerCode());
        System.out.println(generateEmailVerCode());

    }
}
