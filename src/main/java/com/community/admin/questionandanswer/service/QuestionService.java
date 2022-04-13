package com.community.admin.questionandanswer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.questionandanswer.entity.Question;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName QuestionService
 * @Description TODO
 * @Author 提问
 * @Date 2021/10/13 12:15
 * @Version 1.0
 **/
public interface QuestionService extends IService<Question> {

    PageWeb listAll(Long pageNo, Long pageSize, String userName, String questionContent,
                    String createBeginTime, String createEndTime);

    ResultJson insert(Question question);

    ResultJson delete(List<Long> idList);

    ResultJson update(Question question);

    ResultJson information(Long id);

    ResultJson top(Long id);

    ResultJson answerCount(@Param("qId") Long qId);

}
