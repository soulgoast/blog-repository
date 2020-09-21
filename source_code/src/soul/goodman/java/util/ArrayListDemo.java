/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package goodman.java.util;

import org.junit.Test;

import java.util.*;

/**
 * @ClassName ArrayListDemo
 * @Description
 * 1、ArrayList内部使用对象数组来存储
 * 2、
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/24 13:34
 * @ModifyDate 2020/8/24 13:34
 * @Version 1.0
 */
public class ArrayListDemo {

    /**
     * 构造方法
     */
    @Test
    public void testConstructOne() {
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>(10);
        List<String> list3 = new ArrayList<>(list1);
    }

    /**
     * ArrayList的迭代器方法
     *
     * ArrayList.this.elementData
     */
    @Test
    public void testIterable() {
        ArrayList<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        Iterator<String> iterator = list.iterator();
        if (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * https://www.cnblogs.com/lsf90/p/5366325.html
     */
    @Test
    public void test1() {
        SubClass[] subArray = {new SubClass(), new SubClass()};
        System.out.println(subArray.getClass());

        // class [Lcollection.SubClass;
        BaseClass[] baseArray = subArray;
        System.out.println(baseArray.getClass());

        // java.lang.ArrayStoreException
        baseArray[0] = new BaseClass();


    }

    @Test
    public void test2() {
        List<String> list = Arrays.asList("abc");

        // class java.util.Arrays$ArrayList
        System.out.println(list.getClass());

        // class [Ljava.lang.String;
        Object[] objArray = list.toArray();
        System.out.println(objArray.getClass());

        objArray[0] = new Object(); // cause ArrayStoreException
    }

    @Test
    public void test3() {
        List<String> dataList = new ArrayList<>();
        dataList.add("one");
        dataList.add("two");

        Object[] listToArray = dataList.toArray();

        // class [Ljava.lang.Object;返回的是Object数组
        System.out.println(listToArray.getClass());
        listToArray[0] = "";
        listToArray[0] = 123;
        listToArray[0] = new Object();

    }

    @Test
    public void test4() {
        String[] a = new String[2];
        Object[] b = new Object[2];
        b = a;

        b[0] = new Object();
    }


    @Test
    public void test5() {
        System.out.println(null == null);
    }

    @Test
    public void test6() {
        java.util.List<String> arr = new java.util.ArrayList<>();
        arr.addAll(Arrays.asList("one", "one", "one", "two"));
        arr.remove("one");
        System.out.println(arr);
    }

    class SubClass extends BaseClass {

    }

    class BaseClass {

    }
}
