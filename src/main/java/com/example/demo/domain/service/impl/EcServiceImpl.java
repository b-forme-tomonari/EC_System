package com.example.demo.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.MBilling;
import com.example.demo.domain.model.MGoods;
import com.example.demo.domain.model.MHistory;
import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.EcService;
import com.example.demo.repository.EcMapper;

/**
 * データベースから情報取得する実装クラス
 * @author K.Tomonari
 *
 */
@Service
public class EcServiceImpl implements EcService {

    @Autowired
    private EcMapper mapper;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Override
    public Optional<MUser> getLoginUser(String userId) {
        return mapper.findLoginUser(userId);
    }
    
    @Override
    public List<MUser> getUserAll() {
        return mapper.findAll();
    }
    
    @Override
    public MUser getUserOne(String userId) {
        return mapper.findOne(userId);
    }
    
    @Override
    public int insertOne(MUser user) {
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        return mapper.insertOne(user);
    }
    
    @Override
    public int updateOne(MUser user) {
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        return mapper.updateOne(user);
    }
    
    @Override
    public int insertUserRoleOne(String userId) {
    	return mapper.insertUserRoleOne(userId);
    }
    
    @Override
    public List<MGoods> findGoodsMany(){
    	return mapper.findGoodsMany();
    }
    
    @Override
    public MGoods findGoodsOne(String goodsId){
    	return mapper.findGoodsOne(goodsId);
    }
    
    @Override
    public int insertGoodsOne(MGoods goods) {
        return mapper.insertGoodsOne(goods);
    }
    
    @Override
    public int updateGoodsOne(MGoods goods) {
        return mapper.updateGoodsOne(goods);
    }
    
    @Override
    public int deleteGoodsOne(MGoods goods) {
    	return mapper.deleteGoodsOne(goods);
    }
    
    @Override
    public List<MHistory> findHistoryMany(String userId){
    	return mapper.findHistoryMany(userId);
    }
    
    @Override
    public int insertHistoryOne(MHistory history) {
    	return mapper.insertHistoryOne(history);
    }
    
    public MBilling findBillingOne(String userId) {
    	return mapper.findBillingOne(userId);
    }
    
    @Override
    public int insertBillingOne(MBilling billing) {
    	return mapper.insertBillingOne(billing);
    }

    @Override
    public int updateBillingOne(MBilling billing) {
        return mapper.updateBillingOne(billing);
    }
}
