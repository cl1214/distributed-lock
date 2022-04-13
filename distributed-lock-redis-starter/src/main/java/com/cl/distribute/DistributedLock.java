package com.cl.distribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
public class DistributedLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private DefaultRedisScript<Long> script;

    /**
     * 加锁
     *
     * @param   key 缓存key
     * @Param   value 缓存value
     * @Param   timeOut 超时时间
     * @return  java.lang.Boolean 是否成功
     * @date    2022/3/26 13:54
     */
    public Boolean lock(String key, String value, long timeOut) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeOut, TimeUnit.MINUTES);
    }


    /**
     * 解锁
     *
     * @param   key 缓存key
     * @Param   value 缓存值
     * @return  java.lang.Boolean 是否成功
     * @date    2022/3/26 14:08
     */
    public Boolean unLock(String key, String value) {
        Long result = redisTemplate.execute(script, Arrays.asList(key), value);
        return result == 1;
    }
}
