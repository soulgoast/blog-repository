/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @ClassName StringToObj
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/3 14:26
 * @ModifyDate 2019/12/3 14:26
 * @Version 1.0
 */
public class StringToObj {

    @Test
    public void strToObj() throws IOException {
        Person person = new Person("战三", 18, "洪山关山", new Date());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(person);
        json = "{\"person\": " + json + "}";
        System.out.println(json);
        Person person1 = mapper.readValue(new ByteArrayInputStream(json.getBytes(Charset.defaultCharset())), Person.class);
        System.out.println(person1.toString());
    }

}

class Person {
    private String name;

    private Integer age;

    private String address;

    private Date birthday;

    public Person() {
    }

    public Person(String name, Integer age, String address, Date birthday) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
