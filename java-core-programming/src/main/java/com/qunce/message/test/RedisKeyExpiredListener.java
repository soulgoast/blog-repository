/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

/**
 * @ClassName RedisKeyExpiredListener
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/13 11:01
 * @ModifyDate 2019/12/13 11:01
 * @Version 1.0
 */
@Component
public class RedisKeyExpiredListener extends JedisPubSub {

    private final static Logger logger = LoggerFactory.getLogger(RedisKeyExpiredListener.class);

    @Autowired
    private MQTTClient mqttClient;

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage pattern " + pattern + "" + channel + " " + message);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
    }

    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        Jedis jedis = pool.getResource();
        jedis.psubscribe(new RedisKeyExpiredListener(), "__keyevent@0__:expired");
    }

    @Test
    public void test() {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        Jedis jedis = pool.getResource();
        jedis.set("notify", "你还在吗");
        jedis.expire("notify", 10);
    }

}
