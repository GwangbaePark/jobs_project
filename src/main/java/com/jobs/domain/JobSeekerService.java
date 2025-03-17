package com.jobs.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.jobs.common.enums.CustomErrorCode;
import com.jobs.common.exception.CustomException;
import com.jobs.infrastructure.JobSeekerRepository;
import com.jobs.infrastructure.entity.JobSeeker;
import com.jobs.interfaces.request.JobSeekerDto;
import com.jobs.interfaces.response.JobSeekerVo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobSeekerService {

	private final JobSeekerRepository jobSeekerRepository;

	@Transactional
	public void create(JobSeekerDto jobSeekerDto) {

		if(!ObjectUtils.isEmpty(jobSeekerRepository.findByName(jobSeekerDto.name()))) {
			throw new CustomException(CustomErrorCode.ALREADY_EXIST_USER);
		}
		jobSeekerRepository.save(new JobSeeker(jobSeekerDto.name(), jobSeekerDto.email(), jobSeekerDto.password()));
	}

	public List<JobSeekerVo> getJobSeekers() {
		return jobSeekerRepository.findAll().stream().map(
			jobSeeker -> JobSeekerVo.builder()
					.name(jobSeeker.getName())
					.email(jobSeeker.getEmail())
					.password(jobSeeker.getPassword())
					.id(jobSeeker.getId())
					.build()
		).collect(Collectors.toList());
	}

	@Transactional
	public void remove(Long jobSeekerId) {
		if(ObjectUtils.isEmpty(jobSeekerRepository.findById(jobSeekerId))) {
			throw new CustomException(CustomErrorCode.JOB_SEEKER_NOT_FOUND);
		}
		jobSeekerRepository.deleteById(jobSeekerId);
	}
}
