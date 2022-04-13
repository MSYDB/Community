package com.community.admin.questionandanswer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.questionandanswer.entity.Answer;

import java.util.HashSet;
import java.util.List;


/**
 * @ClassName AnswerService
 * @Description TODO
 * @Author 回答
 * @Date 2021/10/13 12:15
 * @Version 1.0
 **/
public interface AnswerService extends IService<Answer> {

    HashSet<Answer> listAnswerTree(Long id, Long questionId);

    ResultJson insert(Answer answer);

    ResultJson delete(Long id);

    ResultJson thumbUp(Long id);

    List<Answer> selectFirst(Long questionId);

}
