package com.qunce.socket;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Slf4j
public class Mock4Gcard {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        // 创建一个HttpServer
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(request -> {
            request.bodyHandler(buffer -> {
                log.info("收到的数据：{}", buffer.toString());
            });
// 获取到response对象
            HttpServerResponse response = request.response();

            // 设置响应头
            response.putHeader("Content-type", "text/html;charset=utf-8");

            // 响应数据
            response.end("Hello World");
        });

        server.listen(8765); // 监听8888端
    }

}
