package com.qunce.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Description 请求参数解密标识符
 * @Author hu zhongxi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestDecode {

}
