package com.qunce.web;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class P01RouterDemo {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route().handler(routingContext -> {
            // 所有的请求都会调用这个处理器处理
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            // 写入响应并结束处理
            response.end("Hello World from Vert.x-Web!");
        });
        server.requestHandler(router::accept).listen(8080);
    }

}
