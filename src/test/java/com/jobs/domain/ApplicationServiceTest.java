package com.jobs.domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
import com.jobs.interfaces.response.ApplicationVo;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

	@Mock
	private ApplicationRepository applicationRepository;

	@Mock
	private JobSeekerRepository jobSeekerRepository;

	@Mock
	private CompanyRepository companyRepository;

	@Mock
	private JobDescriptionRepository jobDescriptionRepository;

	@InjectMocks
	private ApplicationService applicationService;

	private JobSeeker jobSeeker;
	private Company company;
	private JobDescription jobDescription;
	private ApplicationDto applicationDto;
	private Application application;

	@BeforeEach
	void setUp() {
		// 테스트 데이터 준비
		jobSeeker = new JobSeeker( "wan티드01", "ai1@wantedlab.com", "password123");

		company = new Company("원티드", "123-45-67890");

		jobDescription = new JobDescription("백엔드 개발자", company);

		List<JobDescription> jobDescriptions = List.of(jobDescription);
		try {
			java.lang.reflect.Field jobDescriptionsField = Company.class.getDeclaredField("jobDescriptions");
			jobDescriptionsField.setAccessible(true);
			jobDescriptionsField.set(company, jobDescriptions);
		} catch (Exception e) {
			throw new RuntimeException("Failed to set jobDescriptions", e);
		}

		applicationDto = new ApplicationDto(1L, 1L, 1L);

		application = new Application(jobSeeker, company, jobDescription);
	}

	@Test
	@DisplayName("지원 성공 테스트")
	void applySuccess() {
		// given
		when(jobSeekerRepository.findById(1L)).thenReturn(Optional.of(jobSeeker));
		when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
		when(jobDescriptionRepository.findById(1L)).thenReturn(Optional.of(jobDescription));
		when(applicationRepository.existsByJobSeekerIdAndJobDescriptionId(1L, 1L)).thenReturn(false);

		// when
		applicationService.apply(applicationDto);

		// then
		verify(applicationRepository, times(1)).save(any(Application.class));
	}

	@Test
	@DisplayName("지원자가 존재하지 않을 경우 예외 발생")
	void applyJobSeekerNotFound() {
		// given
		when(jobSeekerRepository.findById(1L)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class, () -> {
			applicationService.apply(applicationDto);
		});
		assertEquals(CustomErrorCode.JOB_SEEKER_NOT_FOUND, exception.getErrorCode());
	}

	@Test
	@DisplayName("회사가 존재하지 않을 경우 예외 발생")
	void applyCompanyNotFound() {
		// given
		when(jobSeekerRepository.findById(1L)).thenReturn(Optional.of(jobSeeker));
		when(companyRepository.findById(1L)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class, () -> {
			applicationService.apply(applicationDto);
		});
		assertEquals(CustomErrorCode.COMPANY_NOT_FOUND, exception.getErrorCode());
	}

	@Test
	@DisplayName("채용공고가 존재하지 않을 경우 예외 발생")
	void applyJobDescriptionNotFound() {
		// given
		when(jobSeekerRepository.findById(1L)).thenReturn(Optional.of(jobSeeker));
		when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
		when(jobDescriptionRepository.findById(1L)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class, () -> {
			applicationService.apply(applicationDto);
		});
		assertEquals(CustomErrorCode.JOB_DESCRIPTION_NOT_FOUND, exception.getErrorCode());
	}

	@Test
	@DisplayName("이미 지원한 공고에 중복 지원 시 예외 발생")
	void applyAlreadyApplied() {
		// given
		when(jobSeekerRepository.findById(1L)).thenReturn(Optional.of(jobSeeker));
		when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
		when(jobDescriptionRepository.findById(1L)).thenReturn(Optional.of(jobDescription));
		when(applicationRepository.existsByJobSeekerIdAndJobDescriptionId(1L, 1L)).thenReturn(true);

		// when & then
		CustomException exception = assertThrows(CustomException.class, () -> {
			applicationService.apply(applicationDto);
		});
		assertEquals(CustomErrorCode.ALREADY_APPLY, exception.getErrorCode());
	}

	@Test
	@DisplayName("구직자 ID로 지원 내역 조회 성공")
	void getApplicationsByJobSeekerSuccess() {
		// given
		List<Application> applications = Arrays.asList(application);

		when(jobSeekerRepository.findById(1L)).thenReturn(Optional.of(jobSeeker));
		when(applicationRepository.findByJobSeekerId(1L)).thenReturn(applications);

		// when
		ApplicationVo result = applicationService.getApplicationsByJobSeeker(1L);

		// then
		assertNotNull(result);
		assertEquals(1, result.getTotalApplyCount());
		assertEquals(1, result.getApplies().size());
		assertEquals(company.getId(), result.getApplies().get(0).getCompanyId());
		assertEquals(company.getName(), result.getApplies().get(0).getCompanyName());
		assertEquals(jobDescription.getId(), result.getApplies().get(0).getJobDescriptionId());
		assertEquals(jobDescription.getTitle(), result.getApplies().get(0).getJobDescriptionTitle());
	}

	@Test
	@DisplayName("구직자가 존재하지 않을 경우 예외 발생")
	void getApplicationsJobSeekerNotFound() {
		// given
		when(jobSeekerRepository.findById(1L)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class, () -> {
			applicationService.getApplicationsByJobSeeker(1L);
		});
		assertEquals(CustomErrorCode.JOB_SEEKER_NOT_FOUND, exception.getErrorCode());
	}


	@Test
	@DisplayName("회사가 존재하지 않을 경우 예외 발생")
	void getApplicantsCompanyNotFound() {
		// given
		when(companyRepository.findById(1L)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class, () -> {
			applicationService.getApplicantsByCompanyId(1L);
		});
		assertEquals(CustomErrorCode.COMPANY_NOT_FOUND, exception.getErrorCode());
	}
}