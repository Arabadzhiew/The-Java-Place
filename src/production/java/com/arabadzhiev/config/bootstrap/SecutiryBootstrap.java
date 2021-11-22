package com.arabadzhiev.config.bootstrap;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecutiryBootstrap extends AbstractSecurityWebApplicationInitializer{
	
}
