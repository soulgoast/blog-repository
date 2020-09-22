package com.qunce.kafka.started;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * @ClassName KafkaProducer
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/22 11:14
 * @ModifyDate 2020/9/22 11:14
 * @Version 1.0
 */
public class KafkaProducerDemo {

    @Test
    public void producer() {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "10.8.11.65:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 10);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        for (int i = 1; i < 600; i++) {
            // 参数1：topic名，参数2：消息文本；ProducerRecord多个重载的构造方法
            kafkaProducer.send(new ProducerRecord<>("quickstart-events", "message" + i));
            System.out.println("发送消息：message" + i);
        }
        kafkaProducer.flush();
        kafkaProducer.close();

    }

}
