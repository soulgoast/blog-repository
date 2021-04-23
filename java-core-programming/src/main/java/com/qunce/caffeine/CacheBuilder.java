package com.qunce.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class CacheBuilder {

    public static final AtomicInteger atomic = new AtomicInteger(0);

    @Test
    public void testOne() throws InterruptedException {
        LoadingCache<String, Map<String, Integer>> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .refreshAfterWrite(3, TimeUnit.SECONDS)
                .build(this::init);
        Map<String, Integer> aa = new HashMap<>();
        aa.put("aaa", 10);
        cache.put("aaa", aa);

        Map<String, Integer> map = cache.get("aaa");
        for (String str : map.keySet()) {
            System.out.println("key: " + str + ", value: " + map.get(str));
        }
        System.out.println("================================①========================================");
        TimeUnit.SECONDS.sleep(1);
        map = cache.get("aaa");
        for (String str : map.keySet()) {
            System.out.println("key: " + str + ", value: " + map.get(str));
        }

        System.out.println("=================================②=======================================");
        TimeUnit.SECONDS.sleep(3);
        map = cache.get("aaa");
        for (String str : map.keySet()) {
            System.out.println("key: " + str + ", value: " + map.get(str));
        }

        System.out.println("=================================③=======================================");
        TimeUnit.SECONDS.sleep(30);
        map = cache.get("aaa");
        assert map != null;
        for (String str : map.keySet()) {
            System.out.println("key: " + str + ", value: " + map.get(str));
        }

    }

    public Map<String, Integer> init(String s) {
        int i = atomic.incrementAndGet();
        Map<String, Integer> map = new HashMap<>();
        if ("aaa".equals(s)) {
            System.out.println("i的值：" + i);
            map.put(s, i);
        } else {
            System.out.println("键不存在");
        }
        return map;
    }
}
