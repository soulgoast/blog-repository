package com.qunce.example.core.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @Description TODO 启动错误：No ClusterManagerFactory instances found on classpath
 * @Author hu zhongxi
 */
public class P03Cluster {

    public static void main(String[] args) {
        VertxOptions vertxOptions = new VertxOptions();
        Vertx.clusteredVertx(vertxOptions, res -> {
            if (res.succeeded()) {
                Vertx result = res.result();
                System.out.println("集群创建成功：" + result.getClass().getName());
            } else {
                System.out.println("集群创建失败");
            }
        });
    }

}
