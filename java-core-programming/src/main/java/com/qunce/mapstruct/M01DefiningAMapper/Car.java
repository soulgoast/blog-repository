/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.mapstruct.M01DefiningAMapper;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName Car
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/21 18:32
 * @ModifyDate 2020/7/21 18:32
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Car {

    private String make;
    private int numberOfSeats;
    private CarType type;

}
