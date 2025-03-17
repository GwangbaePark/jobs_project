package com.jobs.interfaces;

import com.jobs.domain.ComapnyService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CompanyController}.
 */
@Generated
public class CompanyController__TestContext001_BeanDefinitions {
  /**
   * Get the bean instance supplier for 'companyController'.
   */
  private static BeanInstanceSupplier<CompanyController> getCompanyControllerInstanceSupplier() {
    return BeanInstanceSupplier.<CompanyController>forConstructor(ComapnyService.class)
            .withGenerator((registeredBean, args) -> new CompanyController(args.get(0)));
  }

  /**
   * Get the bean definition for 'companyController'.
   */
  public static BeanDefinition getCompanyControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CompanyController.class);
    beanDefinition.setInstanceSupplier(getCompanyControllerInstanceSupplier());
    return beanDefinition;
  }
}
