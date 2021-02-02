package com.qunce.springboot.redis.usage;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@NoArgsConstructor
public class PubAndSubServiceTest {

    @Autowired
    private PubAndSubService pubAndSubService;

    @Test
    public void test() {
        pubAndSubService.publish();
    }


}
