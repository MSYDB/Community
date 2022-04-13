package com.community.admin.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.shop.entity.UserGoodsOrder;
import com.community.admin.shop.mapper.UserGoodsOrderDao;
import com.community.admin.shop.service.UserGoodsOrderService;
import com.community.admin.util.JwtUtil;
import com.community.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 订单表(UserGoodsOrder)表服务实现类
 *
 * @author wangdaoren
 * @since 2021-10-22 12:05:35
 */
@Service("userGoodsOrderService")
public class UserGoodsOrderServiceImpl extends ServiceImpl<UserGoodsOrderDao, UserGoodsOrder> implements UserGoodsOrderService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @Value("${ORDER_DEFAULT_KEY}")
    private long orderDefaultKey;

    @Override
    public boolean insert(UserGoodsOrder userGoodsOrder) {
        String token = httpServletRequest.getHeader("token");
        Long userId = Long.valueOf(JwtUtil.getAudience(token));
        if (!redisUtil.hasKey("ORDER_GEN_KEY")) {
            redisUtil.set("ORDER_GEN_KEY", orderDefaultKey);
        }
        redisUtil.incrBy("ORDER_GEN_KEY", 1L);
        userGoodsOrder.setUserId(userId);
        String numString = String.valueOf(redisUtil.get("ORDER_GEN_KEY"));
        userGoodsOrder.setOrderNum(Long.valueOf(numString));
        userGoodsOrder.setStatus(0);
        int flag = this.baseMapper.insert(userGoodsOrder);
        if (flag != 1) {
            return false;
        }
        return true;
    }
}