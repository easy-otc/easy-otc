package com.easytech.otc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.easytech.otc.mvc.protocol.ACLInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Autowired
    private ACLInterceptor aclInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(aclInterceptor).addPathPatterns("/api/v1/**");
        super.addInterceptors(registry);
    }
}