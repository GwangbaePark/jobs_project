package com.jobs.domain.mapper;

import com.jobs.infrastructure.entity.JobSeeker;
import com.jobs.interfaces.response.JobSeekerVo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-08T18:18:11+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.jar, environment: Java 21.0.3 (Amazon.com Inc.)"
)
@Component
public class JobSeekerMapperImpl implements JobSeekerMapper {

    @Override
    public JobSeekerVo toVo(JobSeeker jobSeeker) {
        if ( jobSeeker == null ) {
            return null;
        }

        JobSeekerVo.JobSeekerVoBuilder jobSeekerVo = JobSeekerVo.builder();

        return jobSeekerVo.build();
    }

    @Override
    public List<JobSeekerVo> toVoList(List<JobSeeker> jobSeekers) {
        if ( jobSeekers == null ) {
            return null;
        }

        List<JobSeekerVo> list = new ArrayList<JobSeekerVo>( jobSeekers.size() );
        for ( JobSeeker jobSeeker : jobSeekers ) {
            list.add( toVo( jobSeeker ) );
        }

        return list;
    }
}
