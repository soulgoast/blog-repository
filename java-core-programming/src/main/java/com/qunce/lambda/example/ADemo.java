/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.lambda.example;

import java.awt.event.ActionListener;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/**
 * @ClassName ADemo
 * @Description Lambda表达式的基本形式
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/6/18 15:50
 * @ModifyDate 2020/6/18 15:50
 * @Version 1.0
 */
public class ADemo {

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello World");



        Callable<String> callable = () -> "Hello World";

        Consumer<String> consumer = str -> System.out.println(str);

        ActionListener actionListener = event -> System.out.println("button clicked");

        Runnable mutiStatement = () -> {
            System.out.println("Hello");
            System.out.println(" World");
        };

        BinaryOperator<Long> add = (x, y) -> x + y;

        BinaryOperator<Long> addExplicit = (Long x, Long y) -> x + y;

        /**
         * Java 中初始化数组时，数 组的类型就是根据上下文推断出来的。
         * 另一个常见的例子是 null，只有将 null 赋值给一 个变量，才能知道它的类型。
         */
        final String[] array = {"hello", "world"};

        final Object[] objects = {"hello", "world"};

        final String string = null;


        /**
         *
         */
        String username = "aaa";
        System.out.println(username);
        username = "bbb";
       // Consumer<String> consumer1 = name -> System.out.println(name + username );
        consumer.accept(username);
    }

    public void operator(BinaryOperator<Integer> binaryOperator, Integer first, Integer second) {
        binaryOperator.apply(first, second);
    }

    public void add() {
        operator((x, y) -> x + y, 10, 20);
    }

}
