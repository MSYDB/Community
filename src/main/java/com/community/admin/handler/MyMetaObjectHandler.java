package com.community.admin.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 在插入的时候响应相关操作
     *
     * @param metaObject
     */
    @Override
    public void insertFill(org.apache.ibatis.reflection.MetaObject metaObject) {
        setFieldValByName("createTime", new Date(), metaObject);
        setFieldValByName("updateTime", new Date(), metaObject);
        setFieldValByName("loginTime", new Date(), metaObject);
        setFieldValByName("operateTime", new Date(), metaObject);
        setFieldValByName("registerTime", new Date(), metaObject);
    }

    /**
     * 在修改的时候响应相关操作
     *
     * @param metaObject
     */
    @Override
    public void updateFill(org.apache.ibatis.reflection.MetaObject metaObject) {
        setFieldValByName("updateTime", new Date(), metaObject);
    }
}
