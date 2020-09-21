package com.qunce.abstractdocument.domain;

import com.qunce.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * @ClassName domain
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:32
 * @ModifyDate 2020/9/21 14:32
 * @Version 1.0
 */
public class Car extends AbstractDocument implements HasModel, HasPrice, HasParts{

    public Car(Map<String, Object> properties) {
        super(properties);
    }
}
