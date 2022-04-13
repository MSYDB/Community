package com.community.admin.shop.controller;


import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.GoodsComment;
import com.community.admin.shop.service.GoodsCommentService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品评论表(GoodsComment)表控制层
 *
 * @author wangdaoren
 * @since 2021-10-23 16:24:59
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "goodsComment")
public class GoodsCommentController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsCommentService goodsCommentService;


    /**
     * @param pageNo   页码
     * @param pageSize 查询条数
     * @return
     */
    @AuthString("listAllGoodsComment")
    @SelfLog(module = "goodsComment", name = "查看商品一级评论")
    @RequestMapping("listAll")
    public ResultJson listAll(@RequestParam(defaultValue = "1") Long pageNo,
                              @RequestParam(defaultValue = "10") Long pageSize) {
        return this.goodsCommentService.listAll(pageNo, pageSize);
    }

    @AuthString("expandMoreGoodsComment")
    @SelfLog(module = "goodsComment", name = "展开更多评论")
    @RequestMapping("expandMore")
    public ResultJson expandMore(Long id) {
        return this.goodsCommentService.expandMore(id);
    }

    /**
     * 新增数据
     *
     * @param goodsComment 实体对象
     * @return 新增结果
     */
    @AuthString("insertGoodsComment")
    @SelfLog(module = "goodsComment", name = "添加商品评论")
    @RequestMapping("insert")
    public ResultJson insert(@RequestBody GoodsComment goodsComment) {
        return ResultJson.isOk(this.goodsCommentService.insert(goodsComment));
    }


    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @AuthString("deleteGoodsComment")
    @SelfLog(module = "goodsComment", name = "删除商品评论")
    @RequestMapping("delete")
    public ResultJson delete(@RequestParam("idList") List<Long> idList) {
        return ResultJson.isOk(this.goodsCommentService.removeByIds(idList));
    }
}