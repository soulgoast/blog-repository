/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.redis.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @ClassName JedisSimpleDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/13 10:29
 * @ModifyDate 2019/12/13 10:29
 * @Version 1.0
 */
public class JedisSimpleDemo {

    Jedis jedis = null;
    /**
     * 连接redis
     */
    public void connectRedis() {
        //连接redis ，redis的默认端口是6379


        // jedis.disconnect();//断开连接


    }

    @Test
    public void getKey() {
        jedis = new Jedis("10.8.40.216", 6379);

        //验证密码，如果没有设置密码这段代码省略
        // jedis.auth("password");

        jedis.connect();//连接
        String mainBoard01 = jedis.get("mainBoard01");
        System.out.println(mainBoard01);
    }

    @Test
    public void commonOperations() {
        //设置key生存时间，当key过期时，它会被自动删除。
        jedis.expire("key1", 5);//5秒过期

        //返回key所储存的值的类型。
        //none(key不存在),string(字符串),list(列表),set(集合),zset(有序集),hash(哈希表)
        jedis.type("key1");

        //将key改名为newkey,当key和newkey相同或者key不存在时,返回一个错误
        jedis.rename("key1", "key2");

        //检查给定key是否存在
        jedis.exists("key1");

        //移除给定key的生存时间(设置这个key永不过期)
        jedis.persist("key1");

        Set<String> keys = jedis.keys("*"); //列出所有的key
        Set<String> specifykeys = jedis.keys("key"); //查找特定的key

        //移除给定的一个或多个key,如果key不存在,则忽略该命令.

        jedis.del("key1");

        jedis.del("key1","key2","key3","key4","key5");
    }

    @Test
    public void list() {
        /**
         * rpush(key, value)：在名称为key的list尾添加一个值为value的元素
         *
         * lpush(key, value)：在名称为key的list头添加一个值为value的 元素
         *
         * llen(key)：返回名称为key的list的长度
         *
         * lpop(key)：返回并删除名称为key的list中的首元素
         *
         */
        /**
         *
         */
        for (int i = 0; i < 100000; i++) {
            jedis.rpush("047322eae26480", "在名称为key的list尾添加一个值为value的元素" + i);
        }

        /**
         * lpush(key, value)：在名称为key的list头添加一个值为value的 元素
         *
         * lrange(key, start, end)：返回名称为key的list中start至end之间的元素（下标从0开始，下同）
         *
         * ltrim(key, start, end)：截取名称为key的list，保留start至end之间的元素
         *
         * lindex(key, index)：返回名称为key的list中index位置的元素
         *
         * lset(key, index, value)：给名称为key的list中index位置的元素赋值为value
         *
         * lrem(key, count, value)：删除count个名称为key的list中值为value的元素。count为0，删除所有值为value的元素，count>0      从头至尾删除count个值为value的元素，count<0从尾到头删除|count|个值为value的元素。
         *
         * rpop(key)：返回并删除名称为key的list中的尾元素
         *
         * blpop(key1, key2,… key N, timeout)：lpop 命令的block版本。即当timeout为0时，若遇到名称为key i的list不存在或该list为空，则命令结束。如果 timeout>0，则遇到上述情况时，等待timeout秒，如果问题没有解决，则对key i+1开始的list执行pop操作。
         *
         * brpop(key1, key2,… key N, timeout)：rpop的block版本。参考上一命令。
         *
         * rpoplpush(srckey, dstkey)：返回并删除名称为srckey的list的尾元素，并将该元素添加到名称为dstkey的list的头部
         */

    }

    @Test
    public void listTwo() {
        int count = 0;
        while (jedis.llen("047322eae26480") > 0) {
            String message = jedis.lpop("047322eae26480");
            System.out.println(message);
            count ++;
        }
        System.out.println("获得元素的个数" + count);
    }

}
