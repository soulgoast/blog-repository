/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package goodman.java;

import java.util.ArrayList;

/**
 * @ClassName Test
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/8/21 22:21
 * @ModifyDate 2020/8/21 22:21
 * @Version 1.0
 */
public class Test {

    @org.junit.Test
    public void test() {
        System.out.println("aaa");
    }

    @org.junit.Test
    public void test2() {
        int a = 2147483647 + 1;
        System.out.println(new int[a].length);
        System.out.println(Integer.MAX_VALUE);
    }

    @org.junit.Test
    public void test3() {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(10);
    }

    @org.junit.Test
    public void test4() {
        System.out.println(0 >> 1);
        int a = 1;
        System.out.println(a++);
        System.out.println(a);
    }

}