package com.community.admin.questionandanswer.controller;

import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.questionandanswer.entity.Answer;
import com.community.admin.questionandanswer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AnswerController
 * @Description TODO
 * @Author 回答
 * @Date 2021/10/13 12:06
 * @Version 1.0
 **/
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @SelfLog(module = "file", name = "查看一级评论")
    @AuthString("answer:listAll")
    @RequestMapping("listAll")
    public ResultJson listAll(@RequestBody Long questionId) {
        return ResultJson.success(answerService.selectFirst(questionId));
    }

    @SelfLog(module = "file", name = "查看评论树")
    @AuthString("answer:listAllTree")
    @RequestMapping("listAllTree")
    public ResultJson listAllTree(@RequestBody Long id, Long questionId) {
        return ResultJson.success(answerService.listAnswerTree(id, questionId));
    }

    @SelfLog(module = "file", name = "回答")
    @AuthString("answer:insert")
    @RequestMapping("insert")
    public ResultJson insert(@RequestBody Answer answer) {
        return answerService.insert(answer);
    }

    @SelfLog(module = "file", name = "删除回答")
    @AuthString("answer:delete")
    @RequestMapping("delete")
    public ResultJson delete(@RequestBody Long id) {
        return answerService.delete(id);
    }

    @SelfLog(module = "file", name = "点赞")
    @AuthString("answer:thumbUp")
    @RequestMapping("thumbUp")
    public ResultJson thumbUp(@RequestBody Long id) {
        return answerService.thumbUp(id);
    }


}
