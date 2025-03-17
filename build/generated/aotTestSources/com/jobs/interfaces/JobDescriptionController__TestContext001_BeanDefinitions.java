package com.jobs.interfaces;

import com.jobs.domain.JobDescriptionService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JobDescriptionController}.
 */
@Generated
public class JobDescriptionController__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'jobDescriptionController'.
   */
  private static BeanInstanceSupplier<JobDescriptionController> getJobDescriptionControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<JobDescriptionController>forConstructor(JobDescriptionService.class)
            .withGenerator((registeredBean, args) -> new JobDescriptionController(args.get(0)));
  }

  /**
   * Get the bean definition for 'jobDescriptionController'.
   */
  public static BeanDefinition getJobDescriptionControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JobDescriptionController.class);
    beanDefinition.setInstanceSupplier(getJobDescriptionControllerInstanceSupplier());
    return beanDefinition;
  }
}
