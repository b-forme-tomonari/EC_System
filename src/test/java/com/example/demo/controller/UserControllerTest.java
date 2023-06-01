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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.EcService;
import com.example.demo.form.UserForm;

/**
 * UserControllerのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("UserControllerテスト")
public class UserControllerTest {

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
	@WithMockUser(username = "user", roles = {"USER"})
	@DisplayName("getUserテスト")
	public void getUserTest() throws Exception {
		String userId = "user";
		MUser user = new MUser();
		user.setUserId(userId);
		MBilling bill = new MBilling();
		bill.setUserId(userId);
		UserForm form = new UserForm();
		when(ecService.getUserOne(userId)).thenReturn(user);
		when(ecService.findBillingOne(userId)).thenReturn(bill);
		mvc.perform(get("/user/detail/user").flashAttr("form", form))
        .andExpect(status().isOk())
        .andExpect(view().name("user/user_info"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "user", roles = {"USER"})
	@DisplayName("getEditテスト")
	public void getEditTest() throws Exception {
		String userId = "user";
		MUser user = new MUser();
		user.setUserId(userId);
		MBilling bill = new MBilling();
		bill.setUserId(userId);
		UserForm form = new UserForm();
		when(ecService.getUserOne(userId)).thenReturn(user);
		when(ecService.findBillingOne(userId)).thenReturn(bill);
		mvc.perform(get("/user/edit/user").flashAttr("form", form))
        .andExpect(status().isOk())
        .andExpect(view().name("user/user_edit"))
        .andReturn();
	}
    
	
	@Test
	@WithMockUser(username = "general", roles = {"USER"})
	@DisplayName("postEditテスト")
	public void postEditTest() throws Exception {
		UserForm form = new UserForm();
		
		form.setUserId("general");
		form.setPassword("adminadmin");
		form.setEmail("test@gmail.com");
		form.setName("テスト");
		form.setDateOfBirth(new Date());
		form.setZipCode("600-8216");
		form.setAddress("京都府京都市下京区東塩小路町７２１−１");
		mvc.perform(post("/user/edit")
                .flashAttr("userForm", form)
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("user/user_success"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "general", roles = {"USER"})
	@DisplayName("postEditInputCheckテスト")
	public void postEditInputCheckTest() throws Exception {
		UserForm form = new UserForm();		
		form.setUserId("general");

		mvc.perform(post("/user/edit")
                .flashAttr("userForm", form)
                .with(csrf()))
        .andExpect(status().isOk())
        .andExpect(view().name("user/user_edit"))
        .andReturn();
	}
}
