/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.permi.security.web.rest.modules.sys;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SysUserResource
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/17 15:54
 * @ModifyDate 2020/8/17 15:54
 * @Version 1.0
 */
@RestController
public class SysUserResource {

    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }
}
