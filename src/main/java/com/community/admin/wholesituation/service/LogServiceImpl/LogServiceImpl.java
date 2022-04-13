package com.community.admin.wholesituation.service.LogServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.wholesituation.entity.SysLoginLog;
import com.community.admin.wholesituation.entity.SysOperateLog;
import com.community.admin.wholesituation.mapper.LogMapper;
import com.community.admin.wholesituation.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname LogServiceImpl
 * @Description 日志
 * @Date 2021/10/14 19:28
 * @Created by thx
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, SysLoginLog> implements LogService {
    @Resource
    private LogMapper logMapper;

    @Override
    public Integer insertOperateLog(SysOperateLog operateLog) {
        return logMapper.insertOperateLog(operateLog);
    }
}
