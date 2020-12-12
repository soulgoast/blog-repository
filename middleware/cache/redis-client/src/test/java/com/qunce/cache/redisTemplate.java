package com.qunce.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class redisTemplate {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getKey() {
        redisTemplate.opsForValue().set("google", "youtube", 10, TimeUnit.MINUTES);
        Object google = redisTemplate.opsForValue().get("mainBoard01");
        System.out.println(google);
    }
}
