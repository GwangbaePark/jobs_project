package com.jobs.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.domain.JobDescriptionService;
import com.jobs.interfaces.request.JobDescriptionDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JobDescriptionController {

	private final JobDescriptionService jobDescriptionService;

	@PostMapping("/companies/{company_id}/job-description")
	public ResponseEntity<Void> createJobDescription(
			@PathVariable Long company_id,
			@RequestBody @Valid JobDescriptionDto jobDescriptionDto
	){

		jobDescriptionService.create(company_id, jobDescriptionDto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/companies/{company_id}/job-description/{job_description_id}")
	public ResponseEntity<Void> deleteJobDescription(
			@PathVariable("company_id") Long companyId,
			@PathVariable("job_description_id") Long jobDescriptionId
	){
		jobDescriptionService.remove(companyId, jobDescriptionId);
		return ResponseEntity.noContent().build();
	}
}
