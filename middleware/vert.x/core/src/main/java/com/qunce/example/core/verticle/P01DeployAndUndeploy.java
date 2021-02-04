package com.qunce.example.core.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

/**
 * @Description
 * @Author hu zhongxi
 */
public class P01DeployAndUndeploy {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyFirstVerticle(), res -> {
            if (res.succeeded()) {
                vertx.deploymentIDs().forEach(id -> {
                    System.out.println("ID: " + id);
                    vertx.undeploy(id);
                });
            } else {
                System.out.println("部署失败");
            }
        });
    }
}


class MyFirstVerticle extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        System.out.println("开始部署P01MyFirstVerticle");
    }

    @Override
    public void stop() throws Exception {
        System.out.println("结束部署P01MyFirstVerticle");
    }

}