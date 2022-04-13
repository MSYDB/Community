package com.community.admin.shop.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class GoodsInfo {

    @JsonProperty("goodsName")
    private String goodsName;
    @JsonProperty("goodsPrice")
    private BigDecimal goodsPrice;
    @JsonProperty("goodsImage")
    private String goodsImage;
    @JsonProperty("goodsCount")
    private Integer goodsCount;
}
