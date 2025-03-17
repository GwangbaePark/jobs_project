package com.jobs.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSeekerVo {

	private Long id;

	private String name;

	private String email;

	private String password;
}
