package com.qunce.vert.core;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class Core01Vertx02InstanceProps {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40));
        System.out.println();
    }

}
