package com.qunce.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Service
public class RedisOperation {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisOperation() {
        System.out.println("执行构造方法");
    }

    @PostConstruct
    public void start() {
        System.out.println("执行post方法，而且只执行一次");
    }



}
