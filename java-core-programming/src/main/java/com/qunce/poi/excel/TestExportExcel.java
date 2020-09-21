/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.poi.excel;

import org.junit.Test;

import java.io.FileOutputStream;

/**
 * @ClassName Test
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/28 16:19
 * @ModifyDate 2020/7/28 16:19
 * @Version 1.0
 */
public class TestExportExcel {

    @Test
    public void test() throws Exception {
        ExportExcel write = new ExportExcel("", Person.class).write(new FileOutputStream("aaa.xls"));
    }

}
