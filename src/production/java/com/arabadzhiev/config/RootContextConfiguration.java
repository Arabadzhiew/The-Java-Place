package com.arabadzhiev.config;

import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
@EnableScheduling
@EnableAsync(
		order = 1,
		mode = AdviceMode.PROXY, proxyTargetClass = true
)
@EnableTransactionManagement(
		order = 2,
		mode = AdviceMode.PROXY, proxyTargetClass = true
)
@EnableJpaRepositories(
		basePackages = "com.arabadzhiev.site.repository",
		entityManagerFactoryRef = "entityManagerFactoryBean",
		transactionManagerRef = "jpaTransactionManager"
)
@ComponentScan(
		basePackages = "com.arabadzhiev.site",
		excludeFilters = @ComponentScan.Filter(Controller.class)
)
@Import({SecurityConfiguration.class})
public class RootContextConfiguration implements AsyncConfigurer, SchedulingConfigurer{
	
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}
	
	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
		processor.setValidator(this.localValidatorFactoryBean());
		return processor;
	}
	
	@Bean
	public DataSource springJpaDataSource() {
		JndiDataSourceLookup lookup = new JndiDataSourceLookup();
		return lookup.getDataSource("jdbc/TheJavaPlace");
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
		Map<String, Object> properties = new Hashtable<>();
		properties.put("javax.persistence.schema-generation.database.action", "none");
		//properties.put("hibernate.show_sql", "true");
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(adapter);
		factory.setDataSource(this.springJpaDataSource());
		factory.setPackagesToScan("com.arabadzhiev.site.entity");
		factory.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
		factory.setValidationMode(ValidationMode.NONE);
		factory.setJpaPropertyMap(properties);
		
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager jpaTransactionManager() {
		return new JpaTransactionManager(this.entityManagerFactoryBean().getObject());
	}
	
	@Bean
	public ThreadPoolTaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		
		taskScheduler.setPoolSize(20);
		taskScheduler.setThreadNamePrefix("task-");
		taskScheduler.setAwaitTerminationSeconds(60);
		taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
		
		return taskScheduler;
	}
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		
		mailSender.setUsername("changeMail@gmail.com");
		mailSender.setPassword("changePassword");
		
		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
		
		return mailSender;
	}
	
	@Override 
	public Executor getAsyncExecutor() {
		Executor executor = this.taskScheduler();
		return executor;
	}
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		TaskScheduler taskScheduler = this.taskScheduler();
		taskRegistrar.setTaskScheduler(taskScheduler);
		
	}
}
