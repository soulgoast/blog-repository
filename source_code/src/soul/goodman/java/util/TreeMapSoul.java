/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package goodman.java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.*;

/**
 * @ClassName TreeMapSoul
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/3 13:41
 * @ModifyDate 2020/9/3 13:41
 * @Version 1.0
 */
public class TreeMapSoul<K, V> extends AbstractMap<K, V> implements NavigableMap<K, V>, Cloneable, Serializable {

    private final Comparator<? super K> comparator;

    private transient Entry<K, V> root;

    private transient int size = 0;

    private transient int modCount = 0;


    /**
     * 构造方法
     */
    public TreeMapSoul() {
        comparator = null;
    }

    public TreeMapSoul(Comparator<? extends K> comparator) {
        this.comparator = comparator;
    }

    public TreeMapSoul(Map<? extends K, ? extends V> m) {
        comparator = null;
        putAll(m);
    }

    public TreeMapSoul(SortedMap<K, ? extends V> m) {
        comparator = m.comparator();
        try {
            buildFromSorted(m.size(), m.entrySet().iterator(), null, null);
        } catch (IOException cannotHappen) {
        } catch (ClassNotFoundException cannotHappen) {

        }
    }

    public int size() {
        return size;
    }

    public boolean containsKey(Object key) {
        return getEntry(key) != null;
    }

    public boolean containsValue(Object value) {
        for (Entry<K, V> e = getFirstEntry(); e!= null; e = successor(e)) {
            if (valEquals(value, e.getValue())) {
                return true;
            }
        }
        return false;
    }

    public V get(Object key) {
        Entry<K, V> p = getEntry(key);
        return (p == null ? null : p.getValue());
    }

    public Comparator<? extends K> comparator() {
        return comparator;
    }

    public K firstKey() {
        return key(getFirstEntry());
    }

    public K lastKey() {
        return key(getLastEntry());
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    private K key(Entry<K,V> lastEntry) {
    }


    private boolean valEquals(Object value, V value1) {
        return false;
    }

    private Entry<K,V> successor(Entry<K,V> e) {
        return null;
    }

    private Entry<K,V> getFirstEntry() {
        return null;
    }

    private Entry<K,V> getLastEntry() {
    }

    private Entry<K, V> getEntry(Object key) {
        return null;
    }


    public V put(K key, V value) {
        Entry<K, V> t = root;
        if (t == null) {

        }
    }

    /**
     *
     * @param size
     * @param iterator
     * @param inputStream
     * @param defaultValue
     */
    private void buildFromSorted(int size, Iterator<? extends Entry<K,? extends V>> iterator, ObjectInputStream inputStream, V defaultValue) {
        this.size = size;
        root = buildFromSorted(0, 0, size - 1, computRedLevel(size), iterator, inputStream, defaultValue);
    }

    /**
     * 核心方法
     *
     * @param level
     * @param lo
     * @param hi
     * @param redLevel
     * @param it
     * @param str
     * @param defaultVal
     * @return
     */
    private final Entry<K, V> buildFromSorted(int level, int lo, int hi, int redLevel, Iterable<?> it, ObjectInputStream str, V defaultVal) throws IOException,  {

    }

    public static final boolean RED = false;

    public static final boolean BLOCK = true;

    /**
     * 核心类
     * @param <K>
     * @param <V>
     */
    static final class Entry<K, V> implements Map.Entry<K, V> {
        K key;
        V value;
        Entry<K, V> left;
        Entry<K, V> right;
        Entry<K, V> parent;

        boolean color = BLOCK;

        Entry(K key, V value, Entry<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        /**
         * 如何判断TreeMap中的元素是否相等
         * @return
         */
        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    /**
     *
     * size     level
     * 0        0
     * 1        0
     * 2        1
     * 3        2
     * 4        2
     * 5        2
     * 6        2
     * 7        3
     * @param size
     * @return
     */
    private int computRedLevel(int size) {
        int level = 0;
        for (int m = size - 1; m >= 0; m = m / 2 - 1) {
            level++;
        }
        return level;
    }

    /**
     * 工具类
     */
    final int compare(Object k1, Object k2) {
        return comparator == null ?

    }

    //

    private static final Object UNBOUNDED = new Object();

    abstract static class NavigableSubMap<K, V> extends AbstractMap<K, V> implements
            NavigableMap<K, V>, Serializable {
        private static final long serialVersionUID = -2102997345730753016L;

        final TreeMapSoul<K, V> m;

        final K lo, li;
        final boolean fromStart, toEnd;
        final boolean loInclusive, hiInclusize;

        NavigableSubMap(TreeMapSoul<K, V> m,
                        boolean fromStart, K lo, boolean loInclusive,
                        boolean toEnd, K hi, boolean hiInclusize) {
            if (!fromStart && !toEnd) {
                if (m.compare(lo, hi)) {
                    
                }
            }
        }

    }

    private boolean compare(K lo, K hi) {
    }

}
