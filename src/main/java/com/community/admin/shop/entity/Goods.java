package com.community.admin.shop.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商城表(Goods)表实体类
 *
 * @author wangdaoren
 * @since 2021-10-14 17:41:07
 */
@Data
@SuppressWarnings("serial")
public class Goods extends Model<Goods> {

    @TableId(type = IdType.AUTO)
    private Long id;
    //商品名称
    private String goodsName;
    //商品描述
    private String description;
    //商品图片路径
    private String imagePath;
    //0:待上架，1：已上架
    private Integer publishStatus;
    //价格
    private BigDecimal price;
    //销量
    private Integer saleCount;
    //库存剩余数量
    private Integer remainderCount;
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