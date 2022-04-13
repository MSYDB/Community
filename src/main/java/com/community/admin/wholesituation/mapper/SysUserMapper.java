package com.community.admin.wholesituation.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.wholesituation.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author qinmiao
 * @interfaceName SysUserMapper
 * @description TODO
 * @date 2021.10.31 15:59
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<String> findPermissions(@Param("id") Long id);
}
