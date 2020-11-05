package com.qunce.kafka.started;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @ClassName KafkaConsumerDemo
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/22 11:22
 * @ModifyDate 2020/9/22 11:22
 * @Version 1.0
 */
public class KafkaConsumerDemo {

    public static final String topic = "quickstart-events";

    @Test
    public void consumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.8.11.65:9092");
        props.put("group.id", "test");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        props.put(ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG, 1000);

        //KafkaConsumer类不是线程安全的
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
        try{
            while (true) {
                //拉取消息
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        }finally{
            consumer.close();
        }
    }

}
