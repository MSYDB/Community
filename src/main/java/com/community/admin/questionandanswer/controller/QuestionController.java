package com.community.admin.questionandanswer.controller;

import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.questionandanswer.entity.Question;
import com.community.admin.questionandanswer.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName QuestionController
 * @Description TODO
 * @Author 提问
 * @Date 2021/10/13 12:05
 * @Version 1.0
 **/
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @SelfLog(module = "file", name = "查看问题")
    @AuthString("question:listAll")
    @RequestMapping("listAll")
    public PageWeb listAll(@RequestBody Long pageNo, Long pageSize, String userName, String questionContent,
                           String createBeginTime, String createEndTime) {
        return questionService.listAll(pageNo, pageSize, userName, questionContent,
                createBeginTime, createEndTime);
    }

    @SelfLog(module = "file", name = "提出问题")
    @AuthString("question:insert")
    @RequestMapping("insert")
    public ResultJson insert(@RequestBody Question question) {
        return questionService.insert(question);
    }

    @SelfLog(module = "file", name = "删除问题")
    @AuthString("question:delete")
    @RequestMapping("delete")
    public ResultJson delete(@RequestBody @RequestParam List<Long> idList) {
        return questionService.delete(idList);
    }

    @SelfLog(module = "file", name = "修改问题")
    @AuthString("question:update")
    @RequestMapping("update")
    public ResultJson update(@RequestBody Question question) {
        return questionService.update(question);
    }

    @SelfLog(module = "file", name = "查看问题详情")
    @AuthString("question:information")
    @RequestMapping("information")
    public ResultJson information(@RequestBody Long id) {
        return questionService.information(id);
    }

    @SelfLog(module = "file", name = "置顶")
    @AuthString("question:top")
    @RequestMapping("top")
    public ResultJson top(@RequestBody Long id) {
        return questionService.top(id);
    }

    @SelfLog(module = "file", name = "问题审核")
    @AuthString("question:auditing")
    @RequestMapping("auditing")
    public ResultJson auditing(@RequestBody Question question) {
        return ResultJson.success(questionService.updateById(question));
    }

}
