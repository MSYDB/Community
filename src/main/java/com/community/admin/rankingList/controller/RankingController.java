package com.community.admin.rankingList.controller;

import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.rankingList.service.RankingService;
import com.community.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author DB
 * <br>CreateDate 2021/10/17 1:53
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "ranking")
public class RankingController {
    @Autowired
    private RankingService rankingService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("total")
    public ResultJson total() {
        return rankingService.ranking();
    }

    //文章排行榜
    @RequestMapping("paiHang")
    public ResultJson paiHang() {
        return rankingService.paiHang();
    }

    //作者排行榜
    @RequestMapping("authorPaiHang")
    public ResultJson authorPaiHang(Integer fieldId) {
        return rankingService.authorPaiHang(fieldId);
    }


}
