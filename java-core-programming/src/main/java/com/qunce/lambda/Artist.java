/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName Artist
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/6/18 14:34
 * @ModifyDate 2020/6/18 14:34
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    private String name;

    private List<String> members;

    private String nationality;
}
