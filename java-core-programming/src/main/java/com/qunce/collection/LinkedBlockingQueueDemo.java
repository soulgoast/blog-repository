/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.collection;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ClassName LinkedBlockingQueue
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/12 8:56
 * @ModifyDate 2019/12/12 8:56
 * @Version 1.0
 */
public class LinkedBlockingQueueDemo {

    LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);

    PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue<String>(10, Comparator.comparing(String::toLowerCase));

    @Test
    public void get() {

    }
}
