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

import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.service.EcService;

/**
 * HistoryControllerのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("HistoryControllerテスト")
public class HistoryControllerTest {

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
	@DisplayName("getHistoryテスト")
	public void getHistoryTest() throws Exception {
		String userId = "admin";
		List<MHistory> historyList = new ArrayList<MHistory>();
		when(ecService.findHistoryMany(userId)).thenReturn(historyList);
		mvc.perform(get("/user/history/admin"))
        .andExpect(status().isOk())
        .andExpect(view().name("user/history"))
        .andReturn();
	}
}
