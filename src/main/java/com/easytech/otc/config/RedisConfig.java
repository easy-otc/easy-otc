package com.easytech.otc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.easytech.otc.manager.redis.support.RedisTool;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:57
 */
@Configuration
public class RedisConfig {

    @Value("${redis.port}")
    private int    port;

    @Value("${redis.host}")
    private String host;

    @Value("${redis.passwd}")
    private String password;

    @Bean
    public RedisTool getRedisTool() {
        return new RedisTool(host, port);
    }
}