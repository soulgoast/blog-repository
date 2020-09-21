/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils.time;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DiffTime
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/15 10:23
 * @ModifyDate 2020/7/15 10:23
 * @Version 1.0
 */
public class DiffTime {

    @Test
    public void test() throws ParseException {
        String startTime = "2019-12-31 16:12:11";
        String endTime = "2020-01-01 16:12:10";

        test(startTime, endTime);
    }

    public void test(String startTime, String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(startTime);
        Date date2 = sdf.parse(endTime);

        long time = date2.getTime() - date1.getTime();
        System.out.println("间隔的时间为：" + time / (1000 * 60 * 60 * 24) + "天");
    }

}
