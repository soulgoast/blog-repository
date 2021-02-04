package com.qunce.example.core.vertx;

import io.vertx.core.Vertx;

/**
 * @Description 同步执行有顺序
 * @Author hu zhongxi
 */
public class P06ExecuteBlocking {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.executeBlocking(future -> {
            System.out.println("执行第一方法");
            future.complete("one");
        }, true, res -> {
            System.out.println("The result is: " + res.result());
        });
        vertx.executeBlocking(future -> {
            System.out.println("执行第二个方法");
            future.complete("two");
        }, true, res -> {
            System.out.println("The result is: " + res.result());
        });
    }

}
