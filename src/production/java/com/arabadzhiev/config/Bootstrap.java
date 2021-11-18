package com.arabadzhiev.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


public class Bootstrap implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		container.getServletRegistration("default").addMapping("/resources/*");
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfiguration.class);
		container.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext webServletContext = 
				new AnnotationConfigWebApplicationContext();
		webServletContext.register(WebServletContextConfiguration.class);
		ServletRegistration.Dynamic dispatcher =
				container.addServlet("springDispatcher", new DispatcherServlet(webServletContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
		
		
	}

}
