package com.community.admin.wholesituation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.wholesituation.entity.SysLoginLog;
import com.community.admin.wholesituation.entity.SysOperateLog;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname LogMapper
 * @Description 日志操作
 * @Date 2021/10/14 19:26
 * @Created by thx
 */
public interface LogMapper extends BaseMapper<SysLoginLog> {

    Integer insertOperateLog(@Param("operateLog") SysOperateLog operateLog);
}
