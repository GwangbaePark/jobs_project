package com.jobs.interfaces.request;

public record ApplicationDto(

		Long userId,
		Long companyId,
		Long JobDescriptionId
) {
}
