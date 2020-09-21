package com.qunce.abstractdocument.tls.enums;

/**
 * @ClassName BasicProperty
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 15:06
 * @ModifyDate 2020/9/21 15:06
 * @Version 1.0
 */
public enum BasicProperty {

    SCHEMA("schema"),PROPERTIES("properties"), PROFILE("profile"), EVENTS("events"), SERVICES("services");

    private String value;

    BasicProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
