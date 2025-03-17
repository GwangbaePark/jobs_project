package com.jobs.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobSeekerResponseVo {

	private Long jobSeekerId;
	private String jobSeekerName;
	private String email;
}
