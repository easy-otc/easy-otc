package com.easytech.otc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropertiesConfig {

    @Value("${env.mock}")
    private static boolean mock;

    public static boolean isMock() {
        return mock;
    }
}