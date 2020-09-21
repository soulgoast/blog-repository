package com.qunce.abstractdocument.domain;

import com.qunce.abstractdocument.AbstractDocument;

import java.util.Map;

/**
 * @ClassName Part
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:41
 * @ModifyDate 2020/9/21 14:41
 * @Version 1.0
 */
public class Part extends AbstractDocument implements HasType, HasModel, HasPrice {

    public Part(Map<String, Object> properties) {
        super(properties);
    }
}
