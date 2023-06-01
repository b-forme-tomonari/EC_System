package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログイン画面のコントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
public class LoginController {

	/**
	 * ログイン画面を表示
	 * @return
	 */
    @GetMapping("/login")
    public String getLogin() {
        return "login/login";
    }
}
