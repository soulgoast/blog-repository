package com.qunce.springboot.redis.listener;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
@Slf4j
public class SubscribeListener {

    /**
     * 队列消息接收方法
     *
     * @param message
     */
    public void receiveMessage(String message) {
        log.info("接收到redis推送事件,{}", message);
    }

}
