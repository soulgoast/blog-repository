package com.qunce.example.core.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;

import java.util.concurrent.TimeUnit;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class P03RegisterCompletion {

    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();
        EventBus eb = vertx.eventBus();
        MessageConsumer<Object> consumer = eb.consumer("news.uk.sport", message -> {
            System.out.println("I have received a message: " + message.body());
        });
        consumer.completionHandler(res -> {
            if (res.succeeded()) {
                System.out.println("The handler registration has reached all nodes");
            } else {
                System.out.println("Registration failed!");
            }
        });
        consumer.unregister(res -> {
            if (res.succeeded()) {
                System.out.println("The handler un-registration has reached all nodes");
            } else {
                System.out.println("Un-registration failed!");
            }
        });

        TimeUnit.SECONDS.sleep(1);
        eb.publish("news.uk.sport", "Yay! Someone kicked a ball");
    }

}
