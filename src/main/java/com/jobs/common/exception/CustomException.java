package com.jobs.common.exception;

import com.jobs.common.enums.CustomErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{

	private final CustomErrorCode errorCode;

	public CustomException(CustomErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}
}
