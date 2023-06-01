package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.service.EcService;
import com.example.demo.form.GoodsForm;

/**
 * AdminGoodsControllerのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("AdminGoodsControllerテスト")
public class AdminGoodsControllerTest {

    MockMvc mvc;

    @MockBean
	private EcService ecService;
	
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
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("getAdminGoodsテスト")
	public void getAdminGoodsTest() throws Exception {
		mvc.perform(get("/admin/goods"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/goods"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("getGoodsテスト")
	public void getGoodsTest() throws Exception {
		String goodsId = "cart5";
		MGoods goods = new MGoods();
		goods.setGoodsId(goodsId);
		when(ecService.findGoodsOne(goodsId)).thenReturn(goods);
		mvc.perform(get("/admin/goods/cart5"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/goods_detail"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("getEditGoodsテスト")
	public void getEditGoodsTest() throws Exception {
		String goodsId = "cart5";
		MGoods goods = new MGoods();
		goods.setGoodsId(goodsId);
		GoodsForm form = new GoodsForm();
		when(ecService.findGoodsOne(goodsId)).thenReturn(goods);
		mvc.perform(get("/admin/goods/edit/cart5")
				.flashAttr("goodsForm", form))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/goods_edit"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("postEditGoodsテスト")
	public void postEditGoodsTest() throws Exception {
		GoodsForm form = new GoodsForm();
		form.setGoodsId("cart21");
		form.setGoodsName("サーバー");
		form.setPrice(20000);
		form.setQuantity(100);
		form.setGoodsImage("image/Server21.jpg");
		form.setGoodsExplanation("高性能サーバーです。");
		mvc.perform(post("/admin/goods/edit")
                .flashAttr("goodsForm", form)
                .with(csrf()))
        .andExpect(redirectedUrl("/admin/goods"))
        .andExpect(view().name("redirect:/admin/goods"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("getRegisterGoodsテスト")
	public void getRegisterGoodsTest() throws Exception {
		mvc.perform(get("/admin/goods/register"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/goods_register"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("postRegisterGoodsテスト")
	public void postRegisterGoodsTest() throws Exception {
		GoodsForm form = new GoodsForm();
		form.setGoodsId("cart21");
		form.setGoodsName("サーバー");
		form.setPrice(20000);
		form.setQuantity(100);
		form.setGoodsImage("image/Server21.jpg");
		form.setGoodsExplanation("高性能サーバーです。");
		mvc.perform(post("/admin/goods/register")
                .flashAttr("goodsForm", form)
                .with(csrf()))
        .andExpect(redirectedUrl("/admin/goods"))
        .andExpect(view().name("redirect:/admin/goods"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("getDeleteGoodsテスト")
	public void getDeleteGoodsTest() throws Exception {
		GoodsForm form = new GoodsForm();
		mvc.perform(get("/admin/goods/delete/cart6")
				.flashAttr("goodsForm", form))
        .andExpect(redirectedUrl("/admin/goods"))
        .andExpect(view().name("redirect:/admin/goods"))
        .andReturn();
		
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("postEditGoodsInputCheckテスト")
	public void postEditGoodsInputCheckTest() throws Exception {
		GoodsForm form = new GoodsForm();
		form.setGoodsId("cart21");
		mvc.perform(post("/admin/goods/edit")
                .flashAttr("goodsForm", form)
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/goods_edit"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("postRegisterGoodsInputCheckテスト")
	public void postRegisterGoodsInputCheckTest() throws Exception {
		GoodsForm form = new GoodsForm();
		form.setGoodsId("cart21");
		
		mvc.perform(post("/admin/goods/register")
                .flashAttr("goodsForm", form)
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/goods_register"))
        .andReturn();
	}
}
