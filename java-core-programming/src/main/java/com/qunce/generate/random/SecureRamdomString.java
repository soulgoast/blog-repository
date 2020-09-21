/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.generate.random;

import java.util.UUID;

/**
 * @ClassName SecureRamdomString
 * @Description 生成安全随机字符串
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/11/21 16:18
 * @ModifyDate 2019/11/21 16:18
 * @Version 1.0
 */
public class SecureRamdomString {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        String a  = uuid.toString().replaceAll("-", "").substring(17);
        System.out.println(a + ":" + a.length());

    }
}
