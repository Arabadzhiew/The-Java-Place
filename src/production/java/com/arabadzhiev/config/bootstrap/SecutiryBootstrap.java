package com.arabadzhiev.config.bootstrap;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

@Order(2)
public class SecutiryBootstrap extends AbstractSecurityWebApplicationInitializer{
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext container) {
		FilterRegistration.Dynamic registration = container.addFilter(
				"encodingFilter", new CharacterEncodingFilter("UTF-8", true));
		registration.addMappingForUrlPatterns(null, false, "/*");
		
		
	}
}
