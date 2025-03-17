package com.jobs.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jobs.common.enums.CustomErrorCode;
import com.jobs.common.exception.CustomException;
import com.jobs.infrastructure.ApplicationRepository;
import com.jobs.infrastructure.CompanyRepository;
import com.jobs.infrastructure.JobDescriptionRepository;
import com.jobs.infrastructure.JobSeekerRepository;
import com.jobs.infrastructure.entity.Application;
import com.jobs.infrastructure.entity.Company;
import com.jobs.infrastructure.entity.JobDescription;
import com.jobs.infrastructure.entity.JobSeeker;
import com.jobs.interfaces.request.ApplicationDto;
import com.jobs.interfaces.response.ApplicationDetailVo;
import com.jobs.interfaces.response.ApplicationVo;
import com.jobs.interfaces.response.JobDescriptionResponseVo;
import com.jobs.interfaces.response.JobSeekerResponseVo;

import java.util.Map;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationService {

	private final ApplicationRepository applicationRepository;
	private final JobSeekerRepository jobSeekerRepository;
	private final CompanyRepository companyRepository;
	private final JobDescriptionRepository jobDescriptionRepository;

	@Transactional
	public void apply(ApplicationDto applicationDto) {

		JobSeeker jobSeeker = jobSeekerRepository.findById(applicationDto.userId()).orElseThrow(()->new CustomException(
				CustomErrorCode.JOB_SEEKER_NOT_FOUND));

		Company company = companyRepository.findById(applicationDto.companyId()).orElseThrow(()->new CustomException(
				CustomErrorCode.COMPANY_NOT_FOUND));

		JobDescription jobDescription = jobDescriptionRepository.findById(applicationDto.JobDescriptionId()).orElseThrow(
				()->new CustomException(CustomErrorCode.JOB_DESCRIPTION_NOT_FOUND));

		if(applicationRepository.existsByJobSeekerIdAndJobDescriptionId(applicationDto.userId(),
				applicationDto.JobDescriptionId())) {
			throw new CustomException(CustomErrorCode.ALREADY_APPLY);
		}

		applicationRepository.save(new Application(jobSeeker, company, jobDescription));

	}

	public ApplicationVo getApplicationsByJobSeeker(Long jobSeekerId) {
		JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
				.orElseThrow(() -> new CustomException(CustomErrorCode.JOB_SEEKER_NOT_FOUND));

		List<Application> applications = applicationRepository.findByJobSeekerId(jobSeekerId);

		List<ApplicationDetailVo> applicationDetails = applications.stream()
				.map(app -> ApplicationDetailVo.builder()
								.companyId(app.getCompany().getId())
								.companyName(app.getCompany().getName())
								.companyRegistrationNumber(app.getCompany().getCompanyRegistrationNumber())
								.jobDescriptionId(app.getJobDescription().getId())
								.jobDescriptionTitle(app.getJobDescription().getTitle())
								.build()
				)
				.collect(Collectors.toList());

		return ApplicationVo.builder()
				.totalApplyCount(applicationDetails.size())
				.applies(applicationDetails)
				.build();
	}

	public List<JobDescriptionResponseVo> getApplicantsByCompanyId(Long companyId) {
		Company company = companyRepository.findById(companyId).orElseThrow(()->new CustomException(
				CustomErrorCode.COMPANY_NOT_FOUND));

		List<Application> applications = applicationRepository.findByCompanyId(companyId);

		Map<Long, List<JobSeekerResponseVo>> jobSeekerMap = applications.stream()
				.collect(Collectors.groupingBy(
						app -> app.getJobDescription().getId(),
						Collectors.mapping(app -> JobSeekerResponseVo.builder()
								.jobSeekerId(app.getJobSeeker().getId())
								.jobSeekerName(app.getJobSeeker().getName())
								.email(app.getJobSeeker().getEmail())
								.build(), Collectors.toList())
				));

		return company.getJobDescriptions().stream()
				.map(jobDescription -> JobDescriptionResponseVo.builder()
								.jobDescriptionId(jobDescription.getId())
						.jobDescriptionTitle(jobDescription.getTitle())
								.jobSeekers(jobSeekerMap.getOrDefault(jobDescription.getId(), List.of()))
								.build()
				)
				.collect(Collectors.toList());
	}
}
