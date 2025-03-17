package com.jobs;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;

/**
 * Bean definitions for {@link WantedjobsApplication}.
 */
@Generated
public class WantedjobsApplication__TestContext001_BeanDefinitions {
  /**
   * Get the bean definition for 'wantedjobsApplication'.
   */
  public static BeanDefinition getWantedjobsApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WantedjobsApplication.class);
    beanDefinition.setTargetType(WantedjobsApplication.class);
    ConfigurationClassUtils.initializeConfigurationClass(WantedjobsApplication.class);
    beanDefinition.setInstanceSupplier(WantedjobsApplication$$SpringCGLIB$$0::new);
    return beanDefinition;
  }
}
