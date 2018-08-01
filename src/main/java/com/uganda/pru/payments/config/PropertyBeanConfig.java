/**
 * 
 */
package com.uganda.pru.payments.config;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author megha
 *
 */
@Configuration
public class PropertyBeanConfig {

	@Bean(name = "BarclaysToWorkbench")
	public static PropertiesFactoryBean BarclaysToWorkbench() {
	        PropertiesFactoryBean bean = new PropertiesFactoryBean();
	        bean.setLocation(new ClassPathResource(
	                "BarclaysToWorkbench.properties"));
	        return bean;
	}
	
	@Bean(name = "CentenaryToWorkbench")
	public static PropertiesFactoryBean CentenaryToWorkbench() {
	        PropertiesFactoryBean bean = new PropertiesFactoryBean();
	        bean.setLocation(new ClassPathResource(
	                "CentenaryToWorkbench.properties"));
	        return bean;
	}
	
	@Bean(name = "MobileMoneyToWorkbench")
	public static PropertiesFactoryBean MobileMoneyToWorkbench() {
	        PropertiesFactoryBean bean = new PropertiesFactoryBean();
	        bean.setLocation(new ClassPathResource(
	                "MobileMoneyToWorkbench.properties"));
	        return bean;
	}
}
