package com.qunce.example.core.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @Description 此处只需要了解VertxOptions的基本属性配置
 * eventLoopPoolSize    事件循环线程池线程数      2 * 计算机上的内核数
 * workerPoolSize       工作池线程数             20
 *
 * @Author hu zhongxi
 */
public class P02InstanceWithProps {

    public static void main(String[] args) {
        VertxOptions vertxOptions = new VertxOptions()
                .setWorkerPoolSize(40);
        Vertx vertx = Vertx.vertx(vertxOptions);
        System.out.println(vertx.getClass().getSimpleName());
    }

}
