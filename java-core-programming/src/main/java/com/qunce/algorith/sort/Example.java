/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.algorith.sort;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

/**
 * @ClassName Example
 * @Description 排序算法模板
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/11 14:02
 * @ModifyDate 2019/12/11 14:02
 * @Version 1.0
 */
public class Example {

    public static void sort(Comparable[] a) {

    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if(less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] a = args;
        sort(a);
        assert isSorted(a);
        show(a);
    }

    @Test
    public void test() {
/*        Person person1 = new Person("张三", "武汉");
        Object[] arr = Arrays.asList(person1, person1 , person1).toArray();*/
        StringBuilder a = new StringBuilder("abc");
        Object[] arr = Arrays.asList(a, a , a).toArray();
        System.out.println(Arrays.asList(arr));
        a.append("bcd");
        System.out.println(Arrays.asList(arr));

    }

    @Data
    @AllArgsConstructor
    class Person {
        private String name;

        private String address;
    }


    @Test
    public void test1() {

    }

}
