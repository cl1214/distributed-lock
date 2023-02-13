package com.cl.service.impl;

import com.cl.distribute.DistributedLock;
import com.cl.service.OrderService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DistributedLock distributedLock;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public Boolean order(Long id) {
        Long uuid = System.currentTimeMillis();

        try {
            Boolean locked = distributedLock.lock(String.valueOf(id), String.valueOf(uuid), 1);
            if (locked == null || !locked) {
                return false;
            }
            System.out.println("====进行下单=====");
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            distributedLock.unLock(String.valueOf(id), String.valueOf(uuid));
        }
        System.out.println("====处理完成=====");

        return true;
    }

    @Override
    public Boolean orderRedisson(Long id) {
        RLock lock = redissonClient.getLock(String.valueOf(id));
        try {
            lock.lock(10, TimeUnit.SECONDS);
            lock.tryLock(1, 1, TimeUnit.DAYS);
            System.out.println("====进行下单=====");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            lock.unlock();
        }
        return true;
    }
}
