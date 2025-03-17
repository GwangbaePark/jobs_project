package com.jobs.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.domain.ApplicationService;
import com.jobs.interfaces.request.ApplicationDto;
import com.jobs.interfaces.response.ApplicationVo;
import com.jobs.interfaces.response.JobDescriptionResponseVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApplicationController {

	private final ApplicationService applicationService;

	@PostMapping("/usages")
	public ResponseEntity<Void> apply(@RequestBody ApplicationDto applicationDto) {
		applicationService.apply(applicationDto);
		return ResponseEntity.status(201).build();
	}

	@GetMapping("/job-seeker/{job_seeker_id}/applies")
	public ApplicationVo getApplies(@PathVariable("job_seeker_id") Long jobSeekerId) {
		return applicationService.getApplicationsByJobSeeker(jobSeekerId);
	}

	@GetMapping("/{company_id}/applies")
	public List<JobDescriptionResponseVo> getApplicantsByCompany(@PathVariable("company_id") Long companyId) {
		return applicationService.getApplicantsByCompanyId(companyId);
	}
}
