package com.jobs.interfaces.request;

import jakarta.validation.constraints.NotBlank;

public record JobDescriptionDto(
		@NotBlank(message = "채용 공고명은 한 글자 이상 입력해야 합니다.")
		String title
) {
}
