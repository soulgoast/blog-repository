package com.qunce.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName JedisRedisCluster
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/11 14:00
 * @ModifyDate 2020/9/11 14:00
 * @Version 1.0
 */
public class JedisRedisCluster {

    @Test
    public void getKeys() {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("10.8.40.216", 7000));
        nodes.add(new HostAndPort("10.8.40.216", 7001));
        nodes.add(new HostAndPort("10.8.40.216", 7002));
        nodes.add(new HostAndPort("10.8.40.216", 7003));
        nodes.add(new HostAndPort("10.8.40.216", 7004));
        nodes.add(new HostAndPort("10.8.40.216", 7005));
        JedisCluster cluster = new JedisCluster(nodes);

/*        Set<String> hkeys = cluster.hkeys("*");
        hkeys.stream().forEach(System.out::println);*/

        // String str = cluster.get("appnotestzr001%devicenotestzr001%status");
        // System.out.println(str);
        cluster.set("appnotestzr001%devicenotestzr001%status", "在线");
    }



}
