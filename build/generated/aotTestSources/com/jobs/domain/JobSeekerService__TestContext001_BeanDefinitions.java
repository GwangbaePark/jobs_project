package com.jobs.domain;

import com.jobs.infrastructure.JobSeekerRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JobSeekerService}.
 */
@Generated
public class JobSeekerService__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'jobSeekerService'.
   */
  private static BeanInstanceSupplier<JobSeekerService> getJobSeekerServiceInstanceSupplier() {
    return BeanInstanceSupplier.<JobSeekerService>forConstructor(JobSeekerRepository.class)
            .withGenerator((registeredBean, args) -> new JobSeekerService(args.get(0)));
  }

  /**
   * Get the bean definition for 'jobSeekerService'.
   */
  public static BeanDefinition getJobSeekerServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JobSeekerService.class);
    beanDefinition.setInstanceSupplier(getJobSeekerServiceInstanceSupplier());
    return beanDefinition;
  }
}
