package com.community.admin.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * 订单-商品快照(UserGoodsOrderSnapshot)表实体类
 *
 * @author wangdaoren
 * @since 2021-10-22 12:08:43
 */
@Data
@SuppressWarnings("serial")
public class UserGoodsOrderSnapshot extends Model<UserGoodsOrderSnapshot> {

    @TableId(type = IdType.AUTO)
    private Long id;
    //商品名称
    private String goodsName;
    //商品描述
    private String description;
    //照片路径
    private String imagePath;
    //订单id
    private Long orderId;
    //商品id
    private Long goodsId;
    //单价
    private Double goodsPrice;
    //商品购买数量
    private Integer goodsNum;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}