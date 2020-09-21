/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.function;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * @ClassName AComsumer
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/6/16 14:16
 * @ModifyDate 2020/6/16 14:16
 * @Version 1.0
 */
@Slf4j
public class AComsumer {

    public static void main(String[] args) {
        Consumer consumer = System.out::println;
        consumer.accept("使用副作用进行操作");
        Consumer thenConsumer = consumer.andThen(item -> log.info("日志输出" + item));
        thenConsumer.accept("两次输出");
    }

}
