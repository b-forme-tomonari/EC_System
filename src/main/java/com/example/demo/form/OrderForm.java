package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 注文のFormクラス
 * @author K.Tomonari
 *
 */
@Data
public class OrderForm {
	
    @NotBlank(groups = ValidGroup1.class)
    private String name; // 氏名
    
    @NotBlank(groups = ValidGroup1.class)
    @Email(groups = ValidGroup2.class)
    private String email; // メールアドレス
    
    @NotBlank(groups = ValidGroup1.class)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", groups = ValidGroup2.class)
    private String zipCode; // 郵便番号
    
    @NotBlank(groups = ValidGroup1.class)
    private String address; // 住所
    
    @NotBlank(groups = ValidGroup1.class)
    private String billingName; // 請求先氏名
    
    @NotBlank(groups = ValidGroup1.class)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", groups = ValidGroup2.class)
    private String billingZipCode; // 請求先郵便番号
    
    @NotBlank(groups = ValidGroup1.class)
    private String billingAddress; // 請求先住所
    
    @NotBlank(groups = ValidGroup1.class)
    @Pattern(regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}", groups = ValidGroup2.class)
    private String billingNumber;  // 請求先電話番号
    
    @NotBlank(groups = ValidGroup1.class)
    private String creditName; // カード名義
    
    @NotBlank(groups = ValidGroup1.class)
    @CreditCardNumber(groups = ValidGroup2.class)
    private String creditNumber; // クレジットカード番号
    
    @NotBlank(groups = ValidGroup1.class)
    @Pattern(regexp = "^[0-9]{2}/[0-9]{2}$", groups = ValidGroup2.class)
    private String expirationDate; // 有効期限
    
    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 3, max = 4, groups = ValidGroup2.class)
    private String securityCode; // セキュリティコード
    
    private boolean billingKeep;  // 請求先保存
}