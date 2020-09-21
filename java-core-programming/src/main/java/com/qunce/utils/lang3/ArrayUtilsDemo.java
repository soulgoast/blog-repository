/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils.lang3;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @ClassName ArrayUtilsDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/8 11:55
 * @ModifyDate 2020/7/8 11:55
 * @Version 1.0
 */
public class ArrayUtilsDemo {

    @Test
    public void parameter() {
        boolean[] emptyBooleanArray = ArrayUtils.EMPTY_BOOLEAN_ARRAY;

        String[] initArr = {"abc", "def"};
        Arrays.toString(emptyBooleanArray);
        String s = ArrayUtils.toString(initArr);
        System.out.println(s);

        String s1 = Arrays.toString(initArr);
        System.out.println(s1);

        Integer[] integers = ArrayUtils.toArray(1, 2, 3);
        Serializable[] serializables = ArrayUtils.toArray(1, 2, "3");
    }

}
