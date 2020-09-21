/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package goodman.java.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.assertFalse;

/**
 * @ClassName AtomicBooleanDetailsTest
 * @Description TODO
 * https://www.jianshu.com/p/4ed887664b13
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/8/30 21:56
 * @ModifyDate 2020/8/30 21:56
 * @Version 1.0
 */
public class AtomicBooleanDetailsTest {

    @Test
    public void init() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        assertFalse(atomicBoolean.get());
    }

    @Test
    public void getAndSet() {
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        boolean result = atomicBoolean.getAndSet(true);

    }
}