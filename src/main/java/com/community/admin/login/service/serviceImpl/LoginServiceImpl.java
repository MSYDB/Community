package com.community.admin.login.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.login.mapper.LoginMapper;
import com.community.admin.login.service.LoginService;
import com.community.admin.personalmessage.mapper.PersonalMapper;
import com.community.admin.util.Md5Util;
import com.community.admin.util.RedisUtil;
import com.community.admin.util.StringUtil;
import com.community.admin.wholesituation.entity.SysUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname LoginServiceImpl
 * @Description 登录
 * @Date 2021/10/12 18:18
 * @Created by thx
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, SysUser> implements LoginService {
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private PersonalMapper personalMapper;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 登录接口
     * 首先判断数据库是否存在 userName，如果不存在直接返回
     * 存在以后再判断密码是否正确，如果不正确返回
     * 如果正确，生成 token 返回
     */
    @Override
    public ResultJson<SysUser> loginByPassword(String userName, String password) {
        if (StringUtil.isOrNotEmpty(password)) {
            return ResultJson.failure("密码不能为空", null);
        }
        List<SysUser> user = loginMapper.judgeIsOrNotExists(userName);
        if (user.size() == 0) {
            return ResultJson.failure("用户不存在", null);
        }
        if (!Md5Util.encode(password).equals(user.get(0).getPassword())) {
            return ResultJson.failure("密码不正确", null);
        }
        user.get(0).setAuthList(loginMapper.setAuthList(user.get(0).getId()));
        return ResultJson.success("登陆成功", user.get(0));
    }

    /**
     * 通过短信验证码登录，先判断 code 或者 phone 是否为空
     * 判断 用户是否存在（手机号是否在数据库中存储着呢）
     * 如果不存在，判断 code 是否与 redis 的一致，不一致返回
     * 一致确定登录
     */
    @Override
    public ResultJson<SysUser> loginByPhone(String phone, String code) {
        SysUser user = loginMapper.judgeIsOrNotExistsPhone(phone);
        if (user == null) {
            return ResultJson.failure("该手机号未注册，请先注册", null);
        }
        if (!code.equals(redisUtil.get(phone))) {
            return ResultJson.failure("验证码错误，请重新输入", null);
        }
        user.setAuthList(loginMapper.setAuthList(user.getId()));
        return ResultJson.success("登陆成功", user);
    }

    @Override
    @Transactional
    public ResultJson register(SysUser user, String code) {
        if (redisUtil.get(user.getPhone()) == null) {
            return ResultJson.failure("验证码不存在", null);
        }
        if (!redisUtil.get(user.getPhone()).equals(code)) {
            return ResultJson.failure("验证码错误，请重新输入", null);
        }
        if (StringUtil.isOrNotEmpty(user.getPassword())) {
            return ResultJson.failure("密码不能为空", null);
        }
        List<SysUser> object = loginMapper.judgeIsOrNotExistsUser(user);
        if (object.size() != 0) {
            for (SysUser sysUser : object) {
                if (sysUser.getUserName().equals(user.getUserName())) {
                    return ResultJson.failure("用户名已经存在，请重新输入", null);
                }
                if (sysUser.getPhone().equals(user.getPhone())) {
                    return ResultJson.failure("电话已经被注册，请重新输入", null);
                }
                if (sysUser.getEmail().equals(user.getEmail())) {
                    return ResultJson.failure("邮箱已经被注册，请重新输入", null);
                }
            }
        } else {
            String password = user.getPassword();
            user.setPassword(Md5Util.encode(password));
            loginMapper.insert(user);
            return ResultJson.success("注册成功！请登录", null);
        }
        return null;
    }

    /**
     * 忘记密码
     * 1、判断电话是否存在
     * 2、发送短信验证码
     * 3、设置新密码
     */
    @Override
    @Transactional
    public ResultJson forgetPassword(String phone, String code, String password) {
        SysUser user = loginMapper.judgeIsOrNotExistsPhone(phone);
        if (user == null) {
            return ResultJson.failure("电话号码不存在，请重新输入", null);
        }
        if (redisUtil.get(phone) == null) {
            return ResultJson.failure("验证码不存在，请先获取验证码", null);
        }
        if (!redisUtil.get(phone).equals(code)) {
            return ResultJson.failure("验证码不正确，请重新输入", null);
        }
        int row = personalMapper.updatePassword(user.getId(), password);
        if (row != GlobalConstant.AFFECT_ROWS_SINGLE) {
            return ResultJson.failure("忘记密码失败，请重试", null);
        }
        return ResultJson.success("忘记密码成功！", null);
    }

    /**
     * 设置角色的权限字符串
     */
    public List<String> setAuthList(Long id) {
        List<String> authLists = loginMapper.setAuthList(id);
        return authLists;
    }

}
