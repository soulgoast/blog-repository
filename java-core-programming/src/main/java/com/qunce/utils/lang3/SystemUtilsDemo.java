/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils.lang3;

import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

/**
 * @ClassName SystemUtilsDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/7/9 9:54
 * @ModifyDate 2020/7/9 9:54
 * @Version 1.0
 */
public class SystemUtilsDemo {

    @Test
    public void test() {
        System.out.println(SystemUtils.getJavaIoTmpDir());

        System.out.println(SystemUtils.getUserHome());
        System.out.println(SystemUtils.getUserDir());
        System.out.println(SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_RECENT));
        System.out.println(SystemUtils.getJavaHome());
    }



}
