package com.example.demo.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * reCAPTCHAの結果
 * @author K.Tomonari
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecaptchaResult {
    private boolean success;     // トークン判定
    private String challenge_ts; // タイムスタンプ
    private String hostname;     // ホスト名
    private float score;         // スコア
    private String action;       // アクション名
}