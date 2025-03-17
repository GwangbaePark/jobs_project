package com.jobs.domain;

import org.springframework.stereotype.Service;

import com.jobs.common.enums.CustomErrorCode;
import com.jobs.common.exception.CustomException;
import com.jobs.infrastructure.CompanyRepository;
import com.jobs.infrastructure.JobDescriptionRepository;
import com.jobs.infrastructure.entity.Company;
import com.jobs.infrastructure.entity.JobDescription;
import com.jobs.interfaces.request.JobDescriptionDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobDescriptionService {

	private final JobDescriptionRepository jobDescriptionRepository;
	private final CompanyRepository companyRepository;

	@Transactional
	public void create(Long companyId, JobDescriptionDto jobDescriptionDto) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new CustomException(CustomErrorCode.COMPANY_NOT_FOUND));

		jobDescriptionRepository.save(new JobDescription(jobDescriptionDto.title(), company));
	}

	@Transactional
	public void remove(Long companyId, Long jobDescriptionId) {
		Company company = companyRepository.findById(companyId)
				.orElseThrow(() -> new CustomException(CustomErrorCode.COMPANY_NOT_FOUND));

		JobDescription jobDescription = jobDescriptionRepository.findById(jobDescriptionId)
				.orElseThrow(() -> new CustomException(CustomErrorCode.JOB_DESCRIPTION_NOT_FOUND));

		if (!jobDescription.getCompany().getId().equals(company.getId())) {
			throw new CustomException(CustomErrorCode.NO_PERMISSION);
		}

		jobDescriptionRepository.delete(jobDescription);
	}
}
