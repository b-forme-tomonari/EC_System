package com.example.demo.service;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.domain.model.Cart;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.SessionCart;
import com.example.demo.domain.service.EcService;
import com.example.demo.domain.service.MailSenderService;

/**
 * MailSenderServiceのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("MailSenderServiceテスト")
public class MailSenderServiceTest {

    MockMvc mvc;

    @MockBean
	private EcService ecService;
    
    @MockBean
    SessionCart sessionCart;
    
    @Autowired
    MailSenderService mailSenderService;
	
    /**
     * MockMvcの初期化処理
     * @param context
     */
    @BeforeEach
    public void setup(WebApplicationContext context) {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();   
    }
    
    /**
     * sendメソッドが正しく動作するか確認
     * @throws Exception
     */
	@Test
	@WithMockUser(username = "general", roles = {"USER"})
	@DisplayName("sendテスト")
	public void sendTest() throws Exception {
        List<Cart> cartList = new ArrayList<Cart>();
        Cart cart = new Cart();
        cart.setGoodsId("cart6");
        cart.setQuantity(2);
        cartList.add(cart);
		when(sessionCart.getCartList()).thenReturn(cartList);
		
		String goodsId = "cart6";
		MGoods goods = new MGoods();
		goods.setGoodsId(goodsId);
		goods.setGoodsName("サーバー");
		goods.setPrice(20000);
		goods.setQuantity(50);
		when(ecService.findGoodsOne(goodsId)).thenReturn(goods);
		mailSenderService.send();
	}

}
