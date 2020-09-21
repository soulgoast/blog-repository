/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils.lang3;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.junit.Test;

/**
 * @ClassName NumberDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/6 10:14
 * @ModifyDate 2020/7/6 10:14
 * @Version 1.0
 */
public class NumberDemo {

    @Test
    public void testNumber() {
        String str = "我";
        if (NumberUtils.isCreatable(str)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        int compare = NumberUtils.compare(12, 133);
        System.out.println(compare);


        int compare1 = Integer.compare(12, 1243);
        System.out.println(compare1);


        MutablePair<String, String> aa = MutablePair.of("aa", "bb");
        System.out.println(aa.getLeft());
        System.out.println(aa.getRight());

        MutableTriple<String, String, String> bb = MutableTriple.of("aa", "bb", "cc");
        System.out.println(bb.getLeft());
        System.out.println(bb.getMiddle());
        System.out.println(bb.getRight());

    }

}
