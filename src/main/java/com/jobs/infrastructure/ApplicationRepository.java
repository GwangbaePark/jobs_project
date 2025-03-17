package com.jobs.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobs.infrastructure.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

	boolean existsByJobSeekerIdAndJobDescriptionId(Long jobSeekerId, Long jobDescriptionId);

	List<Application> findByJobSeekerId(Long jobSeekerId);

	List<Application> findByCompanyId(Long companyId);
}
