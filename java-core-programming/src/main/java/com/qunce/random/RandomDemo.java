/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.random;

import java.util.Random;

/**
 * @ClassName RandomDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/23 14:19
 * @ModifyDate 2020/7/23 14:19
 * @Version 1.0
 */
public class RandomDemo {

    public static void main(String[] args) {
        double v = Math.random() * 14;
        Double d = new Double(v);
        System.out.println(d.intValue());


    }

}
