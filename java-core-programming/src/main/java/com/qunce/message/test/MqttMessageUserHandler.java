/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.test;

import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MqttMessageUserHandler
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/17 14:16
 * @ModifyDate 2019/12/17 14:16
 * @Version 1.0
 */
@Component
public class MqttMessageUserHandler implements MqttCallbackExtended {

    private static final Logger logger = LoggerFactory.getLogger(MqttMessageUserHandler.class);

    private MqttClient mqttClient;

    private Map<String, Integer> msgTQ;

    private MqttConnectOptions options;

    @Autowired
    private com.qunce.message.test.MQTTClient client;

    int counter = 1;

    public void setConfig(MqttClient mqttClient, Map<String, Integer> msgTQ, MqttConnectOptions options) {
        this.mqttClient = mqttClient;
        this.msgTQ = msgTQ;
        this.options = options;
    }

    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        if(reconnect) {
            logger.info("重新连接成功，连接的URL为：{}。", serverURI);
        } else {
            logger.info("第一次连接成功，连接的URL为：{}。", serverURI);
        }

        try {
            for (String topic : msgTQ.keySet()) {
                mqttClient.subscribe(topic, msgTQ.get(topic));
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        logger.info("连接丢失，异常原因为:");
        cause.printStackTrace();
        boolean flag = true;
        while (flag) {
            try {
                TimeUnit.SECONDS.sleep(1);
                logger.info("尝试重新连接");
                mqttClient.connect(options);
                flag = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        logger.info("第{}次收到消息", counter);
        logger.info("主题{}，收到消息。消息的ID为{}，内容为{}，QOS为{}", topic, message.getId(), new String(message.getPayload(), Charset.defaultCharset()), message.getQos());
        counter++;
        client.receiveMessage(topic, message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) { // 消息发送完成
        logger.info("deliveryComplete:" + token.getMessageId());
    }

}
