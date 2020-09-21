/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @ClassName PublisherTest
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/16 9:00
 * @ModifyDate 2019/12/16 9:00
 * @Version 1.0
 */
public class PublisherTest {

    private static final Logger logger = LoggerFactory.getLogger(PublisherTest.class);

    public static void main(String[] args) throws MqttException {
        String HOST = "tcp://10.8.40.248:1883";
        String TOPIC = "TY/HZX";
        String userName = "admin";
        String passWord = "public";
        String clientid = UUID.randomUUID().toString().replaceAll("-", "");

        String message = "google";

        MqttClient mqttClient = new MqttClient(HOST, clientid, new MemoryPersistence());


        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        options.setKeepAliveInterval(20);
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                message.getId();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

        mqttClient.connect(options);
        MqttTopic mqttTopic = mqttClient.getTopic(TOPIC);

        for (int i = 0; i < 100; i++) {
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(1);
            mqttMessage.setRetained(false);

            mqttMessage.setPayload((message + i).getBytes());
            MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
            int messageId = token.getMessageId();
            System.out.println("消息ID:" + messageId);
            token.waitForCompletion();
            logger.info("message is published completely! "+ token.isComplete());
        }

    }


}
