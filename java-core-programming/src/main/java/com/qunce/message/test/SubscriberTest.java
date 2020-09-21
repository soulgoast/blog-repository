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

import java.nio.charset.Charset;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SubscriberTest
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/16 9:00
 * @ModifyDate 2019/12/16 9:00
 * @Version 1.0
 */
public class SubscriberTest {

    private static final Logger logger = LoggerFactory.getLogger(SubscriberTest.class);

    public static void main(String[] args) throws MqttException {
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

        mqttClient.setCallback(new MqttCallbackExtended() {
            int i = 1;

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if(reconnect) {
                    logger.info("重新连接成功，连接的URL为：{}。", serverURI);
                } else {
                    logger.info("第一次连接成功，连接的URL为：{}。", serverURI);
                }

                try {
                    mqttClient.subscribe(TOPIC, 0);
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                logger.info("连接丢失，异常原因为:");
                cause.printStackTrace();
                try {
                    TimeUnit.SECONDS.sleep(1);
                    logger.info("尝试重新连接");
                    mqttClient.connect(options);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                logger.info("第{}次收到消息", i);
                logger.info("主题{}，收到消息。消息的ID为{}，内容为{}，QOS为{}", topic, message.getId(), new String(message.getPayload(), Charset.defaultCharset()), message.getQos());
                i++;
                int a = 1 / 0;
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) { // 消息发送完成
                logger.info("deliveryComplete:" + token.getMessageId());
            }
        });

        mqttClient.connect(options);
    }

}
