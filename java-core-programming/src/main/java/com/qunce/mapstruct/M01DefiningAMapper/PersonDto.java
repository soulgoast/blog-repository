/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.mapstruct.M01DefiningAMapper;

import lombok.Data;

/**
 * @ClassName PersonDto
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/23 9:22
 * @ModifyDate 2020/7/23 9:22
 * @Version 1.0
 */
@Data
public class PersonDto {

    private String fullName;

    private String address;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
