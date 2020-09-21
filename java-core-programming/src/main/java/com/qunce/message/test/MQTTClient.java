/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.test;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MQTTClient
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/17 14:12
 * @ModifyDate 2019/12/17 14:12
 * @Version 1.0
 */
@Component
public class MQTTClient {

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private Long overtime = 2L;

    private Map<String, Integer> counter = new HashMap<>();

    // 取第一个
    public void receiveMessage(String topic, MqttMessage message) {
        message.isDuplicate();

    }

    /**
     * 流程描述：
     * 1、
     * @param message
     * @param topicName
     * @param qos
     * @throws MqttException
     */
    public void sendMessage(String message, String topicName, Integer qos)  throws MqttException {
        MqttTopic topic = mqttClient.getTopic(topicName);
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setQos(qos);
        mqttMessage.setPayload(message.getBytes());
        MqttDeliveryToken token = topic.publish(mqttMessage);
        // 放入最后一个
        if(redisTemplate.hasKey(topicName) && redisTemplate.opsForList().size(topicName) > 0) { // 如果队列存在且里面有元素

        } else { // 队列不存在或者没有元素
            redisTemplate.opsForList().leftPush(topicName, message);

        }


    }

    public void putRedis(String topic, String message) {
        redisTemplate.opsForValue().set(topic, message);
        redisTemplate.opsForHash();
        redisTemplate.expire(topic, overtime, TimeUnit.SECONDS);

    }

}
