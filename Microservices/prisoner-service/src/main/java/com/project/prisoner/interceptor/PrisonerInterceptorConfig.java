package com.project.prisoner.interceptor;

import com.project.prisoner.interceptor.PrisonerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class PrisonerInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    PrisonerInterceptor prisonerInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(prisonerInterceptor);
    }
}
