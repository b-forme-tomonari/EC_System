package com.example.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MUser;
import com.example.demo.domain.model.RecaptchaResult;
import com.example.demo.domain.service.EcService;
import com.example.demo.form.GroupOrder;
import com.example.demo.form.SignupForm;

/**
 * 新規登録画面のコントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
public class SignupController {

    @Autowired
    private EcService ecService;

    @Autowired
    private ModelMapper modelMapper;
    
    /**
     * 新規登録画面を表示
     * @param model
     * @param form
     * @return
     */
    @GetMapping("/signup")
    public String getSignup(Model model, @ModelAttribute SignupForm form) {
        return "user/signup";
    }
    
    /**
     * 新規登録情報を送信
     * @param model
     * @param form
     * @param bindingResult
     * @return
     */
    @PostMapping("/signup")
    @Transactional
    public String postSignup(Model model,
    		@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
            BindingResult bindingResult) {
    	
    	String url = "https://www.google.com/recaptcha/api/siteverify?secret=" + "6LeytwIkAAAAAAHu7iOf6t9dhB5qFDldmLAbTUOS" + 
   			 "&response=" + form.getRecaptchaResponse();
    	RestTemplate restTemplate = new RestTemplate();
    	RecaptchaResult result = restTemplate.getForObject(url, RecaptchaResult.class);
    	
    	// reCAPTCHAによる判定
        if(!result.isSuccess()) {
        	if(0.5 >= result.getScore()) {
        		return getSignup(model, form);
        	}
        }
        if (bindingResult.hasErrors()) {
            // 新規登録画面に遷移
            return getSignup(model, form);
        }

        // formをMUserクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        // ユーザー情報を1件追加
        ecService.insertOne(user);
        
        MBilling bill = new MBilling(user.getUserId(), "", "", "", "");
        
        // 請求先情報を1件追加
        ecService.insertBillingOne(bill);
       
        // ユーザー権限を1件追加
        ecService.insertUserRoleOne(user.getUserId());

        return "redirect:/login";
    }
}
