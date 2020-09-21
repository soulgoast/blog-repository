/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message.mqtt;

/**
 * @ClassName ClientStateDemo
 * @Description
 * 客户端的核心类，维护挂起以及正在进行中的消息状态信息。
 * 消息在交付的过程中，会在多个对象之间移动。TODO
 * 1）当客户端不在运行时，消息存储在实现MqttClientPersistent接口的持久性存储中。默认值为MqttDefaultFilePersistencew，
 * 它在发生故障和系统重新启动时安全地存储消息。如果未指定持久性，则会回退到MemoryPersistence，它将在实例化Mqtt客户端时维护消息。
 *
 * 2）实例化客户端或特定的ClientState时，将消息从持久性存储中读取到：
 * - 如果QoS 2 PUBLISH或PUBREL是outboundqos2哈希表
 * - 如果是QoS 1 PUBLISH -outboundqos1哈希表
 * （请参见restoreState）
 *
 * 3）在Connect上，按照messageid顺序将消息从出站哈希表复制到endingMessages或standingFlows向量。
 * - 初始消息发布进入待处理消息缓冲区。
 * - PUBREL进入未决流缓冲区
 * （请参见restoreInflightMessages）
 *
 * 4）发送方线程一次从未决流和未决消息缓冲区中读取消息。该消息已从挂起的缓冲区中删除，但仍保留在*出站*哈希表上。
 * 哈希表是将所有未完成*消息的完整集合存储在内存中的地方。 （持久性仅在启动时使用）
 *
 * 5）接收器线程-接收有线消息：
 * - 如果QoS 1，则从持久性和outboundqos1中删除
 * - 如果QoS 2 PUBREC发送PUBREL。
 * 使用PUBREL更新outboundqos2条目并更新持久性。
 * - 如果QoS 2 PUBCOMP从持久性和outboundqos2中删除
 *
 * 注意：由于客户端的多线程性质，对此类的任何更改都必须考虑并发性至关重要。
 * 例如，一旦在流上/消息上放了电线，接收线程就有可能接收到ack并进行处理在发送端完成处理之前的响应。 TODO
 * 例如，可能发送了*连接，则在处理连接通知发送之前收到了conack！ TODO
 *
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/17 8:57
 * @ModifyDate 2019/12/17 8:57
 * @Version 1.0
 */
public class ClientStateDemo {

}
