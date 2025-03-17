package com.jobs.interfaces;

import com.jobs.domain.ApplicationService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ApplicationController}.
 */
@Generated
public class ApplicationController__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'applicationController'.
   */
  private static BeanInstanceSupplier<ApplicationController> getApplicationControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ApplicationController>forConstructor(ApplicationService.class)
            .withGenerator((registeredBean, args) -> new ApplicationController(args.get(0)));
  }

  /**
   * Get the bean definition for 'applicationController'.
   */
  public static BeanDefinition getApplicationControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApplicationController.class);
    beanDefinition.setInstanceSupplier(getApplicationControllerInstanceSupplier());
    return beanDefinition;
  }
}
