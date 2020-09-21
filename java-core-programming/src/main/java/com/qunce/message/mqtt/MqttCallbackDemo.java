/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.mqtt;

import org.eclipse.paho.client.mqttv3.*;

/**
 * @ClassName MqttCallbackDemo
 * @Description
 * 使与客户端相关的异步事件发生时通知应用程序。可以在两种类型的客户端上注册实现此接口的类：
 * {@link IMqttClient#setCallback(MqttCallback)}和
 * {@link IMqttAsyncClient#setCallback(MqttCallback)}
 *
 * 与客户端有关的异步事件：
 * - 连接丢失
 * -
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/13 16:40
 * @ModifyDate 2019/12/13 16:40
 * @Version 1.0
 */
public interface MqttCallbackDemo {

    /**
     * This method is called when the connection to the server is lost.
     * 与服务器的连接丢失时，将调用此方法。
     *
     * @param cause the reason behind the loss of connection.失去连接的原因。
     */
    public void connectionLost(Throwable cause);

    /**
     * This method is called when a message arrives from the server.
     * 当消息从服务器到达时，将调用此方法。
     * <p>
     * This method is invoked synchronously by the MQTT client. An
     * acknowledgment is not sent back to the server until this
     * method returns cleanly.</p>
     * 此方法由MQTT客户端同步调用。在此方法执行完成返回之前，不会将确认发送回服务器。
     * <p>
     * If an implementation of this method throws an <code>Exception</code>, then the
     * client will be shut down.  When the client is next re-connected, any QoS
     * 1 or 2 messages will be redelivered by the server.</p>
     * 如果这个方法的实现抛出异常，客户端将会关闭。在客户端重启后，服务端将会发送所有Oos为1或者2的消息。
     * <p>
     * Any additional messages which arrive while an
     * implementation of this method is running, will build up in memory, and
     * will then back up on the network.</p>
     *
     * <p>
     * If an application needs to persist data, then it
     * should ensure the data is persisted prior to returning from this method, as
     * after returning from this method, the message is considered to have been
     * delivered, and will not be reproducible.</p>
     * 如果应用程序需要持久化数据，则应该确保从该方法返回之前数据已持久化，因为从该方法返回之后，该消息被视为已经传递，并且将无法重现。
     * <p>
     * It is possible to send a new message within an implementation of this callback
     * (for example, a response to this message), but the implementation must not
     * disconnect the client, as it will be impossible to send an acknowledgment for
     * the message being processed, and a deadlock will occur.</p>
     * 这个回调的实现中可能会发送一条新的消息（例如，这条消息的响应），需要注意的是这个实现一定不要断开客户端连接，因为将不可能为正在处理的消息发送确认
     * 消息，以至于发生死循环。
     *
     * @param topic name of the topic on the message was published to 消息发布的主题名称
     * @param message the actual message. 实际的消息
     * @throws Exception if a terminal error has occurred, and the client should be
     * shut down. 如果出现终端错误，客户端应该关闭。
     */
    public void messageArrived(String topic, MqttMessage message) throws Exception;

    /**
     * Called when delivery for a message has been completed, and all
     * acknowledgments have been received. For QoS 0 messages it is
     * called once the message has been handed to the network for
     * delivery. For QoS 1 it is called when PUBACK is received and
     * for QoS 2 when PUBCOMP is received. The token will be the same
     * token as that returned when the message was published.
     * 消息发送完成并且受到所有的确认后调用。对于QoS 0 的消息，一旦确认消息发送到网络中随即调用此方法。
     * 对于QoS 1 的消息，收到PUBACK后调用。
     * 对于QoS 2 的消息，收到PUBCOMP后调用。
     * 返回的token与消息发布时的token一样。
     *
     * @param token the delivery token associated with the message.
     */
    public void deliveryComplete(IMqttDeliveryToken token);

}
