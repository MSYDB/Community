package com.community.admin.personalmessage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.wholesituation.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Classname PersonalMapper
 * @Description 个人中心
 * @Date 2021/10/14 9:55
 * @Created by thx
 */
public interface PersonalMapper extends BaseMapper<SysUser> {

    int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    int updateHeadPhoto(@Param("id") Long id, @Param("photo") String photo);
}
