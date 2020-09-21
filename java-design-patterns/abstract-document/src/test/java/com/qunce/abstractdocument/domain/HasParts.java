package com.qunce.abstractdocument.domain;

import com.qunce.abstractdocument.Document;
import com.qunce.abstractdocument.domain.enums.Property;

import java.util.stream.Stream;

/**
 * @ClassName HasParts
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:39
 * @ModifyDate 2020/9/21 14:39
 * @Version 1.0
 */
public interface HasParts extends Document {

    default Stream<Part> getParts() { return children(Property.PARTS.getValue(), Part::new);}

}
