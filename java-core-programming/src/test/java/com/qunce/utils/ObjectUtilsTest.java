/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @ClassName ObjectUtilsTest
 * @Description ObjectUtils的测试类
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/11/13 10:02
 * @ModifyDate 2019/11/13 10:02
 * @Version 1.0
 */
public class ObjectUtilsTest {

    @Test
    public void addForNullProperty() throws Exception {
        Person person = new Person();
        person = ObjectUtils.checkNullAndInit(person);
        Assert.assertEquals("", person.getName());
        Assert.assertEquals("", person.getAddress());
    }

    @Test
    public void addForNullPropertyUpgrade() throws Exception  {
        AsiaPerson person = new AsiaPerson();
        // person = ObjectUtils.checkNullAndInitUpgrade(person);
        Assert.assertEquals("", person.getName());
        Assert.assertEquals("", person.getAddress());
        Assert.assertEquals("", person.getColor());
        Assert.assertEquals("", person.getLanguage());
    }

}

class Person {

    private String name;

    private String address;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

class AsiaPerson  extends Person{

    private String color;

    private String language;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}