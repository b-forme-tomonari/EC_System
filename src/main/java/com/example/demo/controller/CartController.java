package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.model.Cart;
import com.example.demo.domain.model.MCart;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.SessionCart;
import com.example.demo.domain.service.EcService;

/**
 * カート画面のコントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
@RequestMapping("/user")
public class CartController {

	@Autowired
	private EcService ecService;
	
	@Autowired
	private SessionCart sessionCart;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * カート画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/cart")
	public String getCart(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName(); // ログインユーザーID
		
		int total = 0;
		List<MCart> cartList = new ArrayList<MCart>();
		
		// カートリストから合計金額を計算
		for(Cart cart : sessionCart.getCartList()) {
			// 商品情報を1件取得
			MGoods goods = ecService.findGoodsOne(cart.getGoodsId());
			MCart mCart = modelMapper.map(goods, MCart.class);
			mCart.setQuantity(cart.getQuantity()); // 個数
			cartList.add(mCart);
			// カートの合計額を計算
			total += mCart.getPrice() * mCart.getQuantity();
		}
		
		// Modelに登録
		model.addAttribute("userId", userId);
		model.addAttribute("total", total);
		model.addAttribute("cartList", cartList);

		return "user/cart";
	}
	
	/**
	 * カートから商品削除
	 * @param model
	 * @param goodsId
	 * @return
	 */
	@GetMapping("/cart/delete/{goodsId:.+}")
	public String getdeleteGoods(Model model ,@PathVariable("goodsId") String goodsId) {
	
		List<Cart> cartList = sessionCart.getCartList();
		Cart deleteCart = null;
		
		// カートリストから商品IDに一致するデータを取得
		for(Cart cart : cartList){
			if(cart.getGoodsId().equals(goodsId)) {
				deleteCart = cart;
			}
		}
		
		// カートリストから対象データを削除
		cartList.remove(deleteCart);
		
		return "redirect:/user/cart";
	}
}
