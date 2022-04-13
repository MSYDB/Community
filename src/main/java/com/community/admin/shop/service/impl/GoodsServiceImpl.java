package com.community.admin.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.community.admin.common.web.PageWeb;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.Goods;
import com.community.admin.shop.mapper.GoodsDao;
import com.community.admin.shop.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * 商城表(Goods)表服务实现类
 *
 * @author wangdaoren
 * @since 2021-10-14 17:41:12
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, Goods> implements GoodsService {

    @Override
    public ResultJson listAll(Long pageNo, Long pageSize, String goodsName) {
        Page page = new Page(pageNo, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (goodsName != null && !goodsName.trim().equals("")) {
            queryWrapper.like("goods_name", goodsName);
        }
        IPage pageInfo = this.page(page, queryWrapper);
        PageWeb pageWeb = PageWeb.build(pageInfo);
        return ResultJson.success(pageWeb);
    }
}