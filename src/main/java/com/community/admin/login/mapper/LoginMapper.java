package com.community.admin.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.wholesituation.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Classname LoginMapper
 * @Description 登录
 * @Date 2021/10/12 18:18
 * @Created by thx
 */
public interface LoginMapper extends BaseMapper<SysUser> {
    List<SysUser> judgeIsOrNotExists(@Param("userName") String userName);

    SysUser judgeIsOrNotExistsPhone(@Param("phone") String phone);

    List<SysUser> judgeIsOrNotExistsUser(@Param("user") SysUser user);

    List<String> setAuthList(@Param("id") Long id);

}
