package com.jobs.domain.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jobs.infrastructure.entity.JobSeeker;
import com.jobs.interfaces.response.JobSeekerVo;

@Mapper(componentModel = "spring")
public interface JobSeekerMapper {

	JobSeekerMapper INSTANCE = Mappers.getMapper(JobSeekerMapper.class);

	JobSeekerVo toVo(JobSeeker jobSeeker);

	List<JobSeekerVo> toVoList(List<JobSeeker> jobSeekers);
}
