package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.Cart;
import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MCart;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.model.SessionCart;
import com.example.demo.domain.service.EcService;
import com.example.demo.domain.service.MailSenderService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.OrderForm;

/**
 * 注文画面のコントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
public class OrderController {

    @Autowired
    private EcService ecService;
    
    @Autowired
    private SessionCart sessionCart;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private MailSenderService mailSenderService;
    
    /**
     * 注文画面を表示
     * @param model
     * @param form
     * @return
     */
    @GetMapping("/order")
    public String getOrder(Model model, @ModelAttribute OrderForm form) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName(); // ログインユーザーID
        
    	int total = 0;
        List<MCart> cartList = new ArrayList<MCart>();
        
     // カートリストから合計金額を計算
        for(Cart cart : sessionCart.getCartList()) {
        	// 商品情報を1件取得
        	MGoods goods = ecService.findGoodsOne(cart.getGoodsId());
        	MCart mCart = modelMapper.map(goods, MCart.class);
        	mCart.setQuantity(cart.getQuantity());
        	cartList.add(mCart);
        	total += mCart.getPrice() * mCart.getQuantity();
        }
        
        // Modelに登録
        model.addAttribute("userId", userId);
        model.addAttribute("total", total);
        model.addAttribute("cartList", cartList);

        
        return "order/order";
    }
    
    /**
     * 注文情報を送信
     * @param model
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/confirm")
    @Transactional
    public String postConfirm(Model model,
    		@ModelAttribute @Validated(GroupOrder.class) OrderForm form,
            BindingResult bindingResult) {
    	
        if (bindingResult.hasErrors()) {
            // 注文画面に遷移
            return getOrder(model, form);
        }
        
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = auth.getName(); // ログインユーザーID
        
        // カートリストの情報から購入履歴を追加
        for(Cart cart : sessionCart.getCartList()) {
        	// 商品情報を1件取得
        	MGoods goods = ecService.findGoodsOne(cart.getGoodsId());
        	// 在庫数が不足している場合
        	if(goods.getQuantity() < cart.getQuantity()) {
        		return "error/error";
        	}
        	MHistory history = new MHistory();
        	Date date = new Date();
        	history.setPurchaseId(userId + format.format(date)); // ユーザーID+日付
        	history.setGoodsId(cart.getGoodsId());               // 商品ID
        	history.setUserId(userId);                           // ユーザーID
        	history.setQuantity(cart.getQuantity());             // 個数
        	history.setPurchaseDate(date);                       // 購入日
        	history.setPrice(goods.getPrice());                  // 価格
        	// 購入履歴を1件追加
        	ecService.insertHistoryOne(history);
        	// 商品の在庫数変更
        	goods.setQuantity(goods.getQuantity() - cart.getQuantity());
        	ecService.updateGoodsOne(goods);
        }
        
        // 請求先保存するかどうか
        if(form.isBillingKeep()) {
            // formをMBillingクラスに変換
            MBilling bill = modelMapper.map(form, MBilling.class);
            bill.setUserId(userId); // ユーザー名
            
            // 請求先情報を1件更新
            ecService.updateBillingOne(bill);
        }
    	
        // メール送信
        mailSenderService.send();
        
        // カート情報削除
        sessionCart.deleteCartList();
        
        // Modelに登録
        model.addAttribute("userId", userId);
        
        return "order/order_success";
    }
}
