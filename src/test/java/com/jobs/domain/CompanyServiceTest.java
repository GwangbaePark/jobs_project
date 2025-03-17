package com.jobs.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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
import com.jobs.infrastructure.CompanyRepository;
import com.jobs.infrastructure.entity.Company;
import com.jobs.interfaces.request.CompanyDto;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

	@Mock
	private CompanyRepository companyRepository;

	@InjectMocks
	private ComapnyService companyService;

	private CompanyDto validCompanyDto;
	private Company existingCompany;
	private final String COMPANY_REGISTRATION_NUMBER = "123-45-67890";
	private final Long COMPANY_ID = 1L;

	@BeforeEach
	void setUp() {
		String COMPANY_NAME = "테스트 회사";
		validCompanyDto = new CompanyDto(COMPANY_NAME, COMPANY_REGISTRATION_NUMBER);
		existingCompany = new Company(COMPANY_NAME, COMPANY_REGISTRATION_NUMBER);
	}

	@Test
	@DisplayName("회사 생성 성공 테스트")
	void createCompanySuccess() {
		// given
		when(companyRepository.findByCompanyRegistrationNumber(COMPANY_REGISTRATION_NUMBER)).thenReturn(null);

		// when
		companyService.create(validCompanyDto);

		// then
		verify(companyRepository, times(1)).findByCompanyRegistrationNumber(COMPANY_REGISTRATION_NUMBER);
		verify(companyRepository, times(1)).save(any(Company.class));
	}

	@Test
	@DisplayName("이미 존재하는 회사 등록번호로 생성 시 예외 발생")
	void createCompanyWithExistingRegistrationNumber() {
		// given
		when(companyRepository.findByCompanyRegistrationNumber(COMPANY_REGISTRATION_NUMBER)).thenReturn(
				Optional.ofNullable(existingCompany));

		// when & then
		CustomException exception = assertThrows(CustomException.class, () -> {
			companyService.create(validCompanyDto);
		});

		verify(companyRepository, times(1)).findByCompanyRegistrationNumber(COMPANY_REGISTRATION_NUMBER);
		verify(companyRepository, times(0)).save(any(Company.class));
		assert exception.getErrorCode() == CustomErrorCode.ALREADY_EXIST_COMPANY;
	}

	@Test
	@DisplayName("회사 삭제 성공 테스트")
	void removeCompanySuccess() {
		// given
		when(companyRepository.findById(COMPANY_ID)).thenReturn(Optional.of(existingCompany));

		// when
		companyService.remove(COMPANY_ID);

		// then
		verify(companyRepository, times(1)).findById(COMPANY_ID);
		verify(companyRepository, times(1)).deleteById(COMPANY_ID);
	}

	@Test
	@DisplayName("존재하지 않는 회사 ID로 삭제 시 예외 발생")
	void removeNonExistingCompany() {
		// given
		when(companyRepository.findById(COMPANY_ID)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class, () -> {
			companyService.remove(COMPANY_ID);
		});

		verify(companyRepository, times(1)).findById(COMPANY_ID);
		verify(companyRepository, times(0)).deleteById(COMPANY_ID);
		assert exception.getErrorCode() == CustomErrorCode.JOB_SEEKER_NOT_FOUND;
	}
}