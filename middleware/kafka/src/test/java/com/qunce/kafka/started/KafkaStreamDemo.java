package com.qunce.kafka.started;

import org.apache.kafka.streams.StreamsConfig;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName KafkaStreamDemo
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/24 10:51
 * @ModifyDate 2020/9/24 10:51
 * @Version 1.0
 */
public class KafkaStreamDemo {

    @Test
    public void stream() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "10.8.11.65:9092");

    }

}
