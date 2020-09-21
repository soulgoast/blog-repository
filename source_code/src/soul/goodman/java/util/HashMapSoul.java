/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package goodman.java.util;

import java.io.Serializable;
import java.util.*;

/**
 * @ClassName HashMapSoul
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/25 14:00
 * @ModifyDate 2020/8/25 14:00
 * @Version 1.0
 */
public class HashMapSoul<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;


    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }


    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 构造方法
     *
     * @see Arrays#asList(Object[])
     */
    public HashMapSoul(int initialCapacity, float loadFactory) {
        if (initialCapacity <  0) {
            throw new IllegalArgumentException("Illegal initial capacity:" + initialCapacity);
        }

        if (initialCapacity > MAXIMUM_CAPACITY) {

        }
    }


    /**
     * put 方法
     * put one
     * put all
     */

    public V put(K key, V value) {
        return putValue(hash(key), key, value, false, true);
    }

    transient Node<K, V>[] table;

    /**
     *
     */
    int threshold;
    /**
     * 实现了map的put及相关方法。
     * @param hash 键的hash值
     * @param key 键
     * @param value 给键设置的值
     * @param onlyIfAbsent 只要在值不存在的情况下，才会设置新值。
     * @param evict TODO
     * @return
     */
    private V putValue(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K, V> [] tab;
        Node<K, V> p;
        int n, i;

        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }

        if ((p = tab[i = (n -1 ) & hash]) == null) {
            // tab[i] = newNode(hash, key, value, null);
        }
        return null;

    }

    /**
     *
     * @return
     */
    final Node<K,V>[] resize() {
        Node<K, V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        int oldThr = threshold;

        int ewCap, newThr = 0;

        /**
         *
         */
        if (oldCap > 0) {

        }
        return null;
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    /**
     * get
     * get batch
     *
     */

    /**
     * Node 节点
     *
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return key;
        }

        @Override
        public final V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hash(value);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Entry<?, ?>) o;
                if (Objects.equals(key, o) && Objects.equals(value, e.getValue())) {
                    return true;
                }
            }
            return false;
        }

    }


}
