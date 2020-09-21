/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.message;

/**
 * @ClassName MessageState
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/12 13:34
 * @ModifyDate 2019/12/12 13:34
 * @Version 1.0
 */
public enum MessageState {

    UNPROCESSED(0, "未开始"),
    PROCESSING(5, "处理中"),
    COMPLETED(10, "已完成");


    private final int value;

    private final String statePhrase;

    MessageState(int value, String statePhrase) {
        this.value = value;
        this.statePhrase = statePhrase;
    }

}
