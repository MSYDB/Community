package com.community.admin.rankingList.controller;

import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * TODO zSet测试接口
 *
 * @author DB
 * <br>CreateDate 2021/10/17 0:19
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "zSet")
public class zSetTestController {
    @Autowired
    private RedisUtil redisUtil;
//    @RequestMapping("test")
//    public void test(String key, String value, Double scores){
//        System.out.println("--------------------------------");
//        redisUtil.zAdd(key, value, scores);
//    }

    @RequestMapping("find")
    public ResultJson find(String key, String value) {
        System.out.println("++++++++++++++++++++++++++");
        Double scores = redisUtil.zScore(key, value);
        return ResultJson.success(scores);
    }

    @RequestMapping("rank")
    public ResultJson rank(String key, String value) {
        System.out.println("**************************");
        Long rank = redisUtil.zRank(key, value);
        return ResultJson.success(rank);
    }

    @RequestMapping("range")
    public ResultJson range(String key) {
        System.out.println("///////////////////////////");
        Set<String> range = redisUtil.sortRange(key, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(range);
        return ResultJson.success(range);
    }


}
