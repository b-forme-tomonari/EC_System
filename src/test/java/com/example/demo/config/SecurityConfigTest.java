package com.example.demo.config;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * SecurityConfigのテストクラス
 * @author K.Tomonari
 *
 */
@SpringBootTest
@DisplayName("SecurityConfigテスト")
public class SecurityConfigTest {

    MockMvc mvc;

    /**
     * MockMvcの初期化処理
     * @param context
     */
    @BeforeEach
    void setup(WebApplicationContext context) {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    
    @Test
    @WithAnonymousUser
    @DisplayName("login.cssがログイン無しで取得できる")
    void getCss() throws Exception {
        mvc.perform(get("/css/login/login.css"))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("admin/adminでログインできる")
    void adminLogin() throws Exception {
        mvc.perform(formLogin("/login").user("userId", "admin").password("admin"))
                .andExpect(authenticated());
    }
    
    @Test
    @DisplayName("ログアウト後にログイン画面にリダイレクト")
    void logoutRedirectLogin() throws Exception {
        mvc.perform(logout())
                .andExpect(status().is3xxRedirection())
                .andExpect(header().stringValues("location", "/login"));
    }
    
    @Test
    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "loginUserDetailsService")
    @DisplayName("ADMINロールで管理者画面にアクセスできる")
    void topWithAdmin() throws Exception {
        mvc.perform(get("/admin"))
                .andExpect(status().isOk());
    }
    
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    @DisplayName("USERロールで管理者画面にアクセスできない")
    void insertMainWithUser() throws Exception {
        mvc.perform(get("/admin"))
                .andExpect(status().isForbidden());
    }
    
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    @DisplayName("新規登録のテスト")
    void signupConfirm() throws Exception {
        mvc.perform(post("/signup")
                .with(csrf())
                .param("userId", "user")
                .param("password", "useruser")
                .param("email", "user@sample.com")
                .param("name", "ユーザー")
        		.param("dateOfBirth", "2022-12-21")
        		.param("zipCode", "602-0881")
        		.param("address", "京都府京都市上京区京都御苑３"))
                .andExpect(status().isOk());
    }
}
