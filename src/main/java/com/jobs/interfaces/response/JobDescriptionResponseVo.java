package com.jobs.interfaces.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobDescriptionResponseVo {

	private Long jobDescriptionId;
	private String jobDescriptionTitle;
	private List<JobSeekerResponseVo> jobSeekers;
}
