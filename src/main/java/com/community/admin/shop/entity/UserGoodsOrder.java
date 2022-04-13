package com.community.admin.shop.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表(UserGoodsOrder)表实体类
 *
 * @author wangdaoren
 * @since 2021-10-22 12:05:30
 */
@Data
@SuppressWarnings("serial")
public class UserGoodsOrder extends Model<UserGoodsOrder> {

    @TableId(type = IdType.AUTO)
    private Long id;
    //下单用户id
    private Long userId;
    //订单号
    private Long orderNum;
    //收货地址
    private String address;
    //收件人手机号
    private String phone;
    //该订单价格
    private Double totalPrice;
    //0 待支付  1 已支付（待评论）  2 支付失败 3 已评论
    private Integer status;
    //支付失败原因
    private String reason;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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