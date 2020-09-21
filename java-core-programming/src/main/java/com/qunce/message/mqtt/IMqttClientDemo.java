/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;

/**
 * @ClassName IMqttClientDemo
 * @Description
 * 使得应用程序可以使用阻塞的方法与MQTT服务器通信
 * 该接口允许应用程序利用MQTT 3.1规范的所有功能，包括：
 * - 连接
 * - 发布
 * - 订阅
 * - 取消订阅
 * - 断开连接
 * 有两种风格的MQTT客户端，这是一种，另一种是{@link IMqttAsyncClient}
 * IMqttClient提供一系列的方法，这些方法是阻塞的，直到MQTT操作完成将控制权交给应用程序。
 * IMqttAsyncClient提供一系列的非阻塞方法，在初步校验参数和状态后，就将控制权交给调用程序。主要的处理是在后台执行的，以免应用程序线程。
 * 当应用程序希望在执行MQTT操作时继续进行处理时，这种非阻塞方法非常方便。
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/13 15:35
 * @ModifyDate 2019/12/13 15:35
 * @Version 1.0
 */
public interface IMqttClientDemo {

}
