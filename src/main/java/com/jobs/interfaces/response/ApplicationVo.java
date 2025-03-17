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
public class ApplicationVo {
	private int totalApplyCount = 0;

	private List<ApplicationDetailVo> applies;
}
