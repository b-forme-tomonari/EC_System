package com.example.demo.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * カートリスト
 * @author K.Tomonari
 *
 */
@Component
@SessionScope
public class SessionCart implements Serializable {
    private List<Cart> cartList;

    /** カートリストを取得 */
	public List<Cart> getCartList() {
		if(cartList == null) {
			cartList = new ArrayList<Cart>();
		}
		return cartList;
	}

	/** カートリストを設定 */
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	
	/** 商品IDからカート情報を取得 */
	public Cart getCart(String goodsId) {
		
		for(Cart cart : this.cartList) {
			if(cart.getGoodsId().equals(goodsId)) {
				return cart;
			}
		}
		return null;
	}
	
	/** カートリストを削除 */
	public void deleteCartList() {
		this.cartList = null;
	}
}
