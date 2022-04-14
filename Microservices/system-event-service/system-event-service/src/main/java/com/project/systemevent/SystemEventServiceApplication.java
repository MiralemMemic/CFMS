package com.project.systemevent;

import com.project.systemevent.controller.MyCustomHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SystemEventServiceApplication {
	@Bean
	public MyCustomHandlerInterceptor myCustomHandlerInterceptor() {
		return new MyCustomHandlerInterceptor();
	}
	@Bean
	public WebMvcConfigurerAdapter adapter() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(myCustomHandlerInterceptor());
			}
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(SystemEventServiceApplication.class, args);
	}
}
