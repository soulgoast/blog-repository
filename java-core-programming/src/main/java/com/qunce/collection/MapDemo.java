package com.qunce.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class MapDemo {

    /**
     * 不存在就添加
     */
    @Test
    public void map() {
        Map<String, String> maps = new HashMap<>();
        maps.put("123", "111");
        String s = maps.computeIfAbsent("124", item -> item + "asd");
        System.out.println(s);
        maps.values().forEach(System.out::println);
    }

    @Test
    public void put() {
        Map<String, String> maps = new HashMap<>();
        String put1 = maps.put("123", "111");
        System.out.println(put1);
        String put = maps.put("123", "123");
        System.out.println(put);
    }

    @Test
    public void put2() {
        Map<String, String> maps = new ConcurrentHashMap<>();
        maps.put("aa", null);
        maps.put("bb", null);
        maps.put("cc", null);
    }

}
