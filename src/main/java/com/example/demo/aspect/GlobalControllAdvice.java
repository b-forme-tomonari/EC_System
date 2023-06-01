package com.example.demo.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalControllAdvice {

    /**
     *  DataAccessException発生の処理
     */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        model.addAttribute("error", "");
        model.addAttribute("message", "DataAccessExceptionが発生しました");
        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error/error";
    }

    /**
     *  Exception発生の処理
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        model.addAttribute("error", "");
        model.addAttribute("message", "Exceptionが発生しました");
        // HTTPのエラーコード（500）をModelに登録
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error/error";
    }
}