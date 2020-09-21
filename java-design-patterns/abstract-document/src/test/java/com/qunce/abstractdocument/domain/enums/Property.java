package com.qunce.abstractdocument.domain.enums;

/**
 * @ClassName Property
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:35
 * @ModifyDate 2020/9/21 14:35
 * @Version 1.0
 */
public enum Property {

    PARTS("parts"), TYPE("type"), PRICE("price"), MODEL("model");

    private String value;

    Property(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
