package com.qunce.example.core.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class P02Register {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });
    }

}
