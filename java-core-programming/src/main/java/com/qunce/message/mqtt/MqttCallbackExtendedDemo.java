/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;

/**
 * @ClassName MqttCallbackExtendedDemo
 * @Description
 * MqttCallback的扩展，以允许新的回调而不会破坏现有应用程序的API。
 * 可以在两种类型的客户端上注册实现此接口的类：
 * {@link IMqttClient#setCallback(MqttCallback)}和
 * {@link IMqttAsyncClient#setCallback(MqttCallback)}
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/13 17:05
 * @ModifyDate 2019/12/13 17:05
 * @Version 1.0
 */
public interface MqttCallbackExtendedDemo {


    /**
     * Called when the connection to the server is completed successfully.
     * 与服务器的连接成功完成时调用。
     * @param reconnect If true, the connection was the result of automatic reconnect.如果为true，则表明连接是自动重新连接的结果。
     * @param serverURI The server URI that the connection was made to.建立连接的服务器URI。
     */
    public void connectComplete(boolean reconnect, String serverURI);
}
