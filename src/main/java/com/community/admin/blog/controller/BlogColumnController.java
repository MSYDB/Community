package com.community.admin.blog.controller;


import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.blog.entity.BlogColumn;
import com.community.admin.blog.service.BlogColumnService;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 博客栏目表(BlogColumn)表控制层
 *
 * @author lx
 * @since 2021-10-20 14:34:29
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "blog")
public class BlogColumnController {
    @Resource
    private BlogColumnService columnService;

    @SelfLog(module = "blog", type = GlobalConstant.INSERT, name = "添加专栏")
    @AuthString("insertColumn")
    @RequestMapping("insertColumn")
    public ResultJson insertColumn(BlogColumn blogColumn) {
        return columnService.insertColumn(blogColumn);
    }

    @SelfLog(module = "blog", type = GlobalConstant.DELETE, name = "删除专栏")
    @AuthString("deleteColumn")
    @RequestMapping("deleteColumn")
    public ResultJson deleteColumn(Long id) {
        if (columnService.removeById(id)) {
            return ResultJson.success("删除成功！", null);
        } else {
            return ResultJson.failure("删除失败！", null);
        }
    }

    @SelfLog(module = "blog", type = GlobalConstant.UPDATE, name = "修改专栏")
    @AuthString("updateColumn")
    @RequestMapping("updateColumn")
    public ResultJson updateColumn(BlogColumn blogColumn) {
        return columnService.updateColumn(blogColumn);
    }

    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "管理专栏")
    @AuthString("listColumnContent")
    @RequestMapping("listColumnContent")
    public ResultJson listColumnContent(Integer pageNo, Integer pageSize, Long columnId) {
        return columnService.listColumnContent(pageNo, pageSize, columnId);
    }

    @SelfLog(module = "blog", type = GlobalConstant.UPDATE, name = "专栏置顶")
    @AuthString("columnTop")
    @RequestMapping("columnTop")
    public ResultJson columnTop(BlogColumn blogColumn) {
        if (columnService.updateById(blogColumn)) {
            return ResultJson.success("置顶成功！", null);
        } else {
            return ResultJson.failure("置顶失败！", null);
        }
    }

    @SelfLog(module = "blog", type = GlobalConstant.SELECT, name = "展示专栏")
    @AuthString("listAllColumn")
    @RequestMapping("listAllColumn")
    public ResultJson listAllColumn(Integer pageNo, Integer pageSize) {
        return columnService.listAllColumn(pageNo, pageSize);
    }

}