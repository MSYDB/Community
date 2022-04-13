package com.community.admin.wholesituation.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.wholesituation.entity.SysUser;

import java.util.List;

/**
 * @author qinmiao
 * @interfaceName SysUserService
 * @description TODO
 * @date 2021.10.31 21:10
 */
public interface SysUserService extends IService<SysUser> {
    List<String> findPermissions(Long id);
}
