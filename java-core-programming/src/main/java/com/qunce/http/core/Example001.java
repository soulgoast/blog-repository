/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.http.core;

import org.apache.http.*;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Test;

/**
 * @ClassName Example001
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/18 13:35
 * @ModifyDate 2020/8/18 13:35
 * @Version 1.0
 */
public class Example001 {

    @Test
    public void request() {
        HttpRequest httpRequest = new BasicHttpRequest("GET", "/", HttpVersion.HTTP_1_1);
        System.out.println(httpRequest.getRequestLine().getMethod());
        System.out.println(httpRequest.getRequestLine().getUri());
        System.out.println(httpRequest.getProtocolVersion());
        System.out.println(httpRequest.getRequestLine().toString());
    }

    @Test
    public void response() {
        HttpResponse httpResponse = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        System.out.println(httpResponse.getProtocolVersion());
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        System.out.println(httpResponse.getStatusLine().getReasonPhrase());
        System.out.println(httpResponse.getStatusLine().toString());
    }

    @Test
    public void commonPropertiesAndMethods() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie",
                "c1=a; path=/; domain=localhost");
        response.addHeader("Set-Cookie",
                "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);
        Header[] hs = response.getHeaders("Set-Cookie");
        System.out.println(hs.length);
    }
}
