package com.jobs.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "job_seeker_id", nullable = false)
	private JobSeeker jobSeeker;

	@ManyToOne
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@ManyToOne
	@JoinColumn(name = "job_description_id", nullable = false)
	private JobDescription jobDescription;

	public Application(JobSeeker jobSeeker, Company company, JobDescription jobDescription) {
		this.jobSeeker = jobSeeker;
		this.company = company;
		this.jobDescription = jobDescription;
	}
}
