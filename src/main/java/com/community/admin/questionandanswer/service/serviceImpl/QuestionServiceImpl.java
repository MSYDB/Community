package com.community.admin.questionandanswer.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.questionandanswer.entity.Question;
import com.community.admin.questionandanswer.mapper.AnswerMapper;
import com.community.admin.questionandanswer.mapper.QuestionMapper;
import com.community.admin.questionandanswer.service.QuestionService;
import com.community.admin.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @ClassName QuestionServiceImpl
 * @Description TODO
 * @Author 提问
 * @Date 2021/10/13 12:25
 * @Version 1.0
 **/
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Override
    @Transactional
    public PageWeb listAll(Long pageNo, Long pageSize, String userName, String questionContent,
                           String createBeginTime, String createEndTime) {
        if (pageNo < 1) {
            pageNo = 1L;
        }
        List<Map<String, Object>> list = questionMapper.listAll((pageNo - 1) * pageSize, pageSize, userName,
                questionContent, createBeginTime, createEndTime);
        PageWeb pageInfo = new PageWeb<>();
        pageInfo.setRecords(list);
        pageInfo.setPageNo(pageNo);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotal(questionMapper.listAllCount());
        Long Pages;
        if (questionMapper.listAllCount() % pageSize == 0) {
            Pages = questionMapper.listAllCount() / pageSize;
        } else {
            Pages = questionMapper.listAllCount() / pageSize + 1;
        }
        pageInfo.setTotalPages(Pages);
        return pageInfo;
    }


    @Override
    @Transactional
    public ResultJson insert(Question question) {
        String token = httpServletRequest.getHeader("token");
        question.setQuestionUserId(Long.parseLong(JwtUtil.getAudience(token)));
        question.setStatus(Integer.valueOf(0));
        question.setQuestionPageviewCount(Integer.valueOf(0));
        baseMapper.insert(question);
        return ResultJson.success();
    }

    @Override
    @Transactional
    public ResultJson delete(@RequestParam List<Long> idList) {
        baseMapper.deleteBatchIds(idList);
        for (Long qId : idList) {
            if (questionMapper.selectAnswer(qId) != null) {
                for (Long aId : questionMapper.selectAnswer(qId)) {
                    answerMapper.approveId(aId);
                }
            }
            questionMapper.questionId(qId);
        }
        return ResultJson.success();
    }

    @Override
    @Transactional
    public ResultJson update(Question question) {
        baseMapper.updateById(question);
        return ResultJson.success();
    }

    @Override
    @Transactional
    public ResultJson information(Long id) {
        Question question = baseMapper.selectById(id);
        question.setQuestionPageviewCount(question.getQuestionPageviewCount() + 1);
        baseMapper.updateById(question);
        return ResultJson.success(questionMapper.selectById(id));
    }

    public ResultJson top(Long id) {
        Question question = baseMapper.selectById(id);
        if (question.getTopFlag() == 1) {
            question.setTopFlag(0);
        } else {
            question.setTopFlag(1);
        }
        baseMapper.updateById(question);
        return ResultJson.success();
    }

    @Override
    @Transactional
    public ResultJson answerCount(Long qId) {
        return ResultJson.success(questionMapper.answerCount(qId));
    }

}
