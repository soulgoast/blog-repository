package com.qunce.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Description 返回参数加密标识符
 * @Author hu zhongxi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseEncode {

}


