package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.service.EcService;

/**
 * 管理者画面のグラフ表示コントローラークラス
 * @author K.Tomonari
 *
 */
@Controller
public class AdminGraphController {
	
	@Autowired
	private EcService ecService;
	
	/**
	 * 月別売上画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		
		// 購入履歴を取得
		List<MHistory> historyList = ecService.findHistoryMany(null);
		int priceList[] = new int[12];
		// 月別売上目標金額
		int targetList[] = {10000, 8000, 10000, 4000, 7000, 12000,
							10000, 8000, 5000, 8000, 7000, 12000};
		
		// 購入履歴から月別売上を計算
		for(MHistory history : historyList) {
			Date purchaseDate = history.getPurchaseDate();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(purchaseDate);
			priceList[calendar.get(Calendar.MONTH)] += 
					history.getPrice() * history.getQuantity(); 
		}
		// Modelに登録
		model.addAttribute("priceList", priceList);   // 月別売上
		model.addAttribute("targetList", targetList); // 月別売上目標

		return "admin/admin";
	}
	
	/**
	 * 売上ランキング画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/admin/rank")
	public String getRank(Model model) {
		
		// 購入履歴を取得
		List<MHistory> historyList = ecService.findHistoryMany(null);
		 Map<String, Integer> rankMap = new HashMap<String, Integer>();

		// 購入履歴から商品別売上ランキングを取得
		for(MHistory history : historyList) {
			Integer goodsSales = rankMap.get(history.getGoodsId());
			// ランキングリストに商品データがない場合は商品データを追加
			if(goodsSales == null) {
				rankMap.put(history.getGoodsId() ,
						history.getPrice() * history.getQuantity());
			}
			else {
				goodsSales += history.getPrice() * history.getQuantity();
				rankMap.replace(history.getGoodsId(), goodsSales);
			}
		}
		
		List<String> goodsList = new ArrayList<>();
		
		// 価格を降順に並び替え
		rankMap = rankMap.entrySet().stream().sorted(
				Collections.reverseOrder(Map.Entry.comparingByValue())
				).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), 
				         (k, v) -> v, LinkedHashMap::new));
		
		// 商品名を取得して商品名リストに追加
		rankMap.entrySet().stream().forEachOrdered(rank -> {
			// 商品情報を1件取得
			MGoods goods = ecService.findGoodsOne(rank.getKey());
			goodsList.add(goods.getGoodsName());
		});
		
		// 価格リストに代入
		List<Integer> priceList = new ArrayList<>(rankMap.values());
		
		// Modelに登録
		model.addAttribute("goodsList", goodsList); // 商品名リスト
		model.addAttribute("priceList", priceList); // 売上リスト
		
		return "admin/rank";
	}
}
