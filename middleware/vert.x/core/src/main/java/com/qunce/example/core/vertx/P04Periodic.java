package com.qunce.example.core.vertx;

import io.vertx.core.Vertx;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class P04Periodic {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.setPeriodic(1000, id -> System.out.println("time fired!"));
    }

}
