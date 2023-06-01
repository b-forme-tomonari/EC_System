package com.example.demo.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.EcService;

/**
 * バリデータクラスの実装クラス
 * @author K.Tomonari
 *
 */
public class UnusedUserIdValidator implements ConstraintValidator<UnusedUserId, String> {

    @Autowired
    private EcService ecService;

    public void initialize(UnusedUserId constraintAnnotation) {
    }

    /**
     * 検証ロジックの実装
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
    	// ユーザー情報を1件取得
        MUser user = ecService.getUserOne(value);
        if(user == null) {
            return true;
        }
        
        return false;
    }
}