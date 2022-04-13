package com.community.admin.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.Goods;

/**
 * 商城表(Goods)表服务接口
 *
 * @author wangdaoren
 * @since 2021-10-14 17:41:12
 */
public interface GoodsService extends IService<Goods> {
    ResultJson listAll(Long pageNo, Long pageSize, String goodsName);
}