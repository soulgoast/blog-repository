/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message;

import java.util.Date;

/**
 * @ClassName MessageQueue
 * @Description 使用redis列表来维护单个消息队列
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/12 8:50
 * @ModifyDate 2019/12/12 8:50
 * @Version 1.0
 */
public class MessageQueue {

    public void add(Message message) {

    }

    private Date date; // 最后更新的时间

    private Integer expirationDate = -1; // 有效期，单位是秒，默认为-1，标识不过期。

    public MessageQueue(Date date) {
        this.date = date;
    }
}
