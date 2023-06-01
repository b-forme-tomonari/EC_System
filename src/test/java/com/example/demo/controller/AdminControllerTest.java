package com.example.demo.controller;

import static org.mockito.Mockito.*;
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

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.EcService;
import com.example.demo.form.UserForm;

/**
 * AdminControllerのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("AdminControllerテスト")
public class AdminControllerTest {

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
	@DisplayName("getCustomerテスト")
	public void getCustomerTest() throws Exception {
		mvc.perform(get("/admin/customer"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/customer"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("getCustomerDetailテスト")
	public void getCustomerDetailTest() throws Exception {
		String userId = "admin";
		MUser user = new MUser();
		user.setUserId(userId);
		MBilling bill = new MBilling();
		bill.setUserId(userId);
		UserForm form = new UserForm();
		when(ecService.getUserOne(userId)).thenReturn(user);
		when(ecService.findBillingOne(userId)).thenReturn(bill);
		mvc.perform(get("/admin/customer/detail/admin")
				.flashAttr("userForm ", form))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/customer_detail"))
        .andReturn();
	}
	
	@Test
	@WithMockUser(username = "admin", roles = {"ADMIN"})
	@DisplayName("getAdminOrderテスト")
	public void getAdminOrderTest() throws Exception {
		mvc.perform(get("/admin/order"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/order"))
        .andReturn();
	}
}
