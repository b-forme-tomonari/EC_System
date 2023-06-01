package com.example.demo.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * ユーザーのFormクラス
 * @author K.Tomonari
 *
 */
@Data
public class UserForm {
	@NotBlank(groups = ValidGroup1.class)
    private String userId; // ユーザー名
    
    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 4, max = 32, groups = ValidGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
    private String password; // パスワード
    
    @NotBlank(groups = ValidGroup1.class)
    @Email(groups = ValidGroup2.class)
    private String email; // メールアドレス
    
    @NotBlank(groups = ValidGroup1.class)
    private String name; // 氏名
    
    @NotNull(groups = ValidGroup1.class)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dateOfBirth; // 生年月日
    
    @NotBlank(groups = ValidGroup1.class)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", groups = ValidGroup2.class)
    private String zipCode; // 郵便番号
    
    @NotBlank(groups = ValidGroup1.class)
    private String address; // 住所
    
    private String billingName;    // 請求先住所
    private String billingZipCode; // 請求先郵便番号
    private String billingAddress; // 請求先住所
    private String billingNumber;  // 請求先電話番号
}