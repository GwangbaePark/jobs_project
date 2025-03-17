package com.jobs.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jobs.common.enums.CustomErrorCode;
import com.jobs.common.exception.CustomException;
import com.jobs.infrastructure.CompanyRepository;
import com.jobs.infrastructure.JobDescriptionRepository;
import com.jobs.infrastructure.entity.Company;
import com.jobs.infrastructure.entity.JobDescription;
import com.jobs.interfaces.request.JobDescriptionDto;

@ExtendWith(MockitoExtension.class)
public class JobDescriptionServiceTest {

	@Mock
	private JobDescriptionRepository jobDescriptionRepository;

	@Mock
	private CompanyRepository companyRepository;

	@InjectMocks
	private JobDescriptionService jobDescriptionService;

	@Test
	@DisplayName("채용공고 등록 성공")
	void createJobDescription_Success() {
		// given
		Long companyId = 1L;
		String title = "백엔드 개발자 채용";
		JobDescriptionDto jobDescriptionDto = new JobDescriptionDto(title);

		Company company = new Company("원티드랩", "123-45-67890");

		setPrivateField(company, "id", companyId);

		when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));

		JobDescription savedJobDescription = new JobDescription(title, company);
		setPrivateField(savedJobDescription, "id", 1L);

		when(jobDescriptionRepository.save(any(JobDescription.class))).thenReturn(savedJobDescription);

		// when
		jobDescriptionService.create(companyId, jobDescriptionDto);

		// then
		verify(companyRepository, times(1)).findById(companyId);
		verify(jobDescriptionRepository, times(1)).save(any(JobDescription.class));
	}

	@Test
	@DisplayName("채용공고 등록 실패 - 회사 정보 없음")
	void createJobDescription_CompanyNotFound() {
		// given
		Long companyId = 999L;
		JobDescriptionDto jobDescriptionDto = new JobDescriptionDto("백엔드 개발자 채용");

		when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class,
				() -> jobDescriptionService.create(companyId, jobDescriptionDto));

		assert exception.getErrorCode() == CustomErrorCode.COMPANY_NOT_FOUND;
		verify(jobDescriptionRepository, times(0)).save(any(JobDescription.class));
	}

	@Test
	@DisplayName("채용공고 삭제 성공")
	void removeJobDescription_Success() {
		// given
		Long companyId = 1L;
		Long jobDescriptionId = 1L;

		Company company = new Company("원티드랩", "123-45-67890");
		setPrivateField(company, "id", companyId);

		JobDescription jobDescription = new JobDescription("백엔드 개발자 채용", company);
		setPrivateField(jobDescription, "id", jobDescriptionId);

		when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
		when(jobDescriptionRepository.findById(jobDescriptionId)).thenReturn(Optional.of(jobDescription));

		// when
		jobDescriptionService.remove(companyId, jobDescriptionId);

		// then
		verify(companyRepository, times(1)).findById(companyId);
		verify(jobDescriptionRepository, times(1)).findById(jobDescriptionId);
		verify(jobDescriptionRepository, times(1)).delete(jobDescription);
	}

	@Test
	@DisplayName("채용공고 삭제 실패 - 회사 정보 없음")
	void removeJobDescription_CompanyNotFound() {
		// given
		Long companyId = 999L;
		Long jobDescriptionId = 1L;

		when(companyRepository.findById(companyId)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class,
				() -> jobDescriptionService.remove(companyId, jobDescriptionId));

		assert exception.getErrorCode() == CustomErrorCode.COMPANY_NOT_FOUND;
		verify(jobDescriptionRepository, times(0)).findById(any());
		verify(jobDescriptionRepository, times(0)).delete(any());
	}

	@Test
	@DisplayName("채용공고 삭제 실패 - 채용공고 정보 없음")
	void removeJobDescription_JobDescriptionNotFound() {
		// given
		Long companyId = 1L;
		Long jobDescriptionId = 999L;

		Company company = new Company("원티드랩", "123-45-67890");
		setPrivateField(company, "id", companyId);

		when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
		when(jobDescriptionRepository.findById(jobDescriptionId)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class,
				() -> jobDescriptionService.remove(companyId, jobDescriptionId));

		assert exception.getErrorCode() == CustomErrorCode.JOB_DESCRIPTION_NOT_FOUND;
		verify(jobDescriptionRepository, times(0)).delete(any());
	}

	@Test
	@DisplayName("채용공고 삭제 실패 - 권한 없음")
	void removeJobDescription_NoPermission() {
		// given
		Long companyId = 1L;
		Long otherCompanyId = 2L;
		Long jobDescriptionId = 1L;

		Company company = new Company("원티드랩", "123-45-67890");
		setPrivateField(company, "id", companyId);

		Company otherCompany = new Company("다른회사", "987-65-43210");
		setPrivateField(otherCompany, "id", otherCompanyId);

		JobDescription jobDescription = new JobDescription("백엔드 개발자 채용", otherCompany);
		setPrivateField(jobDescription, "id", jobDescriptionId);

		when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));
		when(jobDescriptionRepository.findById(jobDescriptionId)).thenReturn(Optional.of(jobDescription));

		// when & then
		CustomException exception = assertThrows(CustomException.class,
				() -> jobDescriptionService.remove(companyId, jobDescriptionId));

		assert exception.getErrorCode() == CustomErrorCode.NO_PERMISSION;
		verify(jobDescriptionRepository, times(0)).delete(any());
	}

	private void setPrivateField(Object target, String fieldName, Object value) {
		try {
			java.lang.reflect.Field field = target.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(target, value);
		} catch (Exception e) {
			throw new RuntimeException("Failed to set field: " + fieldName, e);
		}
	}
}