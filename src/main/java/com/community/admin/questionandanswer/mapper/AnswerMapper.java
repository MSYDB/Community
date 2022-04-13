package com.community.admin.questionandanswer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.questionandanswer.entity.Answer;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @ClassName AnswerMapper
 * @Description TODO
 * @Author 回答
 * @Date 2021/10/13 12:14
 * @Version 1.0
 **/
public interface AnswerMapper extends BaseMapper<Answer> {

    void parentId(@Param("pId") Long pId);

    void approveId(@Param("aId") Long aId);

    List<String> selectApprove(@Param("userId") Long userId, @Param("aId") Long aId);

    void insertApprove(@Param("userId") Long userId, @Param("aId") Long aId);

    List<Long> selectParentId(@Param("pId") Long pId);

    List<Answer> selectFirst(@Param("qId") Long qId);
}
