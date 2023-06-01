package com.example.demo.domain.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * ユーザー
 * @author K.Tomonari
 *
 */
@Data
public class MUser implements Serializable {
    private String userId;    // ユーザーID
    private String password;  // パスワード
    private String email;     // メールアドレス
    private String name;      // 氏名
    private Date dateOfBirth; // 生年月日
    private String zipCode;   // 郵便番号
    private String address;   // 住所
    private String roleName;  // ユーザー権限
}
