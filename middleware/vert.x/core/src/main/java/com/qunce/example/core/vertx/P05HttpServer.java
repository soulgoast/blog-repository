package com.qunce.example.core.vertx;

import io.vertx.core.Vertx;

/**
 * @Description TODO java.lang.IllegalAccessError: class io.vertx.core.buffer.impl.VertxUnsafeHeapByteBuf cannot access its superclass io.netty.buffer.UnpooledUnsafeHeapByteBuf
 * @Author hu zhongxi
 */
public class P05HttpServer {

    public static void main(String[] args) {
        Vertx.vertx()
                .createHttpServer()
                .requestHandler(request -> request.response().end("hello, Vert.x"))
                .listen(8080);
    }

}
