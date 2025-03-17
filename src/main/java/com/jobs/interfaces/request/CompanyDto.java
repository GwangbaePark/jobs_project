package com.jobs.interfaces.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record CompanyDto(
		@NotEmpty(message = "이름 입력은 필수입니다.")
		@Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,20}$", message = "이름은 한글, 영어, 숫자로 이루어진 1~20자여야 합니다.")
		String name,

		@NotEmpty(message = "코드 입력은 필수입니다.")
		@Pattern(regexp = "^\\d{3}-\\d{2}-\\d{5}$", message = "형식이 올바르지 않습니다. (예: 123-45-67890)")
		String companyRegistrationNumber
) {
}
