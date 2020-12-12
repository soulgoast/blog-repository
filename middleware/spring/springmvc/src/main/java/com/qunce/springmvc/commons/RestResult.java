package com.qunce.springmvc.commons;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Data
@Accessors(chain = true)
public class RestResult<T> {

    private String returnCode;
    private String returnDes;
    private T body;

    private String appId;
    private String msgId;
    private String sign;
    private String encodeMethod;
    private String signMethod;
    private RestResult() {
    }
    private RestResult(RestRequest req) {
        this.appId = req.getAppId();
        this.msgId = req.getMsgId();
        this.encodeMethod = req.getEncodeMethod();
        this.signMethod = req.getSignMethod();
    }

    public static <T> RestResult<T> newInstance(RestRequest req) {
        if(req != null){
            return new RestResult<>(req);
        }
        return new RestResult<>();
    }


}
