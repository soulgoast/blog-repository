package com.qunce.example.core.vertx;

import io.vertx.core.Vertx;

/**
 * @Description
 * @Author hu zhongxi
 */
public class P01Instance {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        System.out.println(vertx.getClass().getName());
    }

}
