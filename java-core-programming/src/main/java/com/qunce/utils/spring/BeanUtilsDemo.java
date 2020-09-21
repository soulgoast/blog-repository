/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.ConversionService;

import java.util.Date;

/**
 * @ClassName BeanUtilsDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/2 9:43
 * @ModifyDate 2020/7/2 9:43
 * @Version 1.0
 */
public class BeanUtilsDemo {

    public static void main(String[] args) {

        Human human = new Human("张三", "2020-06-06 18:05:53");
        Person person = new Person();

        BeanUtils.copyProperties(human, person);
        System.out.println(person);

        Human human1 = BeanUtils.instantiateClass(Human.class);
        System.out.println(human1);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {

    private String name;

    private Date birthday;

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Human {
    private String name;

    private String birthday;
}
