package com.qunce.redis.jedis;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class RedisReceiver {

    @Autowired
    private RedisService redisService;

    public void receiveMessage(String message) {
        //这里是收到通道的消息之后执行的方法
    }

}
