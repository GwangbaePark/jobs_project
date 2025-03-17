package com.jobs.domain;

import com.jobs.infrastructure.ApplicationRepository;
import com.jobs.infrastructure.CompanyRepository;
import com.jobs.infrastructure.JobDescriptionRepository;
import com.jobs.infrastructure.JobSeekerRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ApplicationService}.
 */
@Generated
public class ApplicationService__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'applicationService'.
   */
  private static BeanInstanceSupplier<ApplicationService> getApplicationServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ApplicationService>forConstructor(ApplicationRepository.class, JobSeekerRepository.class, CompanyRepository.class, JobDescriptionRepository.class)
            .withGenerator((registeredBean, args) -> new ApplicationService(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'applicationService'.
   */
  public static BeanDefinition getApplicationServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApplicationService.class);
    beanDefinition.setInstanceSupplier(getApplicationServiceInstanceSupplier());
    return beanDefinition;
  }
}
