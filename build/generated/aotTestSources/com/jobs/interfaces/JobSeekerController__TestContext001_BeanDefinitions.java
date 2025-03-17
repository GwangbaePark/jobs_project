package com.jobs.interfaces;

import com.jobs.domain.JobSeekerService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JobSeekerController}.
 */
@Generated
public class JobSeekerController__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'jobSeekerController'.
   */
  private static BeanInstanceSupplier<JobSeekerController> getJobSeekerControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JobSeekerController>forConstructor(JobSeekerService.class)
            .withGenerator((registeredBean, args) -> new JobSeekerController(args.get(0)));
  }

  /**
   * Get the bean definition for 'jobSeekerController'.
   */
  public static BeanDefinition getJobSeekerControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JobSeekerController.class);
    beanDefinition.setInstanceSupplier(getJobSeekerControllerInstanceSupplier());
    return beanDefinition;
  }
}
