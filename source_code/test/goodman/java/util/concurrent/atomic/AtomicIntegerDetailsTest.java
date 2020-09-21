/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package goodman.java.util.concurrent.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerDetailsTest
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/8/30 20:07
 * @ModifyDate 2020/8/30 20:07
 * @Version 1.0
 */
public class AtomicIntegerDetailsTest {

    /**
     * 初始化方法
     */
    @Test
    public void init() {
        AtomicInteger withoutParams = new AtomicInteger();
        System.out.println(withoutParams.get());
        AtomicInteger withParams = new AtomicInteger(0);
    }

    @Test
    public void set() {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(10);
        /**
         * https://blog.csdn.net/ITer_ZC/article/details/40744485
         * 优化不必要的volatile操作
         */
        atomicInteger.lazySet(12);
        System.out.println(atomicInteger.get());

    }

    /**
     * cas 是利用了CPU级别的锁
     * 自旋锁
     *
     * 为什么变量要声明为volatile, 可见性，防止重排序
     */
    @Test
    public void cas() {

    }

    /**
     * 利用CAS定义一个锁
     * CSA原理
     * https://www.jianshu.com/p/21be831e851e
     * https://blog.csdn.net/hsuxu/article/details/9467651
     *
     */
    @Test
    public void customLock() {

    }

    /**
     * 立即失败锁
     *
     * 问题：多线程问题
     */
    class CompareAndSwapLock {
        /**
         * 0 未锁定
         * 1 锁定
         */
        private final AtomicInteger value = new AtomicInteger(0);

        public void tryLock() throws GetLockException {
            boolean success = value.compareAndSet(0, 1);
            if (!success) {
                throw new GetLockException("获取锁失败！");
            }
        }

        public void unLock() {
            if (0 == value.get()) {
                return;
            }
            value.compareAndSet(1, 0);
        }


    }

    class GetLockException extends Exception {

        public GetLockException() {
            super();
        }

        public GetLockException(String message) {
            super(message);
        }
    }

}