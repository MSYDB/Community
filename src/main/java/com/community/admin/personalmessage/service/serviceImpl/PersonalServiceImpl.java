package com.community.admin.personalmessage.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.constant.Constant;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.personalmessage.mapper.PersonalMapper;
import com.community.admin.personalmessage.service.PersonalService;
import com.community.admin.util.FileUtil;
import com.community.admin.util.Md5Util;
import com.community.admin.util.RedisUtil;
import com.community.admin.wholesituation.entity.SysUser;
import com.community.admin.wholesituation.entity.vo.UserVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Classname PersonalServiceImpl
 * @Description 个人中心
 * @Date 2021/10/14 9:55
 * @Created by thx
 */
@Service
@CacheConfig(cacheNames = {"personalService"})
public class PersonalServiceImpl extends ServiceImpl<PersonalMapper, SysUser> implements PersonalService {
    @Resource
    private PersonalMapper personalMapper;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 完善个人信息
     *
     * @param user
     * @return
     */

    @Override
    @Transactional
    public ResultJson updatePersonalMessage(SysUser user, String code) {
        if (redisUtil.get(user.getEmail()) == null) {
            return ResultJson.failure("验证码不存在！");
        }
        if (!redisUtil.get(user.getEmail()).equals(code)) {
            return ResultJson.failure("验证码错误，请重新输入");
        }
        user.setImproveInformation(Constant.IMPROVE_INFORMATION_COMPLETED);
        int affectRows = personalMapper.updateById(user);
        if (affectRows != GlobalConstant.AFFECT_ROWS_SINGLE) {
            return ResultJson.failure("修改失败，请重试", null);
        }
        return ResultJson.success("修改个人信息成功！", null);
    }

    /**
     * 修改密码，删除后清空 redis 的 token 信息并返回
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Override
    @Transactional
    public ResultJson updatePassword(String oldPassword, String newPassword, String newPasswordAgain,
                                     HttpServletRequest request) {
        if (!newPassword.equals(newPasswordAgain)) {
            return ResultJson.failure("两次密码输入不一致", null);
        }
        if (newPassword.equals(oldPassword)) {
            return ResultJson.failure("新老密码不能一样", null);
        }
        SysUser user = (SysUser) redisUtil.get(request.getHeader(GlobalConstant.TOKEN));
        if (!Md5Util.encode(oldPassword).equals(user.getPassword())) {
            return ResultJson.failure("旧密码输入错误", null);
        }
        personalMapper.updatePassword(user.getId(), Md5Util.encode(newPassword));
        redisUtil.del(request.getHeader(GlobalConstant.TOKEN));
        return ResultJson.success("修改密码成功，请重新登陆");
    }

    /**
     * 查看个人信息
     *
     * @param request
     * @return
     */
    @Override
    public ResultJson checkPersonalMessage(HttpServletRequest request) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader(GlobalConstant.TOKEN));
        UserVo userVo = new UserVo(user);
        return ResultJson.success(userVo);
    }

    /**
     * 上传头像
     *
     * @param request
     * @param photo
     * @return
     */
    @Override
    @Transactional
    public ResultJson uploadMsg(HttpServletRequest request, MultipartFile photo) {
        SysUser user = (SysUser) redisUtil.get(request.getHeader(GlobalConstant.TOKEN));
        personalMapper.updateHeadPhoto(user.getId(), FileUtil.uploadFile(photo));
        return ResultJson.success("上传头像成功", null);
    }


}
