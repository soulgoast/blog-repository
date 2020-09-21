package com.qunce.java.core.programming.collection;

import java.util.HashMap;

/**
 * @Author huzhongxi@whty.com.cn
 * @Date 2019/11/6 14:56
 * @Version 1.0
 */
public class HashMapDemo {

    public static void main(String[] args) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("one", "one");
        hashMap.put("one", "two");
        hashMap.put("two", "three");
    }
}
