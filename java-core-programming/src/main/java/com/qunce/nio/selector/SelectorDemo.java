/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.nio.selector;

import org.junit.Test;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/**
 * @ClassName SelectorDemo
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/13 14:53
 * @ModifyDate 2020/8/13 14:53
 * @Version 1.0
 */
public class SelectorDemo {

    public void one() throws IOException {
        Selector selector = Selector.open();

    }

    public static void main(String[] args) {
        System.out.println(1<<0); // 1 * 1
        System.out.println(1<<2); // 1 * 4
        System.out.println(1<<3); // 1 * 8
        System.out.println(1<<4); // 1 * 16

        System.out.println(SelectionKey.OP_READ | SelectionKey.OP_CONNECT);
    }

    public void two(int interestSet) {
        boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
        boolean isInterestedInRead    = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
        boolean isInterestedInWrite   = (interestSet & SelectionKey.OP_WRITE) == SelectionKey.OP_WRITE;
        System.out.println("isInterestedInAccept:" + isInterestedInAccept);
        System.out.println("isInterestedInConnect:" + isInterestedInConnect);
        System.out.println("isInterestedInRead:" + isInterestedInRead);
        System.out.println("isInterestedInWrite:" + isInterestedInWrite);
    }

    @Test
    public void three() {
        two(29);
    }

}
