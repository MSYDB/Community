package com.community.admin.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.shop.entity.UserGoodsOrder;

/**
 * 订单表(UserGoodsOrder)表服务接口
 *
 * @author wangdaoren
 * @since 2021-10-22 12:05:35
 */
public interface UserGoodsOrderService extends IService<UserGoodsOrder> {

    boolean insert(UserGoodsOrder userGoodsOrder);
}