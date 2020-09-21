/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.http.core;

import org.apache.http.HttpConnectionMetrics;
import org.apache.http.impl.DefaultBHttpClientConnection;
import org.junit.Test;

import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName Example002
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/18 14:36
 * @ModifyDate 2020/8/18 14:36
 * @Version 1.0
 */
public class Example002 {

    @Test
    public void blocking() throws IOException {
        Socket socket = new Socket("localhost", 9200);
        DefaultBHttpClientConnection conn = new DefaultBHttpClientConnection(8 * 1024);
        conn.bind(socket);
        System.out.println(conn.isOpen());
        HttpConnectionMetrics metrics = conn.getMetrics();
        System.out.println(metrics.getRequestCount());
        System.out.println(metrics.getResponseCount());
        System.out.println(metrics.getReceivedBytesCount());
        System.out.println(metrics.getSentBytesCount());

    }

}
