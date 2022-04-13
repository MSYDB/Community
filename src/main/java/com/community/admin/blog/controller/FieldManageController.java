package com.community.admin.blog.controller;

import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.blog.service.BlogFieldService;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description 领域管理
 * Author  流星
 * Date 2021/10/15 21:45
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "blog")
public class FieldManageController {

    @Resource
    private BlogFieldService fieldService;

    @SelfLog(module = "blog", type = GlobalConstant.INSERT, name = "添加领域")
    @AuthString("insertField")
    @RequestMapping("insertField")
    public ResultJson insertField(String fieldName) {
        return fieldService.insertField(fieldName);
    }


    @SelfLog(module = "blog", type = GlobalConstant.DELETE, name = "删除领域")
    @AuthString("deleteField")
    @RequestMapping("deleteField")
    public ResultJson deleteField(Long id) {
        if (fieldService.removeById(id)) {
            return ResultJson.success("删除成功！", null);
        } else {
            return ResultJson.failure("删除失败！", null);
        }
    }

    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "展示领域")
    @AuthString("listAllField")
    @RequestMapping("listAllField")
    public ResultJson displayField(Integer pageNo, Integer pageSize, String fieldName) {
        return fieldService.displayField(pageNo, pageSize, fieldName);
    }
}
