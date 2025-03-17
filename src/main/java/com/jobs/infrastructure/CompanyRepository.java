package com.jobs.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobs.infrastructure.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Optional<Company> findByCompanyRegistrationNumber(String companyRegistrationNumber);
}