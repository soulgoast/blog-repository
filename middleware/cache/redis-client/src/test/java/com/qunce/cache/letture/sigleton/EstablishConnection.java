package com.qunce.cache.letture.sigleton;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class EstablishConnection {

    @Test
    public void connection() throws ExecutionException, InterruptedException {
        RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379/0");
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisAsyncCommands<String, String> async = connect.async();
        RedisFuture<String> set = async.set("google", "youtube");
        System.out.println(set.get());
        RedisFuture<String> google = async.get("google");
        System.out.println(google.get());
        connect.close();
        redisClient.shutdown();
    }

}
