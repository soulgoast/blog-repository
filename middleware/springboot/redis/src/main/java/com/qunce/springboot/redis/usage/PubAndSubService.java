package com.qunce.springboot.redis.usage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PubAndSubService {

    private final RedisTemplate redisTemplate;

    public void publish() {
        String message = "message";
        redisTemplate.convertAndSend("aaa/bbb", message);
        log.info("消息发送完成");
    }


}
