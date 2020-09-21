package com.qunce.abstractdocument.tls.enums;

/**
 * @ClassName CommonProperty
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 15:18
 * @ModifyDate 2020/9/21 15:18
 * @Version 1.0
 */
public enum CommonProperty {

    IDENTIFIER("identifier"), NAME("name"), DESC("desc"), TYPE("type"), ACCESS_MODE("accessMode"), REQUIRED("required"), DATA_TYPE("dataType");

    private String value;

    CommonProperty(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
