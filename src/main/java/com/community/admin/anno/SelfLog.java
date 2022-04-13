package com.community.admin.anno;


import com.community.admin.common.constant.GlobalConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Classname SelfLog
 * @Description 切面注解
 * @Date 2021/10/10 14:44
 * @Created by thx
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SelfLog {
    /**
     * 操作类型 （默认是查询）
     */
    int type() default GlobalConstant.SELECT;

    /**
     * 操作模块
     *
     * @return
     */
    String module() default "";

    /**
     * 名称参数
     *
     * @return
     */
    String name() default "name";
}
