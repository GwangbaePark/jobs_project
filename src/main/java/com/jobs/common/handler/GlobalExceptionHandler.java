package com.jobs.common.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jobs.common.exception.CustomException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Map<String, String>> handleCustomException(CustomException ex) {
		Map<String, String> response = new HashMap<>();
		response.put("code", ex.getErrorCode().getCode());
		response.put("message", ex.getErrorCode().getMessage());

		return ResponseEntity
				.status(ex.getErrorCode().getHttpStatus())
				.body(response);
	}
}
