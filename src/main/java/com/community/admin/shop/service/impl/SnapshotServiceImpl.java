package com.community.admin.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.Goods;
import com.community.admin.shop.entity.GoodsInfo;
import com.community.admin.shop.entity.Snapshot;
import com.community.admin.shop.mapper.GoodsDao;
import com.community.admin.shop.mapper.SnapshotDao;
import com.community.admin.shop.service.SnapshotService;
import com.community.admin.util.JwtUtil;
import com.community.admin.util.RedisTestUtil;
import com.community.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单-商品快照(UserGoodsOrderSnapshot)表服务实现类
 *
 * @author wangdaoren
 * @since 2021-10-21 01:25:44
 */
@Service("userGoodsOrderSnapshotService")
public class SnapshotServiceImpl extends ServiceImpl<SnapshotDao, Snapshot> implements SnapshotService {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTestUtil redisTestUtil;

    @Override
    public boolean insert(Long goodsId) {
        String token = httpServletRequest.getHeader("token");
        String userId = JwtUtil.getAudience(token);
        Goods goods = this.goodsDao.selectById(goodsId);
        if (goods == null || goods.getRemainderCount() <= 0) {
            return false;
        }
        redisTestUtil.hmSet(userId, goodsId + "", 1);
        return true;
    }

    @Override
    public boolean goodsNumIncr(Long goodsId) {
        String token = httpServletRequest.getHeader("token");
        String userId = JwtUtil.getAudience(token);
        Goods goods = this.goodsDao.selectById(goodsId);
        if (goods == null || redisTestUtil.hmGet(userId, goodsId + "") == null) {
            return false;
        }
        if (goods.getRemainderCount() < ((int) redisTestUtil.hmGet(userId, goodsId + "") + 1)) {
            return false;
        }
        redisUtil.hashIncrBy(userId, goodsId + "", 1);
        return true;
    }

    @Override
    public boolean goodsNumDecr(Long goodsId) {
        String token = httpServletRequest.getHeader("token");
        String userId = JwtUtil.getAudience(token);
        Goods goods = this.goodsDao.selectById(goodsId);
        if (goods == null || redisTestUtil.hmGet(userId, goodsId + "") == null) {
            return false;
        }
        if (((int) redisTestUtil.hmGet(userId, goodsId + "") - 1) < 1) {
            return false;
        }
        redisUtil.hashIncrBy(userId, goodsId + "", -1);
        return true;
    }

    @Override
    public ResultJson listAll() {
        String token = httpServletRequest.getHeader("token");
        String userId = JwtUtil.getAudience(token);
        Map<String, Integer> map = redisTestUtil.hashGetAll(userId);
        List<Map<String, GoodsInfo>> goodsInfoList = new ArrayList<>();
        Map<String, GoodsInfo> goodsInfoMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            GoodsInfo goodsInfo = new GoodsInfo();
            Goods goods = this.goodsDao.selectById(entry.getKey());
            goodsInfo.setGoodsName(goods.getGoodsName());
            goodsInfo.setGoodsPrice(goods.getPrice());
            goodsInfo.setGoodsImage(goods.getImagePath());
            goodsInfo.setGoodsCount(entry.getValue());
            goodsInfoMap.put("商品" + entry.getKey(), goodsInfo);
        }
        goodsInfoList.add(goodsInfoMap);
        return ResultJson.success(goodsInfoList);
    }

    @Override
    public boolean delete(List<Long> goodsIdList) {
        String token = httpServletRequest.getHeader("token");
        String userId = JwtUtil.getAudience(token);
        for (Long goodsId : goodsIdList) {
            redisTestUtil.hashDelete(userId, goodsId + "");
            if (redisTestUtil.hmGet(userId, goodsId + "") != null) {
                return false;
            }
        }
        return true;
    }
}