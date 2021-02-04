package com.qunce.example.core.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;

/**
 * @Description
 * 公用worker pool
 * @Author hu zhongxi
 */
public class P07WorkerExecutor {

    public static void main(String[] args) {
        WorkerExecutor sharedWorkerExecutor = Vertx.vertx().createSharedWorkerExecutor("my-worker-pool");
        sharedWorkerExecutor.executeBlocking(future -> {
            String result = blockingMethod("hello");
            future.complete(result);
        }, res -> System.out.println("The result is: " + res.result()));
        sharedWorkerExecutor.close(System.out::println);
    }

    public static String blockingMethod(String param) {
        return param + ", Vert.x";
    }

}
