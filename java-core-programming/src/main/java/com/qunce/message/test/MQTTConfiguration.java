/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.test;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.UUID;

/**
 * @ClassName MQTTClient
 * @Description MQTT服务客户端连接对象
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/16 11:20
 * @ModifyDate 2019/12/16 11:20
 * @Version 1.0
 */
@Configuration
public class MQTTConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberTest.class);

    @Autowired
    private MqttMessageUserHandler mqttMessageUserHandler;

    @Bean
    public MqttClient getInstance() throws MqttException {
        String HOST = "tcp://10.8.40.248:1883";
        String TOPIC = "TY/HZX";
        String userName = "admin";
        String passWord = "public";


        String clientid = UUID.randomUUID().toString().replaceAll("-", "");
        MqttClient mqttClient = new MqttClient(HOST, clientid, new MemoryPersistence());

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        options.setMaxInflight(10); // TODO
        options.setCleanSession(false);
        HashMap<String, Integer> msgTQ = new HashMap<>();
        mqttMessageUserHandler.setConfig(mqttClient, msgTQ, options);
        mqttClient.setCallback(mqttMessageUserHandler);

        mqttClient.connect(options);
        return mqttClient;
    }

}
