package com.qunce.redis.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    public void sendChannelMess(String channel, String message) {
        stringRedisTemplate.convertAndSend(channel, message);
    }

}
