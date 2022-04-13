package com.community.admin.questionandanswer.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.web.ResultJson;
import com.community.admin.questionandanswer.entity.Answer;
import com.community.admin.questionandanswer.mapper.AnswerMapper;
import com.community.admin.questionandanswer.mapper.QuestionMapper;
import com.community.admin.questionandanswer.service.AnswerService;
import com.community.admin.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName AnswerServiceImpl
 * @Description TODO
 * @Author 回答
 * @Date 2021/10/13 12:25
 * @Version 1.0
 **/
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private HttpServletRequest httpServletRequest;

    HashSet<Answer> LsAnswer = new HashSet<>();

    @Override
    @Transactional
    public HashSet<Answer> listAnswerTree(Long id, Long questionId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("question_id", questionId);
        List<Answer> AnswerList = baseMapper.selectList(queryWrapper);
        //二级评论
        queryWrapper.eq("parent_id", id);
        List<Answer> fAnswerList = baseMapper.selectList(queryWrapper);
        LsAnswer.addAll(fAnswerList);
        for (Answer answer : fAnswerList) {
            getChildren(answer, AnswerList, LsAnswer);
            listAnswerTree(answer.getId(), questionId);
        }
        return LsAnswer;
    }

    private HashSet<Answer> getChildren(Answer answer, List<Answer> answerList, HashSet<Answer> LsAnswer) {
        for (Answer aList : answerList) {
            if (aList.getParentId().equals(answer.getId())) {
                LsAnswer.add(aList);
            }
        }
        return LsAnswer;
    }


    @Override
    @Transactional
    public ResultJson insert(Answer answer) {
        String token = httpServletRequest.getHeader("token");
        answer.setUserId(Long.parseLong(JwtUtil.getAudience(token)));
        if (answer.getParentId() == null) {
            answer.setParentId(Long.valueOf(0));
        }
        answerMapper.insert(answer);
        return ResultJson.success();
    }

    HashSet<Long> Listarrrays = new HashSet<>();

    @Override
    @Transactional
    public ResultJson delete(Long id) {
        if (baseMapper.selectById(id) == null) {
            return null;
        }
        Answer answer = baseMapper.selectById(id);
        Set<Long> idList = listList(id, answer.getQuestionId());
        for (Long ids : idList) {
            answerMapper.approveId(ids);
        }
        baseMapper.deleteBatchIds(idList);
        return ResultJson.success();
    }

    public HashSet<Long> listList(Long id, Long questionId) {

        List<Long> AList = questionMapper.selectAnswer(questionId);
        List<Answer> answerList = baseMapper.selectBatchIds(AList);
        List<Long> fAnswerList = answerMapper.selectParentId(id);
        Listarrrays.add(id);
        Listarrrays.addAll(fAnswerList);
        for (Long ids : fAnswerList) {
            Children(ids, answerList, Listarrrays);
            listList(ids, questionId);
        }
        return Listarrrays;
    }

    private HashSet<Long> Children(Long ids, List<Answer> answerList, HashSet<Long> Listarrrays) {
        for (Answer aList : answerList) {
            if (aList.getParentId().equals(ids)) {
                Listarrrays.add(aList.getId());
            }
        }
        return Listarrrays;
    }


    public ResultJson thumbUp(Long id) {
        String token = httpServletRequest.getHeader("token");
        Long userId = Long.parseLong(JwtUtil.getAudience(token));
        if (answerMapper.selectApprove(userId, id).size() > 0) {
            answerMapper.approveId(id);
        } else {
            answerMapper.insertApprove(userId, id);
        }
        return ResultJson.success();
    }

    @Override
    @Transactional
    public List<Answer> selectFirst(Long questionId) {
        return answerMapper.selectFirst(questionId);
    }

}
