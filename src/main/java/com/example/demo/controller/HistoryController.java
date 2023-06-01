package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.service.EcService;

/**
 * 購入履歴画面のコントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
@RequestMapping("/user")
public class HistoryController {

    @Autowired
    private EcService ecService;

    /**
     * 購入履歴画面を表示
     * @param model
     * @param userId
     * @return
     */
    @GetMapping("/history/{userId:.+}")
    public String getHistory(Model model ,@PathVariable("userId") String userId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
    	// ログインユーザーとパラメータ情報が一致しない場合
    	if(!(userId.equals(auth.getName()))) {
    		return "error/errors";
    	}
    	
    	// 購入履歴を取得
        List<MHistory> historyList = ecService.findHistoryMany(userId);

        // Modelに登録
        model.addAttribute("historyList", historyList);

        return "user/history";
    }
}
