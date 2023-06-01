package com.example.demo.controller;

import static org.mockito.Mockito.*;
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

import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.service.EcService;

/**
 * AdminGraphControllerのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("AdminGraphControllerテスト")
public class AdminGraphControllerTest {

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
	@DisplayName("getAdminテスト")
	public void getAdminTest() throws Exception {
		mvc.perform(get("/admin"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/admin"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("getRankテスト")
	public void getRankTest() throws Exception {
		List<MHistory> historyList = new ArrayList<MHistory>();
		MHistory history = new MHistory();
		history.setGoodsId("cart6");
		history.setPrice(20000);
		history.setQuantity(2);
		historyList.add(history);
		when(ecService.findHistoryMany(null)).thenReturn(historyList);
		
		String goodsId = "cart6";
		MGoods goods = new MGoods();
		goods.setGoodsId(goodsId);
		goods.setGoodsName("サーバー");
		when(ecService.findGoodsOne(goodsId)).thenReturn(goods);
		mvc.perform(get("/admin/rank"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/rank"))
        .andReturn();
	}
	
}
