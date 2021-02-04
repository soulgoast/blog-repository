package com.qunce.example.core.eventbus;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class P01Instance {

    public static void main(String[] args) {
        EventBus eventBus = Vertx.vertx().eventBus();
        System.out.println(eventBus.getClass().getName());
    }

}
