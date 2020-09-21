/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.algorith.search;

/**
 * @ClassName SymbolTable
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/1/2 15:47
 * @ModifyDate 2020/1/2 15:47
 * @Version 1.0
 */
public interface SymbolTable<K, V> {

    /**
     * 将键值对存入表中，若值为空则将键key从表中删除
     * @param key 键
     * @param value 值
     */
    void put(K key, V value);

    /**
     * 获取键key对应的值，若键不存在则返回null
     * @param key 键
     * @return 值
     */
    V get(K key);

    /**
     * 从表中删除键及其对应的值
     * 默认实现：put(key, null);
     * @param key 键
     */
    void delete(K key);

    /**
     * 键 key 在表中是否有对应的值
     * @param key 键
     * @return true，有；false，没有
     */
    boolean contains(K key);

    boolean isEmpty();

    /**
     *
     * @return 表中键值对数量
     */
    int size();

    /**
     *
     * @return 表中所有键的集合
     */
    Iterable<K> keys();

}
