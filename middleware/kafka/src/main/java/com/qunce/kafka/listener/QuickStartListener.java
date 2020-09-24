package com.qunce.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName QuickStartConsumer
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/24 14:32
 * @ModifyDate 2020/9/24 14:32
 * @Version 1.0
 */
@Component
@Slf4j
public class QuickStartListener {

    @KafkaListener(topics = "quickstart-events")
    public void onMessage(String message) {
        log.info("收到消息：{}", message);
    }

}
