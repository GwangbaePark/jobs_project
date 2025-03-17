package com.jobs.common.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

	ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "BAD_REQUEST_BODY", "동일한 사용자 아이디가 있습니다."),
	JOB_SEEKER_NOT_FOUND(HttpStatus.NOT_FOUND, "JOB_SEEKER_NOT_FOUND", "해당 구직자를 찾을 수 없습니다."),
	ALREADY_EXIST_COMPANY(HttpStatus.BAD_REQUEST, "BAD_REQUEST_BODY", "동일한 사업자등록번호가 있습니다."),
	COMPANY_NOT_FOUND(HttpStatus.NOT_FOUND, "COMPANY_NOT_FOUND", "해당기업을 찾을 수 없습니다."),
	NO_PERMISSION(HttpStatus.FORBIDDEN, "NO_PERMISSION", "해당 기업에 속하지 않은 채용공고 입니다."),
	JOB_DESCRIPTION_NOT_FOUND(HttpStatus.NOT_FOUND, "JOB_DESCRIPTION_NOT_FOUND", "해당 채용공고를 찾을 수 없습니다."),
	ALREADY_APPLY(HttpStatus.BAD_REQUEST, "ALREADY_APPLY", "이미 지원한 채용공고 입니다.");


	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
