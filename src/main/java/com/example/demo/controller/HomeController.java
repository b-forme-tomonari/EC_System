package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.model.Cart;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.SessionCart;
import com.example.demo.domain.service.EcService;

/**
 * ホーム画面のコントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
public class HomeController {

    @Autowired
    private EcService ecService;
    
    @Autowired
    private SessionCart sessionCart;
    
    /**
     * ホーム画面を表示
     * @param model
     * @return
     */
    @GetMapping("/home")
    public String getHome(Model model) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName(); // ログインユーザーID
         
        // 商品情報の取得
        List<MGoods> goodsList = ecService.findGoodsMany();

        // Modelに登録
        model.addAttribute("userId", userId);
        model.addAttribute("goodsList", goodsList);

        return "home/home";
    }
    
    /**
     * 商品詳細画面を表示
     * @param model
     * @param goodsId
     * @return
     */
    @GetMapping("/home/{goodsId:.+}")
    public String getGoods(Model model ,@PathVariable("goodsId") String goodsId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName(); // ログインユーザーID
        
        // 商品情報を1件取得
        MGoods goods = ecService.findGoodsOne(goodsId);

        // Modelに登録
        model.addAttribute("userId", userId);
        model.addAttribute("goods", goods);

        return "home/goods_info";
    }
    
    /**
     * カートに商品追加
     * @param model
     * @param goodsId
     * @return
     */
    @GetMapping("/home/add/{goodsId:.+}")
    public String getAddGoods(Model model ,@PathVariable("goodsId") String goodsId) {
    
    	List<Cart> cartList = sessionCart.getCartList();
    	
    	// カートリストが空の場合
        if(cartList.isEmpty()) {
        	Cart cart = new Cart(goodsId, 1);
        	cartList.add(cart);
        }
        else {
        	Cart cart = sessionCart.getCart(goodsId);
        	// 指定した商品IDがカートに入っていない場合
        	if(cart == null) {
            	cart = new Cart(goodsId, 1);
            	cartList.add(cart);
        	}
        	else {
        		cart.setQuantity(cart.getQuantity() + 1);
        	}
        }
        return "redirect:/home";
    }
}
