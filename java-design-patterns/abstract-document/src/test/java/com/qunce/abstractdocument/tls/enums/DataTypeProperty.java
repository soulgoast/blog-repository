package com.qunce.abstractdocument.tls.enums;

/**
 * @ClassName DataTypeProperty
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 15:24
 * @ModifyDate 2020/9/21 15:24
 * @Version 1.0
 */
public enum DataTypeProperty {

    TYPE("type"), SPECS("specs");

    private String value;

    DataTypeProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
