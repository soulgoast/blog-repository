package com.qunce.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.junit.Test;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class SimpleDemo {

    /**
     * 单机版
     */
    @Test
    public void connectSingle() {
        RedisClient redisClient = RedisClient.create("redis://localhost:6379/0");
        StatefulRedisConnection<String, String> connect = redisClient.connect();

        RedisCommands<String, String> sync = connect.sync();
        String set = sync.set("aaa", "bbb");
        System.out.println(set);

        String aaa = sync.get("aaa");
        System.out.println(aaa);

    }

    /**
     * 集群
     */
    @Test
    public void connectCluster() {
        RedisClusterClient clusterClient = RedisClusterClient.create("redis://10.8.15.126:7001/0");
        StatefulRedisClusterConnection<String, String> connect = clusterClient.connect();
        RedisAdvancedClusterCommands<String, String> sync = connect.sync();
        sync.set("aaaa", "bbbb");

        RedisClusterClient clusterClient2 = RedisClusterClient.create("redis://10.8.15.126:7002/0");
        StatefulRedisClusterConnection<String, String> connect2 = clusterClient2.connect();
        RedisAdvancedClusterCommands<String, String> sync1 = connect2.sync();
        String aaaa = sync1.get("aaaa");
        System.out.println(aaaa);
    }

}
