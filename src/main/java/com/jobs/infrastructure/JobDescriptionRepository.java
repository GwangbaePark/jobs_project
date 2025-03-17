package com.jobs.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobs.infrastructure.entity.JobDescription;

public interface JobDescriptionRepository extends JpaRepository<JobDescription, Long> {

	Optional<JobDescription> findById(Long id);

	Optional<JobDescription> findByIdAndCompanyId(Long jobDescriptionId, Long companyId);
}
