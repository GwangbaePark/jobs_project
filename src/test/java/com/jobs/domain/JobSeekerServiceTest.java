package com.jobs.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jobs.common.enums.CustomErrorCode;
import com.jobs.common.exception.CustomException;
import com.jobs.infrastructure.JobSeekerRepository;
import com.jobs.infrastructure.entity.JobSeeker;
import com.jobs.interfaces.request.JobSeekerDto;
import com.jobs.interfaces.response.JobSeekerVo;

@ExtendWith(MockitoExtension.class)
public class JobSeekerServiceTest {

	@Mock
	private JobSeekerRepository jobSeekerRepository;

	@InjectMocks
	private JobSeekerService jobSeekerService;

	@Test
	@DisplayName("구직자 등록 성공")
	void createJobSeeker_Success() {
		// given
		String name = "홍길동";
		String email = "hong@example.com";
		String password = "password123";
		JobSeekerDto jobSeekerDto = new JobSeekerDto(name, email, password);

		when(jobSeekerRepository.findByName(name)).thenReturn(null);
		when(jobSeekerRepository.save(any(JobSeeker.class))).thenReturn(new JobSeeker(name, email, password));

		// when
		jobSeekerService.create(jobSeekerDto);

		// then
		verify(jobSeekerRepository, times(1)).findByName(name);
		verify(jobSeekerRepository, times(1)).save(any(JobSeeker.class));
	}

	@Test
	@DisplayName("구직자 등록 실패 - 이미 존재하는 이름")
	void createJobSeeker_AlreadyExistUser() {
		// given
		String name = "wan티드01";
		String email = "ai@wantedlab.com";
		String password = "password123";
		JobSeekerDto jobSeekerDto = new JobSeekerDto(name, email, password);

		when(jobSeekerRepository.findByName(name)).thenReturn(Optional.of(new JobSeeker(name, email, password)));

		// when & then
		CustomException exception = assertThrows(CustomException.class,
				() -> jobSeekerService.create(jobSeekerDto));

		assertEquals(CustomErrorCode.ALREADY_EXIST_USER, exception.getErrorCode());
		verify(jobSeekerRepository, times(0)).save(any(JobSeeker.class));
	}

	@Test
	@DisplayName("모든 구직자 목록 조회")
	void getJobSeekers_Success() {
		// given
		JobSeeker jobSeeker1 = new JobSeeker("wan티드01", "ai1@wantedlab.com", "password123");
		JobSeeker jobSeeker2 = new JobSeeker("wan티드02", "ai2@wantedlab.com", "password456");

		// JobSeeker 객체에 ID 설정
		try {
			java.lang.reflect.Field field = JobSeeker.class.getDeclaredField("id");
			field.setAccessible(true);
			field.set(jobSeeker1, 1L);
			field.set(jobSeeker2, 2L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		List<JobSeeker> jobSeekers = Arrays.asList(jobSeeker1, jobSeeker2);

		when(jobSeekerRepository.findAll()).thenReturn(jobSeekers);

		// when
		List<JobSeekerVo> result = jobSeekerService.getJobSeekers();

		// then
		assertEquals(2, result.size());
		assertEquals("wan티드01", result.get(0).getName());
		assertEquals("wan티드02", result.get(1).getName());
		assertEquals("ai1@wantedlab.com", result.get(0).getEmail());
		assertEquals("ai2@wantedlab.com", result.get(1).getEmail());
		assertEquals(1L, result.get(0).getId());
		assertEquals(2L, result.get(1).getId());
		verify(jobSeekerRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("구직자 삭제 성공")
	void removeJobSeeker_Success() {
		// given
		Long jobSeekerId = 1L;
		JobSeeker jobSeeker = new JobSeeker("wan티드01", "ai1@wantedlab.com", "password123");

		when(jobSeekerRepository.findById(jobSeekerId)).thenReturn(Optional.of(jobSeeker));

		// when
		jobSeekerService.remove(jobSeekerId);

		// then
		verify(jobSeekerRepository, times(1)).findById(jobSeekerId);
		verify(jobSeekerRepository, times(1)).deleteById(jobSeekerId);
	}

	@Test
	@DisplayName("구직자 삭제 실패 - 구직자 정보 없음")
	void removeJobSeeker_JobSeekerNotFound() {
		// given
		Long jobSeekerId = 999L;

		when(jobSeekerRepository.findById(jobSeekerId)).thenReturn(Optional.empty());

		// when & then
		CustomException exception = assertThrows(CustomException.class,
				() -> jobSeekerService.remove(jobSeekerId));

		assertEquals(CustomErrorCode.JOB_SEEKER_NOT_FOUND, exception.getErrorCode());
		verify(jobSeekerRepository, times(0)).deleteById(any());
	}
}