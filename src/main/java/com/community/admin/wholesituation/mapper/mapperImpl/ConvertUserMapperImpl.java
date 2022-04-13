package com.community.admin.wholesituation.mapper.mapperImpl;

import com.community.admin.wholesituation.entity.SysUser;
import com.community.admin.wholesituation.entity.vo.UserVo;
import com.community.admin.wholesituation.mapper.ConvertUserMapper;

/**
 * @Classname ConvertUserMapperImpl
 * @Description 类转换实现类
 * @Date 2021/10/14 20:53
 * @Created by thx
 */
public class ConvertUserMapperImpl implements ConvertUserMapper {

    public ConvertUserMapperImpl() {
    }

    @Override
    public UserVo convert(SysUser user) {
        if (user == null) {
            return null;
        } else {
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setUserName(user.getUserName());
            userVo.setNickName(user.getNickName());
            userVo.setRealName(user.getRealName());
            userVo.setSex(user.getSex());
            userVo.setAge(user.getAge());
            userVo.setHeadPhoto(user.getHeadPhoto());
            userVo.setPhone(user.getPhone());
            userVo.setEmail(user.getEmail());
            userVo.setRegisterTime(user.getRegisterTime());
            userVo.setVipStatus(user.getVipStatus());
            userVo.setStudyMoney(user.getStudyMoney());
            userVo.setStudyIntegration(user.getStudyIntegration());
            userVo.setImproveInformation(user.getImproveInformation());
            return userVo;
        }
    }
}
