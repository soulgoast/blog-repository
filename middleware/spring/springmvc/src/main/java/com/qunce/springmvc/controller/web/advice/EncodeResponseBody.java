package com.qunce.springmvc.controller.web.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Component
@ControllerAdvice(basePackages = "com.qunce.springmvc.controller.web.rest")
public class EncodeResponseBody implements ResponseBodyAdvice {

    /**
     * 返回false，则不执行当前advice的业务
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return null;
    }
}
