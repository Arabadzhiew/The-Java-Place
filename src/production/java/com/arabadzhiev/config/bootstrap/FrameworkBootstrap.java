package com.arabadzhiev.config.bootstrap;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.arabadzhiev.config.RootContextConfiguration;
import com.arabadzhiev.config.WebServletContextConfiguration;

@Order(1)
public class FrameworkBootstrap implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		container.getServletRegistration("default").addMapping("/resources/*");
		container.getServletRegistration("default").addMapping("/favicon.ico");
		
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfiguration.class);
		container.addListener(new ContextLoaderListener(rootContext));
		
		AnnotationConfigWebApplicationContext webServletContext = 
				new AnnotationConfigWebApplicationContext();
		webServletContext.register(WebServletContextConfiguration.class);
		ServletRegistration.Dynamic dispatcher =
				container.addServlet("springDispatcher", new DispatcherServlet(webServletContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.setMultipartConfig(new MultipartConfigElement(null, 20_971_520L, 41_943_040L, 512_000));
		dispatcher.addMapping("/");
		
		
	}

}
