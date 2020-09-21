/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.lambda;

import java.awt.event.ActionListener;
import java.util.function.BinaryOperator;

/**
 * @ClassName ADemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/6/18 15:50
 * @ModifyDate 2020/6/18 15:50
 * @Version 1.0
 */
public class ADemo {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello World");

        ActionListener actionListener = event -> System.out.println("button clicked");

        Runnable mutiStatement = () -> {
            System.out.println("Hello");
            System.out.println(" World");
        };

        BinaryOperator<Long> add = (x, y) -> x + y;

        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;


    }

}
