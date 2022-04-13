package com.community.admin.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname AuthString
 * @Description 权限字符串
 * @Date 2021/10/10 14:43
 * @Created by thx
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthString {
    String value() default "";
}
