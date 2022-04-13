package com.community.admin.wholesituation.service.SysUserServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.wholesituation.entity.SysUser;
import com.community.admin.wholesituation.mapper.SysUserMapper;
import com.community.admin.wholesituation.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author qinmiao
 * @className SysUserServiceImpl
 * @description TODO
 * @date 2021.10.31 21:10
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Override
    public List<String> findPermissions(Long id) {
        return sysUserMapper.findPermissions(id);
    }
}
