package com.qunce.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Verticle;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class TestVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        System.out.println("BasicVerticle started");
    }
}
