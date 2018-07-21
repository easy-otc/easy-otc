package com.easytech.otc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class PropertiesConfig {


    private static boolean mock;
    @Value("${env.mock}")
    public void setMock(boolean _mock) {
        mock = _mock;
    }
    public static boolean isMock() {
        return mock;
    }
}