package com.example.demo.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Cart;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.SessionCart;

/**
 * メール送信のサービスクラス
 * @author K.Tomonari
 *
 */
@Service
public class MailSenderService {

	@Autowired
	private EcService userService;

	@Autowired
	MailSender mailSender;

	@Autowired
	private SessionCart sessionCart;

	/** 注文確定した情報をメール送信 */
    public void send() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("k_tomonari@b-forme.net"); // 宛先
		message.setFrom("k_tomonari@b-forme.net"); // 差出人
		message.setSubject("注文確定"); // 件名
		String text = userId + " 様\n以下の商品が注文確定しました\n\n";
		int total = 0;

		// カート情報から商品名検索と合計金額を計算
		for (Cart cart : sessionCart.getCartList()) {
			MGoods goods = userService.findGoodsOne(cart.getGoodsId());
			text += goods.getGoodsName() + " " + goods.getPrice() + "円 " + cart.getQuantity() + "個\n";
			total += goods.getPrice() * cart.getQuantity();
		}

		text += "\n合計金額 " + total + "円";
		message.setText(text);

		// メール送信
		try {
			this.mailSender.send(message);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}
}