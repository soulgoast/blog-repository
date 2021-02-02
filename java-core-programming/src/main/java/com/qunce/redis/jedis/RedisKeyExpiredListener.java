/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.redis.jedis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName RedisKeyExpiredListener
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/13 11:01
 * @ModifyDate 2019/12/13 11:01
 * @Version 1.0
 */
public class RedisKeyExpiredListener extends JedisPubSub {

    private final static Logger logger = LoggerFactory.getLogger(RedisKeyExpiredListener.class);

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage pattern " + pattern + "" + channel + " " + message);
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost");
        Jedis jedis = pool.getResource();
        String re = jedis.get("notify");
        System.out.println(re);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("onPSubscribe " + pattern + " " + subscribedChannels);
    }

    public static void main(String[] args) {
/*        JedisPool pool = new JedisPool(new JedisPoolConfig(), "10.8.40.165", 7000, Protocol.DEFAULT_TIMEOUT); // 10.8.40.248
        Jedis jedis = pool.getResource();
        jedis.psubscribe(new RedisKeyExpiredListener(), "__keyevent@0__:expired");*/

        HostAndPort hostAndPort1 = new HostAndPort("10.8.40.216", 7000);
        HostAndPort hostAndPort2 = new HostAndPort("10.8.40.216", 7001);
        HostAndPort hostAndPort3 = new HostAndPort("10.8.40.216", 7002);
        HostAndPort hostAndPort4 = new HostAndPort("10.8.40.216", 7003);
        HostAndPort hostAndPort5 = new HostAndPort("10.8.40.216", 7004);
        HostAndPort hostAndPort6 = new HostAndPort("10.8.40.216", 7005);
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        hostAndPortSet.add(hostAndPort1);
        hostAndPortSet.add(hostAndPort2);
        hostAndPortSet.add(hostAndPort3);
        hostAndPortSet.add(hostAndPort4);
        hostAndPortSet.add(hostAndPort5);
        hostAndPortSet.add(hostAndPort6);
        JedisCluster jedis = new JedisCluster(hostAndPortSet);
        jedis.psubscribe(new RedisKeyExpiredListener(), "__keyevent@0__:expired");
    }

    @Test
    public void test() {
/*        JedisPool pool = new JedisPool(new JedisPoolConfig(), "10.8.40.216", 7000, Protocol.DEFAULT_TIMEOUT); // 10.8.40.248
        Jedis jedis = pool.getResource();
        jedis.set("notify", "你还在吗");
        jedis.expire("notify", 10);*/

        HostAndPort hostAndPort = new HostAndPort("10.8.40.216", 7000);
        Set<HostAndPort> hostAndPortSet = new HashSet<>();
        hostAndPortSet.add(hostAndPort);
        JedisCluster jedis = new JedisCluster(hostAndPortSet);

        jedis.set("notify", "你还在吗");
        jedis.expire("notify", 1);
/*        jedis.set("wh01%dev01%status", "在线");
        jedis.set("wh01%dev01%message", "{status:success}");
        jedis.set("wh01%dev01%msgTime", "2020-05-20 11:31:12");*/
    }

}
