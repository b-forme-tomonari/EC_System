package com.example.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login          // フォーム認証設定
                .loginProcessingUrl("/login")  // ユーザーID・パスワードの送信先URL
                .loginPage("/login")           // ログインのURL
                .usernameParameter("userId")   
                .passwordParameter("password") 
                .defaultSuccessUrl("/home")    // ログイン成功後のリダイレクト先URL
                .failureUrl("/login?error")    // ログイン失敗後のリダイレクト先URL
                .permitAll()                   // 未ログイン状態のログイン画面にアクセス許可
        ).logout(logout -> logout
        		.logoutUrl("/logout")          // ログアウトのURL
                .logoutSuccessUrl("/login")    // ログアウト成功後のリダイレクト先URL
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
        ).authorizeHttpRequests(authz -> authz // URLごとの認可設定
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .mvcMatchers("/home").permitAll()          // ホーム画面はログイン無しでアクセス可能
                .mvcMatchers("/signup").permitAll()        // 新規登録画面はログイン無しでアクセス可能
                .mvcMatchers("/image/**").permitAll()      // 画像はログイン無しでアクセス可能
                .mvcMatchers("/admin/**").hasRole("ADMIN") // 管理者のみアクセス可能
                .anyRequest().authenticated()         
        );
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
