package com.qunce.redis.lettuce;

import io.lettuce.core.cluster.RedisClusterClient;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class RedisKeyExpiredListener {

    public static void main(String[] args) {
        RedisClusterClient clusterClient = RedisClusterClient.create("redis://10.8.15.126:7001/0");
    }

}
