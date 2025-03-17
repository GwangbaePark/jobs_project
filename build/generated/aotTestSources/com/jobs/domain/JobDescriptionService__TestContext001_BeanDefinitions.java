package com.jobs.domain;

import com.jobs.infrastructure.CompanyRepository;
import com.jobs.infrastructure.JobDescriptionRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JobDescriptionService}.
 */
@Generated
public class JobDescriptionService__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'jobDescriptionService'.
   */
  private static BeanInstanceSupplier<JobDescriptionService> getJobDescriptionServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JobDescriptionService>forConstructor(JobDescriptionRepository.class, CompanyRepository.class)
            .withGenerator((registeredBean, args) -> new JobDescriptionService(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'jobDescriptionService'.
   */
  public static BeanDefinition getJobDescriptionServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JobDescriptionService.class);
    beanDefinition.setInstanceSupplier(getJobDescriptionServiceInstanceSupplier());
    return beanDefinition;
  }
}
