/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.concurrency;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName SpinLock
 * @Description 自旋锁
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/18 15:46
 * @ModifyDate 2019/12/18 15:46
 * @Version 1.0
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(current, null)) {
            System.out.println("======================");

        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        // sign.compareAndSet(null, current);
        sign.set(current);
    }

    @Test
    public void test() throws InterruptedException {
        // System.out.println(sign.get());
/*        lock();
        TimeUnit.SECONDS.sleep(1);
        unlock();*/
        // int i = Integer.valueOf(String.valueOf(new Date().getTime()));
        // System.out.println(i);
        System.out.println(new Date().getTime());
        // System.out.println(Integer.valueOf("4294967295"));
        System.out.println(Math.pow(2, 32));
        System.out.println(Integer.MAX_VALUE); // 2147483647
    }

}
