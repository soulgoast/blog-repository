/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package goodman.java.io;

import org.junit.Test;

/**
 * @ClassName BB
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/8/21 22:26
 * @ModifyDate 2020/8/21 22:26
 * @Version 1.0
 */
public class BitsTest {

    /**
     * 0 false
     * other true
     */
    @Test
    public void booleanTest() {
        byte[] bytes = new byte[] {1, 2, 0};
        System.out.println(Bits.getBoolean(bytes, 2));
    }

    @Test
    public void charTest() {
        char[] chars = new char[]{ '中', 'a', '雩'};
        byte[] bytes = new String(chars).getBytes();
        System.out.println(bytes.length); // -28 -72 -83 97 -23 -101 -87
        System.out.println(Bits.getChar(bytes, 3));
    }
}