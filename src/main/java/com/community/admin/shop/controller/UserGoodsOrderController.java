package com.community.admin.shop.controller;


import com.community.admin.anno.AuthString;
import com.community.admin.anno.SelfLog;
import com.community.admin.common.constant.GlobalConstant;
import com.community.admin.common.web.ResultJson;
import com.community.admin.shop.entity.UserGoodsOrder;
import com.community.admin.shop.service.UserGoodsOrderService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单表(UserGoodsOrder)表控制层
 *
 * @author wangdaoren
 * @since 2021-10-22 12:05:35
 */
@RestController
@RequestMapping(GlobalConstant.SERVER_URL_PREFIX + "order")
public class UserGoodsOrderController {
    /**
     * 服务对象
     */
    @Resource
    private UserGoodsOrderService userGoodsOrderService;

    /**
     * 查看订单
     *
     * @param id 主键
     * @return 单条数据
     */
    @AuthString("listOrder")
    @SelfLog(module = "order", name = "查看订单")
    @RequestMapping("list")
    public ResultJson selectOne(Long id) {
        return ResultJson.success(this.userGoodsOrderService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userGoodsOrder 实体对象
     * @return 新增结果
     */
    @AuthString("insertOrder")
    @SelfLog(module = "order", name = "生成订单")
    @RequestMapping("insert")
    public ResultJson insert(@RequestBody UserGoodsOrder userGoodsOrder) {
        return ResultJson.isOk(this.userGoodsOrderService.insert(userGoodsOrder));
    }

    /**
     * 修改数据
     *
     * @param userGoodsOrder 实体对象
     * @return 修改结果
     */
    @PutMapping
    public ResultJson update(@RequestBody UserGoodsOrder userGoodsOrder) {
        return ResultJson.isOk(this.userGoodsOrderService.updateById(userGoodsOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键结合
     * @return 删除结果
     */
    @AuthString("deleteOrder")
    @SelfLog(module = "order", name = "删除订单")
    @RequestMapping("delete")
    public ResultJson delete(Long id) {
        return ResultJson.isOk(this.userGoodsOrderService.removeById(id));
    }
}