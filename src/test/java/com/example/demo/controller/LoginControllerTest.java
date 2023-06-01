package com.example.demo.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.domain.service.EcService;

/**
 * LoginControllerのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("LoginControllerテスト")
public class LoginControllerTest {

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
	@DisplayName("getLoginテスト")
	public void getLoginTest() throws Exception {
		mvc.perform(get("/login"))
        .andExpect(status().isOk())
        .andExpect(view().name("login/login"))
        .andReturn();
	}
}
