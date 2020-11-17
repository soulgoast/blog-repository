package com.qunce.springmvc.commons;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Data
@Accessors(chain = true)
public class RestRequest<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "应用编码(appId)不能为空!")
    protected String appId;
    @NotBlank(message = "消息编码(msgId)不能为空!")
    protected String msgId;
    protected String sign;
    protected String encodeMethod;
    protected String signMethod;
    @Valid
    protected T body;

}
