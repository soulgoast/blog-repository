package com.qunce.abstractdocument.domain;

import com.qunce.abstractdocument.Document;
import com.qunce.abstractdocument.domain.enums.Property;

import java.util.Optional;

/**
 * @ClassName HasModel
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:33
 * @ModifyDate 2020/9/21 14:33
 * @Version 1.0
 */
public interface HasModel extends Document {

    default Optional<String> getModel() { return Optional.of( (String) get(Property.MODEL.getValue()) );}

}
