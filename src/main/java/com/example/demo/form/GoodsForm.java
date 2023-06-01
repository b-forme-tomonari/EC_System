package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 商品のFormクラス
 * @author K.Tomonari
 *
 */
@Data
public class GoodsForm {
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 4, max = 32, groups = ValidGroup2.class)
    private String goodsId; // 商品ID
    
    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 4, max = 100, groups = ValidGroup2.class)
    private String goodsName; // 商品名
    
    @NotNull(groups = ValidGroup1.class)
    @PositiveOrZero(groups = ValidGroup2.class)
    private Integer price; // 価格
    
    @NotNull(groups = ValidGroup1.class)
    @PositiveOrZero(groups = ValidGroup2.class)
    private Integer quantity; // 個数
    
    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 4, max = 100, groups = ValidGroup2.class)
    private String goodsImage; // 商品画像
    
    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 4, max = 200, groups = ValidGroup2.class)
    private String goodsExplanation; // 商品説明
}