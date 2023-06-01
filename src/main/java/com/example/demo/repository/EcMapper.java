package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.model.MUser;

/**
 * データベースと接続するためのインターフェイス
 * @author K.Tomonari
 *
 */
@Mapper
public interface EcMapper {
	
    /** ログインユーザー取得 */
    public Optional<MUser> findLoginUser(String userId);
    
    /** ユーザー取得 */
    public List<MUser> findAll();
    
    /** ユーザー取得(1件) */
    public MUser findOne(String userId);
    
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
