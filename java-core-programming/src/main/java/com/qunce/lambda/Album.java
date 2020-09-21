/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.lambda;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Album
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/6/18 14:39
 * @ModifyDate 2020/6/18 14:39
 * @Version 1.0
 */
@Data
public class Album {

    private String name;

    private List<Track> tracks;

    private List<Artist> musicians;

}
