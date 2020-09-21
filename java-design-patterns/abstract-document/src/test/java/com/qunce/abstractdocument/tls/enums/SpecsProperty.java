package com.qunce.abstractdocument.tls.enums;

/**
 * @ClassName SpecsProperty
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 15:24
 * @ModifyDate 2020/9/21 15:24
 * @Version 1.0
 */
public enum SpecsProperty {

    MIN("min"), MAX("max"), UNIT("unit"), UNIT_NAME("unitName"), SIZE("size"), STEP("step"), LENGTH("length"),TRUE("0"),FALSE("1");

    private String value;

    SpecsProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
