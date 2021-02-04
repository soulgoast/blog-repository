package com.qunce.vert.core;

import io.vertx.core.Vertx;
import io.vertx.core.WorkerExecutor;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class Core01Vertx01Instance {

    private void test() {

    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
    }

    @Test
    public void instance() {
        Vertx vertx = Vertx.vertx();
    }

    @Test
    public void instanceAndConfig() {

    }

    @Test
    public void timedEvent() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.setPeriodic(1000, id -> System.out.println(id + "time fired!"));
    }

    /**
     * TODO 失败
     * @throws InterruptedException
     */
    @Test
    public void requestHandler() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        vertx.createHttpServer()
                .requestHandler(request -> {
                    request.response()
                            .putHeader("content-type", "text/plain")
                            .end("Hello from Vert.x!");})
                .listen(8080);
        TimeUnit.SECONDS.sleep(60);
    }

    @Test
    public void worker() throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        WorkerExecutor workerExecutor = vertx.createSharedWorkerExecutor("my-worker-pool");
        workerExecutor.executeBlocking(future -> {
            String result = "hello";
            future.complete(result);
        }, res -> {
            System.out.println("The result is :" + res.result());
        });
        workerExecutor.close();
    }
}
