package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import com.example.demo.form.OrderForm;

/**
 * OrderControllerのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("OrderControllerテスト")
public class OrderControllerTest {

    MockMvc mvc;

    @MockBean
	private EcService ecService;
    
    @MockBean
    SessionCart sessionCart;
	
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
    
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	@DisplayName("getOrderテスト")
	public void getOrderTest() throws Exception {
        List<Cart> cartList = new ArrayList<Cart>();
        Cart cart = new Cart();
        cart.setGoodsId("cart6");
        cart.setQuantity(2);
        cartList.add(cart);
		when(sessionCart.getCartList()).thenReturn(cartList);
		
		String goodsId = "cart6";
		MGoods goods = new MGoods();
		goods.setGoodsId(goodsId);
		goods.setPrice(20000);
		goods.setQuantity(50);
		when(ecService.findGoodsOne(goodsId)).thenReturn(goods);
		
		mvc.perform(get("/order"))
        .andExpect(status().isOk())
        .andExpect(view().name("order/order"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "general", roles = {"USER"})
	@DisplayName("postConfirmテスト")
	public void postConfirmTest() throws Exception {
		OrderForm form = new OrderForm();
		form.setName("general");
		form.setEmail("general@sample.com");
		form.setZipCode("600-8216");
		form.setAddress("京都府京都市下京区東塩小路町７２１−１");
		form.setBillingName("test");
		form.setBillingZipCode("600-8216");
		form.setBillingAddress("京都府京都市下京区東塩小路町７２１−１");
		form.setBillingNumber("070-1111-1111");
		form.setBillingKeep(true);
		form.setCreditName("test");
		form.setCreditNumber("4111111111111111");
		form.setExpirationDate("05/24");
		form.setSecurityCode("322");
		
        List<Cart> cartList = new ArrayList<Cart>();
        Cart cart = new Cart();
        cart.setGoodsId("cart6");
        cart.setQuantity(2);
        cartList.add(cart);
		when(sessionCart.getCartList()).thenReturn(cartList);
		
		String goodsId = "cart6";
		MGoods goods = new MGoods();
		goods.setGoodsId(goodsId);
		goods.setPrice(20000);
		goods.setQuantity(50);
		when(ecService.findGoodsOne(goodsId)).thenReturn(goods);
		
		mvc.perform(post("/confirm")
				.flashAttr("orderForm", form)
				.with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("order/order_success"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "general", roles = {"USER"})
	@DisplayName("postConfirmInputCheckテスト")
	public void postConfirmInputCheckTest() throws Exception {
		OrderForm form = new OrderForm();
		form.setName("general");
		mvc.perform(post("/confirm")
				.flashAttr("orderForm", form)
				.with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("order/order"))
        .andReturn();
	}
}
