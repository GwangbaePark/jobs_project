package com.jobs.domain;

import com.jobs.infrastructure.CompanyRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ComapnyService}.
 */
@Generated
public class ComapnyService__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'comapnyService'.
   */
  private static BeanInstanceSupplier<ComapnyService> getComapnyServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ComapnyService>forConstructor(CompanyRepository.class)
            .withGenerator((registeredBean, args) -> new ComapnyService(args.get(0)));
  }

  /**
   * Get the bean definition for 'comapnyService'.
   */
  public static BeanDefinition getComapnyServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ComapnyService.class);
    beanDefinition.setInstanceSupplier(getComapnyServiceInstanceSupplier());
    return beanDefinition;
  }
}
