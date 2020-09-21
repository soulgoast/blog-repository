package com.qunce.abstractdocument;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @ClassName Document
 * @Description 定义抽象文档的基本操作
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 13:44
 * @ModifyDate 2020/9/21 13:44
 * @Version 1.0
 */
public interface Document {

    /**
     * 放入元素
     * @param key 元素的KEY
     * @param value 元素的值
     * @return
     */
    Void put(String key, Object value);

    /**
     * 获取元素的值
     * @param key 元素的KEY
     * @return 元素的值
     */
    Object get(String key);

    /**
     * 获取子文档的Stream
     * @param key 子文档的KEY
     * @param constructor 子文档的构造方法
     * @return 子文档
     */
    <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor);
}
