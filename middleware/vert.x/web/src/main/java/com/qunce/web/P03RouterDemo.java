package com.qunce.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class P03RouterDemo {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);

        router.route().blockingHandler(routingContext -> {
            // 执行某些同步的耗时操作
            doSomethingThatBlocks();
            // 调用下一个处理器
            routingContext.next();
        });

        server.requestHandler(router::accept).listen(8080);
    }

    private static void doSomethingThatBlocks() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        System.out.println("aaaaabbbb");
    }

}
