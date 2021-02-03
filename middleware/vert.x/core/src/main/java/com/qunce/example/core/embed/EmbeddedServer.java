package com.qunce.example.core.embed;

import io.vertx.core.Vertx;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class EmbeddedServer {

    public static void main(String[] args) {
        Vertx.vertx().createHttpServer().requestHandler(req -> req.response().end("Hello World!")).listen(8080);
/*        Vertx.vertx()
                .createHttpServer()
                .requestHandler(
                        request -> {
                            request.response()
                                    .putHeader("content-type", "text-plain")
                                    .end("Hello vert.x");
                        }
                ).listen(8080);*/
    }

}
