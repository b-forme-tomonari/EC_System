package com.example.demo.controller;

import java.util.List;
import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.service.EcService;
import com.example.demo.form.GoodsForm;
import com.example.demo.form.GroupOrder;

/**
 * 管理者画面の商品情報コントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
public class AdminGoodsController {
	
	@Autowired
	private EcService ecService;
	
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * 商品一覧画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/goods")
	public String getAdminGoods(Model model) {
		
		// 商品情報の取得
		List<MGoods> goodsList = ecService.findGoodsMany();

		// Modelに登録
		model.addAttribute("goodsList", goodsList);
		
		return "admin/goods";
	}
	
	/**
	 * 商品詳細画面を表示
	 * @param model
	 * @param goodsId
	 * @return
	 */
	@GetMapping("/admin/goods/{goodsId:.+}")
	public String getGoods(Model model, @PathVariable("goodsId") String goodsId) {
		
		// 商品情報を1件取得
		MGoods goods = ecService.findGoodsOne(goodsId);

		// Modelに登録
		model.addAttribute("goods", goods);

		return "admin/goods_detail";
	}
	
	/**
	 * 商品編集画面を表示
	 * @param form
	 * @param model
	 * @param goodsId
	 * @return
	 */
	@GetMapping("/admin/goods/edit/{goodsId:.+}")
	public String getEditGoods(GoodsForm form, Model model, @PathVariable("goodsId") String goodsId) {
		
		// ユーザー情報を1件取得
		MGoods goods = ecService.findGoodsOne(goodsId);
		
		// MUserをformに変換
		form = modelMapper.map(goods, GoodsForm.class);
		
		// Modelに登録
		model.addAttribute("goodsForm", form);
		
		return "admin/goods_edit";
	}
	
	/**
	 * 商品編集情報を送信
	 * @param model
	 * @param locale
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/admin/goods/edit")
	public String postEditGoods(Model model, Locale locale,
			@ModelAttribute @Validated(GroupOrder.class) GoodsForm form,
			BindingResult bindingResult) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		
		// 入力エラーの場合
		if (bindingResult.hasErrors()) {
			// 商品編集画面に遷移
			model.addAttribute("GoodsForm", form);
			return "admin/goods_edit";
		}
		
		// formをMGoodsクラスに変換
		MGoods goods = modelMapper.map(form, MGoods.class);
		goods.setUserId(userId); // ユーザーID
		
		// 商品情報を1件更新
		ecService.updateGoodsOne(goods);
		
		return "redirect:/admin/goods";
	
	}
	
	/**
	 * 商品登録画面を表示
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/goods/register")
	public String getRegisterGoods(GoodsForm form, Model model) {
		
		return "admin/goods_register";
	}
	
	/**
	 * 商品登録画面を表示
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/admin/goods/register")
	public String postRegisterGoods(Model model,
			@ModelAttribute @Validated(GroupOrder.class) GoodsForm form,
			BindingResult bindingResult) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		
		// 入力エラーの場合
		if (bindingResult.hasErrors()) {
			// 商品登録画面に遷移
			model.addAttribute("GoodsForm", form);
			return "admin/goods_register";
		}
		
		// formをMGoodsクラスに変換
		MGoods goods = modelMapper.map(form, MGoods.class);
		goods.setUserId(userId); // ユーザーID
		
		// 商品情報を1件追加
		ecService.insertGoodsOne(goods);

		return "redirect:/admin/goods";
	
	}
	
	/**
	 * 登録されている商品を削除
	 * @param form
	 * @param model
	 * @param goodsId
	 * @return
	 */
	@GetMapping("/admin/goods/delete/{goodsId:.+}")
	public String getDeleteGoods(GoodsForm form, Model model, @PathVariable("goodsId") String goodsId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName(); // ログインユーザーID
		MGoods goods = new MGoods();
		goods.setGoodsId(goodsId);	  	// 商品ID
		goods.setUserId(userId);		// ユーザーID
		
		// ユーザー情報を1件削除
		ecService.deleteGoodsOne(goods);
	
		return "redirect:/admin/goods";
	}
}
