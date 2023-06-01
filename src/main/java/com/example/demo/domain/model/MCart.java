package com.example.demo.domain.model;

import lombok.Data;

/**
 * カートの商品
 * @author K.Tomonari
 *
 */
@Data
public class MCart {
    private String goodsId;          // 商品ID
    private String goodsName;        // 商品名
    private Integer price;           // 価格
    private Integer quantity;        // 個数
    private String goodsImage;       // 商品画像
    private String goodsExplanation; // 商品説明
}

