package com.example.demo.domain.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * カート
 * @author K.Tomonari
 *
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Cart implements Serializable {
    private String goodsId;   // 商品ID
    private Integer quantity; // 個数
}
