/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.qunce.undertow.started;

import org.xnio.OptionMap;
import org.xnio.Xnio;
import org.xnio.XnioWorker;

/**
 * @ClassName XnioStarted
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/9/23 21:58
 * @ModifyDate 2020/9/23 21:58
 * @Version 1.0
 */
public class XnioStarted {

    public static void main(String[] args) {
        Xnio xnio = Xnio.getInstance();

        // XnioWorker worker = xnio.createWorker(OptionMap.builder().set());

    }
}