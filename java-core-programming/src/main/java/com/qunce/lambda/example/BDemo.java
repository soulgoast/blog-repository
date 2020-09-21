/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.qunce.lambda.example;

import java.util.function.*;

/**
 * @ClassName BDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/6/20 20:53
 * @ModifyDate 2020/6/20 20:53
 * @Version 1.0
 */
public class BDemo {

    public static void main(String[] args) {
        Predicate<Integer> predicate = x -> x > 5;

        Consumer<String> consumer = str -> System.out.println(str);

        Function<String, String> function = str -> str;

        Supplier<String> supplier = String::new;

        UnaryOperator<Boolean> unaryOperator = bool -> bool;

        BinaryOperator<Integer> binaryOperator = (x, y) -> x * y;
    }
}