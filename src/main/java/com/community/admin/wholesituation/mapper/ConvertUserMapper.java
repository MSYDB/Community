package com.community.admin.wholesituation.mapper;

import com.community.admin.wholesituation.entity.SysUser;
import com.community.admin.wholesituation.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Classname ConvertUserMapper
 * @Description 实体类转换接口
 * @Date 2021/10/14 20:49
 * @Created by thx
 */
@Mapper
public interface ConvertUserMapper {
    ConvertUserMapper INSTANCE = Mappers.getMapper(ConvertUserMapper.class);

    UserVo convert(SysUser user);
}
