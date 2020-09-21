/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package goodman.java.concurrent;

import org.w3c.dom.Node;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LinkedBlockingQueueSoul
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/2 18:27
 * @ModifyDate 2020/9/2 18:27
 * @Version 1.0
 */
public class LinkedBlockingQueueSoul<E> extends AbstractQueue<E> implements BlockingQueue<E>, Serializable {

    private static final long serialVersionUID = -6903933977591709194L;

    static class Node<E> {
        E item;

        /**
         * TODO
         * 有三种情况：
         * 1. 真正的后续节点
         * 2. 指向自己,标识将要被垃圾回收
         * 3. null值，没哟后续元素
         */
        Node<E> next;

        Node(E x) { item = x;}
    }

    /**
     * 属性
     */

    /** 绑定的容量，如果没有，则为Integer.MAX_VALUE */
    private final int capacity;

    /**
     * 当前的元素个数
     */
    private final AtomicInteger count = new AtomicInteger();

    transient Node<E> head;

    private transient Node<E> last;

    private final ReentrantLock takeLock = new ReentrantLock();

    private final Condition notEmpty = takeLock.newCondition();

    private final ReentrantLock putLock = new ReentrantLock();

    private final Condition notFull = putLock.newCondition();

    /**
     * 构造方法
     */
    public LinkedBlockingQueueSoul() { this(Integer.MAX_VALUE);}

    public LinkedBlockingQueueSoul(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException();
        this.capacity = capacity;
        last = head = new Node<E>(null);
    }

    public LinkedBlockingQueueSoul(Collection<? extends E> c) {
        this(Integer.MAX_VALUE);

        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            int n = 0;
            for (E e : c) {
                if (e == null) {
                    throw new NullPointerException();
                }
                if (n == capacity) {
                    throw new IllegalArgumentException("Queue full");
                }
                enqueue(new Node<E>(e));
                ++n;
            }
            count.set(n);
        } finally {
            putLock.unlock();
        }
    }

    /**
     * 添加元素
     * TODO last.next这个做什么？
     * @param node
     */
    private void enqueue(Node<E> node) {
        last = last.next = node;
    }

    /**
     * 移除元素
     * @return
     */
    private E dequeue() {
        Node<E> h = head;
        Node<E> first = h.next;
        h.next = h; // TODO help GC ?
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }


    /**
     * 常用操作
     */
    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();

        int c = -1;
        Node<E> node = new Node<>(e);
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;

        /**
         * TODO
         */
        putLock.lockInterruptibly();
        try {
            /**
             * 如果队列是满的，则等待
             */
            while (count.get() == capacity) {
                notFull.wait();
            }
            /**
             * 将元素放入队列中
             */
            enqueue(node);

            /**
             * 队列的元素个数+1
             */
            c = count.getAndIncrement();

            /**
             *
             */
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock();
        }
        if (c == 0) {
            signalNotEmpty();
        }
    }

    private void signalNotEmpty() {
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }

    }


    public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
        if (e == null) throw new NullPointerException();

        long nacos = unit.toMillis(timeout);
        int c = -1;

        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;

        putLock.lockInterruptibly();

        try {
            while (count.get() == capacity) {
                if (nacos <= 0) {
                    return false;
                }
                nacos = notFull.awaitNanos(nacos);
            }
            enqueue(new Node<>(e));

            c = count.getAndIncrement();
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock();
        }

        /***
         *
         */
        if (c == 0) {
            signalNotEmpty();
        }

        return true;
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E x = null;
        int c = -1;
        long nacos = unit.toMillis(timeout);
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;

        takeLock.lockInterruptibly();

        try {
            while (count.get() == 0) {
                if (nacos == 0) {
                    return null;
                }
                nacos = notEmpty.awaitNanos(nacos);
            }

            x = dequeue();
            c = count.getAndDecrement();
            if (c >  1) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c == capacity) {
            signalNotEmpty();
        }
        return x;
    }

    /**
     * TODO
     * @param p
     * @param trail
     */
    void unlink(Node<E> p, Node<E> trail) {
        p.item = null;
        trail.next = p.next;

    }

    public boolean remove(Object o) {
        if (o == null) return false;
        fullyLock();

        try {
            for (Node<E> trail = head, p = trail.next; p != null; trail = p, p = p.next) {
                if (o.equals(p.item)) {
                    unlink(p, trail);
                    return true;
                }
            }
            return false;
        } finally {
            fullyUnlock();
        }
    }

    private void fullyUnlock() {
    }

    private void fullyLock() {
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();

        count.set(0);

        last = head = new Node<>(null);
        for (;;) {
            E item = (E) s.readObject();
            if (item == null) {
                break;
            }
            add(item);
        }

    }
}
