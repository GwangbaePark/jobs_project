package com.jobs.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.domain.JobSeekerService;
import com.jobs.interfaces.request.JobSeekerDto;
import com.jobs.interfaces.response.JobSeekerVo;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JobSeekerController {

	private final JobSeekerService jobSeekerService;

	@PostMapping("/job-seeker")
	public ResponseEntity<Void> createJobSeeker(@RequestBody JobSeekerDto jobSeekerDto) {
		jobSeekerService.create(jobSeekerDto);

		return ResponseEntity.status(201).build();
	}

	@GetMapping("/job-seekers")
	public List<JobSeekerVo> getJobSeekers() {
		return jobSeekerService.getJobSeekers();
	}

	@DeleteMapping("/job-seekers/{job_seeker_id}")
	public ResponseEntity<Void> deleteJobSeeker(@PathVariable("job_seeker_id") Long jobSeekerId) {
		jobSeekerService.remove(jobSeekerId);
		return ResponseEntity.noContent().build();
	}
}
