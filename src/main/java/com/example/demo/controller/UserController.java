package com.example.demo.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.EcService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.UserForm;

/**
 * ユーザー情報のコントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private EcService ecService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * ユーザー詳細画面を表示
     * @param form
     * @param model
     * @param userId
     * @return
     */
    @GetMapping("/detail/{userId:.+}")
    public String getUser(UserForm form, Model model, @PathVariable("userId") String userId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     
    	// ログインユーザーとパラメータの情報が一致しない場合
    	if(!(userId.equals(auth.getName()))) {
    		return "error/errors";
    	}
    	
        // ユーザー情報を1件取得
        MUser user = ecService.getUserOne(userId);
        // 請求先情報を1件取得
        MBilling bill = ecService.findBillingOne(userId);
        user.setPassword(null);
      
        // MUserをformに変換
        form = modelMapper.map(user, UserForm.class);
        
        form.setBillingName(bill.getBillingName());       // 請求先氏名
        form.setBillingZipCode(bill.getBillingZipCode()); // 請求先郵便番号
        form.setBillingAddress(bill.getBillingAddress()); // 請求先住所
        form.setBillingNumber(bill.getBillingNumber());   // 請求先電話番号
        
        // Modelに登録
        model.addAttribute("userForm", form);
        
        return "user/user_info";
    }

    
    /**
     * ユーザー編集画面を表示
     * @param form
     * @param model
     * @param userId
     * @return
     */
    @GetMapping("/edit/{userId:.+}")
    public String getEdit(UserForm form, Model model, @PathVariable("userId") String userId) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
    	// ログインユーザーとパラメータの情報が一致しない場合
    	if(!(userId.equals(auth.getName()))) {
    		return "error/errors";
    	}
    	
    	// ユーザーを1件取得
        MUser user = ecService.getUserOne(userId);
        // 請求先を1件取得
        MBilling bill = ecService.findBillingOne(userId);
        user.setPassword(null);

        // MUserをformに変換
        form = modelMapper.map(user, UserForm.class);
        
        form.setBillingName(bill.getBillingName());       // 請求先氏名
        form.setBillingZipCode(bill.getBillingZipCode()); // 請求先郵便番号
        form.setBillingAddress(bill.getBillingAddress()); // 請求先住所
        form.setBillingNumber(bill.getBillingNumber());   // 請求先電話番号

        // Modelに登録
        model.addAttribute("userForm", form);
    	
    	return "user/user_edit";
    
    }
    
    /**
     * ユーザー編集情報を送信
     * @param model
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/edit")
    @Transactional
    public String postEdit(Model model,
    		@ModelAttribute @Validated(GroupOrder.class) UserForm form,
            BindingResult bindingResult) {

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	model.addAttribute("userId", auth.getName());
    	
        if (bindingResult.hasErrors()) {
            // ユーザー編集画面に遷移
        	model.addAttribute("userForm", form);
        	return "user/user_edit";
        }
        
        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);
       
        // ユーザー情報を1件更新
        ecService.updateOne(user);
        
        // formをMBillingクラスに変換
        MBilling bill = modelMapper.map(form, MBilling.class);
        
        // 請求先情報を1件更新
        ecService.updateBillingOne(bill);
        
    	return "user/user_success";
    
    }
}
