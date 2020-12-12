/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.qunce.core;

import com.qunce.lang.Nullable;

/**
 * @ClassName AttributeAccessor
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/11/5 22:25
 * @ModifyDate 2020/11/5 22:25
 * @Version 1.0
 */
public interface AttributeAccessor {

    void setAttribute(String name, @Nullable Object value);

    @Nullable
    Object getAttribute(String name);

    @Nullable
    Object removeAttribute(String name);

    boolean hadAttribute(String name);

    String[] attributeNames();
}