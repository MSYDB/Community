package com.community.admin.questionandanswer.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.community.admin.questionandanswer.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName QuestionMapper
 * @Description TODO
 * @Author 提问
 * @Date 2021/10/13 12:14
 * @Version 1.0
 **/
public interface QuestionMapper extends BaseMapper<Question> {

    List<Map<String, Object>> listAll(@Param("pageNo") Long pageNo, @Param("pageSize") Long pageSize,
                                      @Param("userName") String userName,
                                      @Param("questionContent") String questionContent,
                                      @Param("createBeginTime") String createBeginTime,
                                      @Param("createEndTime") String createEndTime);

    Long listAllCount();

    void questionId(@Param("qId") Long qId);

    List<Long> selectAnswer(@Param("qId") Long qId);

    Long answerCount(@Param("qId") Long qId);
}
