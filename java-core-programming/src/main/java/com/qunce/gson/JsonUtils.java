/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.gson;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName JsonUtils
 * @Description
 *
 * json数据是如何解析成java对象
 *
 * 使用json数据描述json格式的数据
 * 能够解析json数据
 * 通过给定字段，能够获得字段的值
 *
 *
 * 任意来源——》字符串——》{
 *     根据描述信息，对字符串进行校验（创建一个实例），然后让json字符串对实例进行初始化
 *     只起到一个校验的作用。
 *
 * }——》 客户端根据字段名称，以及字段对应的实例类型，可以从json字符串中获取对应的数据。
 *
 *
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/4/13 17:11
 * @ModifyDate 2020/4/13 17:11
 * @Version 1.0
 */
public class JsonUtils {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Person person = new Person("张三", 19, Arrays.asList("北京", "上海", "广州"));
        String json = gson.toJson(person);

        Person p = new Gson().fromJson(json, Person.class);
        System.out.println(p.toString());
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private String name;

    private Integer age;

    private List<String> addresses;
}
