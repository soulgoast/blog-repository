package com.qunce.example.core.router;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class P01Router {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(request -> {
            // 所有的请求都会调用这个处理器处理
            HttpServerResponse response = request.response();
            response.putHeader("content-type", "text/plain");
            // 写入响应并结束处理
            response.end("Hello World!");
        });
        server.listen(8080);
    }

}
