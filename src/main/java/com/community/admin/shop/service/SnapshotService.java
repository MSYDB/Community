package com.community.admin.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.Snapshot;

import java.util.List;

/**
 * 订单-商品快照(UserGoodsOrderSnapshot)表服务接口
 *
 * @author wangdaoren
 * @since 2021-10-21 01:25:44
 */
public interface SnapshotService extends IService<Snapshot> {

    boolean insert(Long goodsId);

    boolean goodsNumIncr(Long goodsId);

    boolean goodsNumDecr(Long goodsId);

    ResultJson listAll();

    boolean delete(List<Long> goodsIdList);
}