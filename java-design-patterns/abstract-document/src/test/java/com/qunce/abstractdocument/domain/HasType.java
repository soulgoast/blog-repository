package com.qunce.abstractdocument.domain;

import com.qunce.abstractdocument.Document;
import com.qunce.abstractdocument.domain.enums.Property;

import java.util.Optional;

/**
 * @ClassName HasType
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:38
 * @ModifyDate 2020/9/21 14:38
 * @Version 1.0
 */
public interface HasType extends Document {

    default Optional<String> getType() { return Optional.of((String) get(Property.TYPE.getValue()));}

}
