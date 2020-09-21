/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.poi.excel;

import com.qunce.poi.excel.annotaion.ExcelField;
import lombok.Data;

/**
 * @ClassName Person
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/28 16:20
 * @ModifyDate 2020/7/28 16:20
 * @Version 1.0
 */
@Data
public class Person {

    @ExcelField(title = "占位编号**编号", align = 2, sort = 1)
    private String no;
    @ExcelField(title = "所属机房编号", align = 2, sort = 2)
    private String roomNo;

    @ExcelField(title = "机柜名称*", align = 2, sort = 3)
    private String name;

}
