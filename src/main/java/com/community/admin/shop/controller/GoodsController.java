package com.community.admin.shop.controller;


import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.Goods;
import com.community.admin.shop.service.GoodsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商城表(Goods)表控制层
 *
 * @author wangdaoren
 * @since 2021-10-14 17:41:20
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "goods")
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;

    /**
     * @param pageNo    页码
     * @param pageSize  查询条数
     * @param goodsName 商品名称
     * @return
     */
    @AuthString("listAllGoods")
    @SelfLog(module = "goods", name = "查看商品")
    @RequestMapping("listAll")
    public ResultJson listAll(@RequestParam(defaultValue = "1") Long pageNo,
                              @RequestParam(defaultValue = "10") Long pageSize,
                              @RequestParam(value = "goodsName", required = false) String goodsName) {
        return this.goodsService.listAll(pageNo, pageSize, goodsName);
    }

    @AuthString("detailGoods")
    @SelfLog(module = "goods", name = "查看商品详情")
    @RequestMapping("detail")
    public ResultJson detail(@RequestParam Long id) {
        return ResultJson.success(this.goodsService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param goods 实体对象
     * @return 新增结果
     */
    @AuthString("insertGoods")
    @SelfLog(module = "goods", name = "新增商品")
    @RequestMapping("insert")
    public ResultJson insert(@RequestBody Goods goods) {
        return ResultJson.isOk(this.goodsService.save(goods));
    }

    /**
     * 修改数据
     *
     * @param goods 实体对象
     * @return 修改结果
     */
    @AuthString("updateGoods")
    @SelfLog(module = "goods", name = "修改商品信息")
    @RequestMapping("update")
    public ResultJson update(@RequestBody Goods goods) {
        return ResultJson.isOk(this.goodsService.updateById(goods));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @AuthString("deleteGoods")
    @SelfLog(module = "goods", name = "商品下架")
    @RequestMapping("delete")
    public ResultJson delete(@RequestParam("idList") List<Long> idList) {
        return ResultJson.isOk(this.goodsService.removeByIds(idList));
    }
}