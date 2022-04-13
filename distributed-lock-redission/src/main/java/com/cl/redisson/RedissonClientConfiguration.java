package com.cl.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonClientConfiguration {

    @Value("${redisson.url}")
    private String url;

    @Value("${redisson.password}")
    private String password;

    @Bean
    public RedissonClient getClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(url);
//        config.useSingleServer().setPassword(password);
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
