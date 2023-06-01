package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 請求先
 * @author K.Tomonari
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MBilling {
    private String userId;         // ユーザーID
    private String billingName;    // 請求先氏名
    private String billingZipCode; // 請求先郵便番号
    private String billingAddress; // 請求先住所
    private String billingNumber;  // 請求先電話番号
}
