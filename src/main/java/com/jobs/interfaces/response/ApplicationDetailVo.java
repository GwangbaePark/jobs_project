package com.jobs.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDetailVo {

	private Long companyId;
	private String companyName;
	private String companyRegistrationNumber;
	private Long jobDescriptionId;
	private String jobDescriptionTitle;
}
