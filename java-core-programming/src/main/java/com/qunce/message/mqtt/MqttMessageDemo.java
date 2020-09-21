/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;

/**
 * @ClassName MqttMessageDemo
 * @Description
 * MQTT消息包含应用程序有效负载和指定如何传递消息的选项。该消息包括表示为byte []的“有效负载”（消息的主体）
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/16 10:00
 * @ModifyDate 2019/12/16 10:00
 * @Version 1.0
 */
public class MqttMessageDemo {

    /**
     * Sets the quality of service for this message.
     * 设置此消息的服务质量。
     * <ul>
     * <li>Quality of Service 0 - indicates that a message should
     * be delivered at most once (zero or one times).  The message will not be persisted to disk,
     * and will not be acknowledged across the network.  This QoS is the fastest,
     * but should only be used for messages which are not valuable - note that
     * if the server cannot process the message (for example, there
     * is an authorization problem), then an
     * {@link MqttCallback#deliveryComplete(IMqttDeliveryToken)}.
     * Also known as "fire and forget".</li>
     *
     * <li>Quality of Service 1 - indicates that a message should
     * be delivered at least once (one or more times).  The message can only be delivered safely if
     * it can be persisted, so the application must supply a means of
     * persistence using <code>MqttConnectOptions</code>.
     * If a persistence mechanism is not specified, the message will not be
     * delivered in the event of a client failure.
     * The message will be acknowledged across the network.
     * This is the default QoS.</li>
     *
     * <li>Quality of Service 2 - indicates that a message should
     * be delivered once.  The message will be persisted to disk, and will
     * be subject to a two-phase acknowledgement across the network.
     * The message can only be delivered safely if
     * it can be persisted, so the application must supply a means of
     * persistence using <code>MqttConnectOptions</code>.
     * If a persistence mechanism is not specified, the message will not be
     * delivered in the event of a client failure.</li>
     * 服务质量2-表示消息应传递一次。该消息将保留在磁盘上，并且将通过网络进行两阶段确认。如果消息可以持久保存，
     * 则只能安全地传递该消息，因此应用程序必须使用MqttConnectOptions提供持久性手段。如果未指定持久性机制，
     * 则在客户端故障的情况下将不传递消息。
     *
     *</ul>
     * If persistence is not configured, QoS 1 and 2 messages will still be delivered
     * in the event of a network or server problem as the client will hold state in memory.
     * If the MQTT client is shutdown or fails and persistence is not configured then
     * delivery of QoS 1 and 2 messages can not be maintained as client-side state will
     * be lost.
     *
     * @param qos the "quality of service" to use.  Set to 0, 1, 2.
     * @throws IllegalArgumentException if value of QoS is not 0, 1 or 2.
     * @throws IllegalStateException if this message cannot be edited
     */
    public void setQos(int qos) {

    }
}
