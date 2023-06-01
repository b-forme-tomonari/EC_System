package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.domain.model.RecaptchaResult;
import com.example.demo.domain.service.EcService;
import com.example.demo.form.SignupForm;

/**
 * SignupControllerのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("SignupControllerテスト")
public class SignupControllerTest {

    MockMvc mvc;

    @MockBean
	private EcService ecService;
    
    @MockBean
    private RecaptchaResult result;
	
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
	@DisplayName("getSignupテスト")
	public void getSignupTest() throws Exception {
		mvc.perform(get("/signup"))
        .andExpect(status().isOk())
        .andExpect(view().name("user/signup"))
        .andReturn();
	}
	
	@Test
	@DisplayName("postSignupテスト")
	public void postSignupTest() throws Exception {
		SignupForm form = new SignupForm();
		form.setUserId("sample");
		form.setPassword("samplesample");
		form.setEmail("sample@sample.com");
		form.setName("test");
		form.setDateOfBirth(new Date());
		form.setZipCode("000-0000");
		form.setAddress("test");
		when(result.isSuccess()).thenReturn(true);
		mvc.perform(post("/signup")
				.flashAttr("signupForm", form)
				.with(csrf()))
        //.andExpect(redirectedUrl("/login"))
        //.andExpect(view().name("redirect:/login"))
        .andReturn();
	}

}
