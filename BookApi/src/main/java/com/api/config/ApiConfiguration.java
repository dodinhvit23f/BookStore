package com.api.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
public class ApiConfiguration{
	
	@Bean(name = "messageResource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
		messageResource.setBasename("classpath:messages");
		messageResource.setDefaultEncoding("UTF-8");
		messageResource.setUseCodeAsDefaultMessage(true);
		return messageResource;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(this.getMessageSource());
	    return bean;
	}
	
	@Bean
	public Jackson2ObjectMapperBuilder jsonCustomizer() {
		return new Jackson2ObjectMapperBuilder() .serializationInclusion(JsonInclude.Include.NON_NULL)
				.serializationInclusion(JsonInclude.Include.NON_EMPTY);
	}
		

}
