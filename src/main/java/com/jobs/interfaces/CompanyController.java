package com.jobs.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobs.domain.ComapnyService;
import com.jobs.interfaces.request.CompanyDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CompanyController {

	private final ComapnyService comapnyService;

	@PostMapping("/companies")
	public ResponseEntity<Void> createCompany(@RequestBody CompanyDto companyDto) {
		comapnyService.create(companyDto);
		return ResponseEntity.status(201).build();
	}

	@DeleteMapping("/companies/{company_id}")
	public ResponseEntity<Void> deleteCompany(@PathVariable Long company_id) {
		comapnyService.remove(company_id);
		return ResponseEntity.noContent().build();
	}

}
