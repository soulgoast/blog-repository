/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils;

import java.util.Arrays;

/**
 * @ClassName ByteToString
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/11/26 20:02
 * @ModifyDate 2019/11/26 20:02
 * @Version 1.0
 */
public class ByteToString {

    public static void main(String[] args) {
        byte[] bytes = new byte[42];
        String a = "";
        for (byte b : bytes) {
            a += b;
        }
        System.out.println(a);
    }

}
