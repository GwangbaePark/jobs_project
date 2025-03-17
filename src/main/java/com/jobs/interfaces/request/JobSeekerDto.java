package com.jobs.interfaces.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record JobSeekerDto(

		@NotEmpty(message = "이름 입력은 필수 입니다.")
		@Pattern(regexp = "^[a-zA-Z0-9가-힣]+$", message = "이름은 한글, 영어, 숫자만 포함할 수 있습니다.")
		@Size(min = 1, max = 16, message = "이름은 1자 이상 16자 이하로 입력해야 합니다.")
		String name,

		@NotEmpty(message = "이메일 입력은 필수 입니다.")
		@Email
		String email,

		@NotEmpty(message = "비밀번호 입력은 필수 입니다.")
		@Pattern(
				regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,16}$",
				message = "비밀번호는 8자 이상 16자 이하이며, 영문 대소문자, 숫자, 특수문자 중 최소 3가지 이상을 조합해야 합니다."
		)
		String password
) {
}
