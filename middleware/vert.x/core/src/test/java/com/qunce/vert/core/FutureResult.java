/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.qunce.vert.core;

import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileProps;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpServer;
import io.vertx.core.net.NetServer;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName FutureResult
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2021/2/3 19:42
 * @ModifyDate 2021/2/3 19:42
 * @Version 1.0
 */
public class FutureResult {

/*    @Test
    public void test() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        FileSystem fileSystem = vertx.fileSystem();
        Future<FileProps> future = fileSystem.props("D:\\迅雷下载\\aaa.mkv");
        future.onComplete(filePropsAsyncResult -> {
            if (filePropsAsyncResult.succeeded()) {
                FileProps result = filePropsAsyncResult.result();
                System.out.println("File size = " + result.size());
            } else {
                System.out.println("Failure: " + filePropsAsyncResult.cause().getMessage());
            }
        });
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void compose() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        FileSystem fs = vertx.fileSystem();
        Future<Void> compose = fs.createFile("D:\\迅雷下载\\bbb.txt")
                .compose(v -> {
                    return fs.writeFile("D:\\迅雷下载\\bbb.txt", Buffer.buffer("Hello, Vert.x"));
                }).compose(v -> {
                    return fs.move("D:\\迅雷下载\\bbb.txt", "C:\\");
                });
        boolean succeeded = compose.succeeded();
        System.out.println(succeeded);

        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    public void futureCoordination() {
        Vertx vertx = Vertx.vertx();
        HttpServer httpServer = vertx.createHttpServer();
        NetServer netServer = vertx.createNetServer();

        Future<HttpServer> httpServerFuture = httpServer.listen();
        Future<NetServer> netServerFuture = netServer.listen();

        CompositeFuture.all(httpServerFuture, netServerFuture.onComplete(ar -> {
            if (ar.succeeded()) {
                // All servers started
            } else {
                // At least one server failed
            }
        }));
    }*/

}