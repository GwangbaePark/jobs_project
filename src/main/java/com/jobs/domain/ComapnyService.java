package com.jobs.domain;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.jobs.common.enums.CustomErrorCode;
import com.jobs.common.exception.CustomException;
import com.jobs.infrastructure.CompanyRepository;
import com.jobs.infrastructure.entity.Company;
import com.jobs.interfaces.request.CompanyDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComapnyService {

	private final CompanyRepository companyRepository;

	@Transactional
	public void create(CompanyDto companyDto) {

		if(!ObjectUtils.isEmpty(companyRepository.findByCompanyRegistrationNumber(companyDto.companyRegistrationNumber()))) {
			throw new CustomException(CustomErrorCode.ALREADY_EXIST_COMPANY);
		}
		companyRepository.save(new Company(companyDto.name(), companyDto.companyRegistrationNumber()));
	}

	@Transactional
	public void remove(Long companyId) {
		if(ObjectUtils.isEmpty(companyRepository.findById(companyId))) {
			throw new CustomException(CustomErrorCode.JOB_SEEKER_NOT_FOUND);
		}
		companyRepository.deleteById(companyId);
	}
}
