/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.magic;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

/**
 * @ClassName UrlDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/1/20 9:18
 * @ModifyDate 2020/1/20 9:18
 * @Version 1.0
 */
public class UrlDemo {

    @Test
    public void test() throws InterruptedException, MalformedURLException {
        HashSet set = new HashSet();
        set.add(new URL("http://google.com"));
        System.out.println(set.contains(new URL("http://google.com")));
        Thread.sleep(60000);
        System.out.println(set.contains(new URL("http://google.com")));
    }

}
