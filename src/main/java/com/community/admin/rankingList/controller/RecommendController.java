package com.community.admin.rankingList.controller;

import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.rankingList.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "mainPage")
public class RecommendController {

    @Autowired
    private RecommendService recommendService;

    /**
     * @return []
     * @Author liu-miss
     * @Description //TODO 每日推荐的接口
     * @Date l 2021/10/14
     * @Param com.community.admin.common.web.ResultJson
     **/
    @RequestMapping("recommend")
    public ResultJson recommend(@NotNull Integer pageNo, @NotNull Integer pageSize) {
        return ResultJson.success(PageWeb.build(recommendService.EveryDayRecommend(pageNo,
                pageSize)));

    }


}
