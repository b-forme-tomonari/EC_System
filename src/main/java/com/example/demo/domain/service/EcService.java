package com.example.demo.domain.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.model.MUser;

/**
 * データベースから情報取得するインターフェイス
 * @author K.Tomonari
 *
 */
public interface EcService {

	/** ログインユーザー取得 */
	public Optional<MUser> getLoginUser(String userId);
	
    /** ユーザー取得 */
    public List<MUser> getUserAll();
    
    /** ユーザー取得(1件) */
    public MUser getUserOne(String userId);
    
    /** ユーザー登録(1件) */
    public int insertOne(MUser user);
    
    /** ユーザー更新(1件) */
    public int updateOne(MUser user);
    
    /** ユーザー権限登録(1件) */
    public int insertUserRoleOne(String userId);
    
    /** 商品取得 */
    public List<MGoods> findGoodsMany();
    
    /** 商品取得(1件) */
    public MGoods findGoodsOne(String goodsId);
    
    /** 商品登録(1件) */
    public int insertGoodsOne(MGoods goods);
    
    /** 商品更新(1件) */
    public int updateGoodsOne(MGoods goods);
    
    /** 商品削除(1件) */
    public int deleteGoodsOne(MGoods goods);
    
    /** 購入履歴取得 */
    public List<MHistory> findHistoryMany(String userId);
    
    /** 購入履歴登録(1件) */
    public int insertHistoryOne(MHistory history);
    
    /** 請求先取得(1件) */
    public MBilling findBillingOne(String userId);
    
    /** 請求先登録(1件) */
    public int insertBillingOne(MBilling billing);
    
    /** 請求先更新(1件) */
    public int updateBillingOne(MBilling billing);
}
