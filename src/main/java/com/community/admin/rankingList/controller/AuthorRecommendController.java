package com.community.admin.rankingList.controller;

import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.rankingList.service.AuthorRecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * TODO 作者推荐接口
 *
 * @author DB
 * <br>CreateDate 2021/10/14 20:29
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "authorRecommend")
public class AuthorRecommendController {
    @Autowired
    private AuthorRecommendService authorRecommendService;

    @RequestMapping("recommend")
    public ResultJson recommend(@NotNull Integer pageNo, @NotNull Integer pageSize,
                                @NotNull Long fieldId) {
        return ResultJson.success(PageWeb.build(authorRecommendService.authorRecommend(
                pageNo, pageSize, fieldId)));
    }
}
