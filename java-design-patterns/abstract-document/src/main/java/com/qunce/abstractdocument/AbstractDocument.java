package com.qunce.abstractdocument;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @ClassName AbstractDocument
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 13:50
 * @ModifyDate 2020/9/21 13:50
 * @Version 1.0
 */
public abstract class AbstractDocument implements Document{

    private final Map<String, Object> properties;

    public AbstractDocument(Map<String, Object> properties) {
        Objects.requireNonNull(properties, "properties map is required");
        this.properties = properties;
    }

    @Override
    public Void put(String key, Object value) {
        properties.put(key, value);
        return null;
    }

    @Override
    public Object get(String key) {
        return properties.get(key);
    }

    /**
     * 此处的优化点：
     *  (List<Map<String, Object>>) el,强转有问题，可能发生ClassCaseException。
     *  在强转之前要通过instanceOf判断一下
     *
     * @param key 子文档的KEY
     * @param constructor 子文档的构造方法
     * @param <T>
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> Stream<T> children(String key, Function<Map<String, Object>, T> constructor) {
        Optional<List<Map<String, Object>>> any = Stream.of(get(key)).filter(Objects::nonNull).map(el -> el instanceof List ? (List) el : Arrays.asList(el) ).map(el -> (List<Map<String, Object>>) el).findAny();
        return any.map(maps -> maps.stream().map(constructor)).orElseGet(Stream::empty);
    }

}
