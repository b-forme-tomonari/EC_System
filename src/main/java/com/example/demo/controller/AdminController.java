package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.EcService;
import com.example.demo.form.UserForm;

/**
 * 管理者画面の顧客情報と注文情報コントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
public class AdminController {

	@Autowired
	private EcService ecService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/**
	 * 顧客情報画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/customer")
	public String getCustomer(Model model) {
		
		// ユーザー情報を取得
		List<MUser> userList = ecService.getUserAll();

		// Modelに登録
		model.addAttribute("userList", userList);
		
		return "admin/customer";
	}

	/**
	 * 顧客詳細情報画面を表示
	 * @param form
	 * @param model
	 * @param userId
	 * @return 
	 */
	@GetMapping("/admin/customer/detail/{userId:.+}")
	public String getCustomerDetail(UserForm form, Model model, @PathVariable("userId") String userId) {

		// ユーザー情報を1件取得
		MUser user = ecService.getUserOne(userId);
		// 請求先情報を1件取得
		MBilling bill = ecService.findBillingOne(userId);
		user.setPassword(null);
	  
		// MUserをformに変換
		form = modelMapper.map(user, UserForm.class);
		
		form.setBillingName(bill.getBillingName());			// 請求先氏名
		form.setBillingZipCode(bill.getBillingZipCode());	// 郵便番号
		form.setBillingAddress(bill.getBillingAddress());	// 請求先住所
		form.setBillingNumber(bill.getBillingNumber());		// 請求先電話番号
		
		// Modelに登録
		model.addAttribute("userDetailForm", form);

		return "admin/customer_detail";
	}
	
	/**
	 * 注文情報画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/order")
	public String getAdminOrder(Model model) {

		// 購入履歴を取得
		List<MHistory> historyList = ecService.findHistoryMany(null);

		// Modelに登録
		model.addAttribute("historyList", historyList);

		return "admin/order";
	}
}
