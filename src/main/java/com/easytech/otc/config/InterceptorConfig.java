package com.easytech.otc.config;

import com.easytech.otc.mvc.protocol.ACLInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ACLInterceptor()).addPathPatterns("/api/v1/**");
        super.addInterceptors(registry);
    }
}