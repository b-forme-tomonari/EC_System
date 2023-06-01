package com.example.demo.domain.model;

import java.util.Date;

import lombok.Data;

/**
 * 購入履歴
 * @author K.Tomonari
 *
 */
@Data
public class MHistory {
	private String purchaseId; // 購入ID
    private String goodsId;    // 商品ID
    private String userId;     // ユーザーID
    private String goodsName;  // 商品名
    private String goodsImage; // 商品画像
    private Integer quantity;  // 個数
    private Date purchaseDate; // 購入日
    private Integer price;     // 価格
}

