package com.community.admin.wholesituation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.wholesituation.entity.SysLoginLog;
import com.community.admin.wholesituation.entity.SysOperateLog;

/**
 * @Classname LogService
 * @Description 日志
 * @Date 2021/10/14 19:27
 * @Created by thx
 */
public interface LogService extends IService<SysLoginLog> {
    Integer insertOperateLog(SysOperateLog operateLog);
}
