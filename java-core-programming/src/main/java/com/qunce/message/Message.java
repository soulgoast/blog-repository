/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message;

import lombok.Data;

/**
 * @ClassName Message
 * @Description 用于封装消息
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/12 13:26
 * @ModifyDate 2019/12/12 13:26
 * @Version 1.0
 */
@Data
public class Message<T> {

    private String messageNo; // 消息体

    private MessageState state; // 消息状态，有三种:0未开始，5处理中，10已完成

    private String topic; // 发送主题

    private Boolean acknowledge; // 是否等待IOT设备返回确认消息
}
