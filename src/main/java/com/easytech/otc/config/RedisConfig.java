package com.easytech.otc.config;

import com.easytech.otc.manager.redis.support.RedisTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Description:
 * Author: Hank
 * Date: 2018/7/19 22:57
 */
@Configuration
public class RedisConfig {
    @Autowired
    private Environment envir;

    @Bean
    public RedisTool getRedisTool() {
        return new RedisTool(envir.getProperty("redis.host", "127.0.0.1"), Integer.valueOf(envir.getProperty("redis.port", "6379")));
    }
}
