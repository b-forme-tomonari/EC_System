package com.example.demo.domain.service;

public class InvalidReCaptchaTokenException extends Exception {

	public InvalidReCaptchaTokenException(String message) {
		super(message);
	}
}
