package com.community.admin.shop.controller;

import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.service.SnapshotService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "shoppingCart")
public class ShoppingCartController {

    @Resource
    private SnapshotService snapshotService;

    /**
     * @param id 商品id
     * @return com.community.admin.common.web.ResultJson
     * @author wangdaoren
     * @Description 添加商品到购物车
     */
    @AuthString("shoppingCartInsert")
    @SelfLog(module = "shoppingCart", name = "向购物车添加商品")
    @RequestMapping("insert")
    public ResultJson insert(Long id) {
        return ResultJson.isOk(this.snapshotService.insert(id));
    }

    /**
     * @param goodsId 商品id
     * @return com.community.admin.common.web.ResultJson
     * @author wangdaoren
     * @Description 购物车中商品的数量+1
     */
    @AuthString("goodsNumIncr")
    @SelfLog(module = "shoppingCart", name = "购物车中商品的数量+1")
    @RequestMapping("goodsNumIncr")
    public ResultJson goodsNumIncr(Long goodsId) {
        return ResultJson.isOk(this.snapshotService.goodsNumIncr(goodsId));
    }

    /**
     * @param goodsId 商品id
     * @return com.community.admin.common.web.ResultJson
     * @author wangdaoren
     * @Description 购物车中商品的数量-1
     */
    @AuthString("goodsNumDecr")
    @SelfLog(module = "shoppingCart", name = "购物车中商品的数量-1")
    @RequestMapping("goodsNumDecr")
    public ResultJson goodsNumDecr(Long goodsId) {
        return ResultJson.isOk(this.snapshotService.goodsNumDecr(goodsId));
    }

    /**
     * @param
     * @return com.community.admin.common.web.ResultJson
     * @author wangdaoren
     * @Description 查看购物车
     */
    @AuthString("shoppingCartListAll")
    @SelfLog(module = "shoppingCart", name = "查看购物车")
    @RequestMapping("listAll")
    public ResultJson listAll() {
        return this.snapshotService.listAll();
    }

    /**
     * @param goodsIdList 商品id列表
     * @return com.community.admin.common.web.ResultJson
     * @author wangdaoren
     * @Description 删除购物车中的商品
     */
    @AuthString("shoppingCartDelete")
    @SelfLog(module = "shoppingCart", name = "删除购物车中的商品")
    @RequestMapping("delete")
    public ResultJson delete(@RequestParam List<Long> goodsIdList) {
        return ResultJson.isOk(this.snapshotService.delete(goodsIdList));
    }
}
