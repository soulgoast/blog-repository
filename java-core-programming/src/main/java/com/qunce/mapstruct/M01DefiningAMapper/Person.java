/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.mapstruct.M01DefiningAMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @ClassName Person
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/23 9:22
 * @ModifyDate 2020/7/23 9:22
 * @Version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
