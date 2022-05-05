package com.project.prisoner.interceptor;

import com.project.prisoner.interceptor.PrisonerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class PrisonerInterceptorConfig implements WebMvcConfigurer{

    @Autowired
    PrisonerInterceptor prisonerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(prisonerInterceptor);
    }
}
