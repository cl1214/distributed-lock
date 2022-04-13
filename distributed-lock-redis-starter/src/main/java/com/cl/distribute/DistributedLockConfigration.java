package com.cl.distribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

@ComponentScan
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass(RedisTemplate.class)
@EnableConfigurationProperties(RedisLockProperties.class)
public class DistributedLockConfigration {

    @Autowired
    private RedisLockProperties redisLockProperties;

    @Bean
    public DefaultRedisScript setScript() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new
                ClassPathResource(redisLockProperties.getPath())));
        return script;
    }
}
