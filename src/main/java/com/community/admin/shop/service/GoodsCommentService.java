package com.community.admin.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.GoodsComment;

/**
 * 商品评论表(GoodsComment)表服务接口
 *
 * @author wangdaoren
 * @since 2021-10-23 16:24:59
 */
public interface GoodsCommentService extends IService<GoodsComment> {

    ResultJson listAll(Long pageNo, Long pageSize);

    ResultJson expandMore(Long id);

    boolean insert(GoodsComment goodsComment);

}