package com.example.demo.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.config.UnusedUserId;

import lombok.Data;

/**
 * 新規登録のFormクラス
 * @author K.Tomonari
 *
 */
@Data
public class SignupForm {
	
	@NotBlank(groups = ValidGroup1.class)
	@UnusedUserId(groups = ValidGroup2.class)
    private String userId; // ユーザーID
    
    @NotBlank(groups = ValidGroup1.class)
    @Length(min = 8, max = 32, groups = ValidGroup2.class)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
    private String password; // パスワード
    
    @NotBlank(groups = ValidGroup1.class)
    @Email(groups = ValidGroup2.class)
    private String email; // メールアドレス
    
    @NotBlank(groups = ValidGroup1.class)
    private String name; // 氏名
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull(groups = ValidGroup1.class)
    private Date dateOfBirth; // 生年月日
    
    @NotBlank(groups = ValidGroup1.class)
    @Pattern(regexp = "^[0-9]{3}-[0-9]{4}$", groups = ValidGroup2.class)
    private String zipCode; // 郵便番号
    
    @NotBlank(groups = ValidGroup1.class)
    private String address; // 住所
    
    private String recaptchaResponse; // reCAPTCHA結果
}