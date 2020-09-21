/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.collection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * @ClassName QueueDemo
 * @Description
 * Queue是用来保存等待处理的元素的集合。除了基本的Collection提供的操作。Queue提供了额外的添加, 移除, 和检查操作。
 * 其中每个方法存在两种表现形式：一种操作失败抛出异常，另一种是根据操作的不同返回指定的结果（比如null或者false）。
 * 后一种添加方式专为使用容量受限的实现而设计；大多数实现，插入操作不会失败。
 * Queue的方法总结
 *          会抛出异常       返回指定的值
 * 添加       add(e)          offer(e)
 * 移除       remove()        poll()
 * 检查       element()       peek()
 *
 * 通常情况下，但不是必须的，Queue按照先入先出的方式对元素进行排序。例外的有（priority queue）优先级队列，
 * 它根据支持的比较器或者元素的自然顺序进行排序，LIFO队列（比如栈结构）按照后入先出的顺序对元素进行排序。
 * 不管使用哪种排序方式，使用remove()或者poll()方法移除的必须是队列的头元素。在一个FIFO队列中，所有元素都会被添加到
 * 队列的尾部。其他种类的Queue可能使用不同的放置规则。每个Queue的实现必须指定它的排序规则。
 *
 * offer()方法在可能的情况下添加一个元素，否则返回false。与Collection的add方法不同的是，后者只能通过抛出一个未检查异常
 * 通知客户端添加元素失败。offer()设计的目的在于当失败是一种正常情况，而不是一种例外，比如固定容量（有界）的队列。
 *
 * remove()和poll()方法移除队列中的头元素，并返回它。移除元素是基于队列的排序策略的，每个实现各不相同。remove()和poll()方法的不同之处在于
 * ：当队列为空时，remove()方法将会抛出异常，而poll()方法将会返回空元素。
 *
 * element()和peek()方法会返回但是不移除队列中的头元素。
 *
 * Queue接口并没有定义在并发编程中常见的阻塞队列的方法。那些等待元素出现或者是空间可用的方法将会在继承了Queue的{@link java.util.concurrent.BlockingQueue}
 * 接口中定义。
 *
 * Queue的大部分实现都不允许放入null元素，但是也有例外情况，比如{@link java.util.LinkedList}并不禁止放入空元素。即使在允许放入null的实现中，null也不应该放入
 * 队列中，因为null是poll()方法用来表明队列为空的指定的返回值。
 *
 * <p>{@code Queue} implementations generally do not define
 * element-based versions of methods {@code equals} and
 * {@code hashCode} but instead inherit the identity based versions
 * from class {@code Object}, because element-based equality is not
 * always well-defined for queues with the same elements but different
 * ordering properties.
 *
 * @see java.util.Collection
 * @see LinkedList
 * @see PriorityQueue
 * @see java.util.concurrent.LinkedBlockingQueue
 * @see java.util.concurrent.BlockingQueue
 * @see java.util.concurrent.ArrayBlockingQueue
 * @see java.util.concurrent.LinkedBlockingQueue
 * @see java.util.concurrent.PriorityBlockingQueue
 * @since 1.5
 * @author Doug Lea
 * @param <E> the type of elements held in this collection
 *
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/12 18:33
 * @ModifyDate 2019/12/12 18:33
 * @Version 1.0
 */
public interface QueueDemo<E> {

    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an {@code IllegalStateException}
     * if no space is currently available.
     * 在不违反容量限制的情况下，将指定的元素放入到队列中，并返回{@code true}，如果目前没有空间可用就会抛出{@code IllegalStateException}
     *
     * @param e the element to add 添加的元素
     * @return {@code true} (as specified by {@link Collection#add}) {@code true}(在{@link Collection}的add方法中指定)
     * @throws IllegalStateException if the element cannot be added at this
     *         time due to capacity restrictions 如果由于容量限制，此时此刻元素不能被添加到集合
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */
    boolean add(E e);

    /**
     * Inserts the specified element into this queue if it is possible to do
     * so immediately without violating capacity restrictions.
     * When using a capacity-restricted queue, this method is generally
     * preferable to {@link #add}, which can fail to insert an element only
     * by throwing an exception.
     *
     * @param e the element to add
     * @return {@code true} if the element was added to this queue, else
     *         {@code false}
     * @throws ClassCastException if the class of the specified element
     *         prevents it from being added to this queue
     * @throws NullPointerException if the specified element is null and
     *         this queue does not permit null elements
     * @throws IllegalArgumentException if some property of this element
     *         prevents it from being added to this queue
     */
    boolean offer(E e);

    /**
     * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll poll} only in that it throws an exception if this
     * queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E remove();

    /**
     * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     *
     * @return the head of this queue, or {@code null} if this queue is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the head of this queue.  This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
     *
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E element();

    /**
     * 检索，但是不移除，队列中的头元素
     * 或者返回null，如果是空队列
     * @return 队列中的头元素，者返回null，如果是空队列
     */
    E peek();

}
