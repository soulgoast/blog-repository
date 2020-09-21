/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.permi.shiro.web.rest.modules.sys;

import com.qunce.permi.shiro.web.rest.modules.sys.vm.SysLoginForm;
import com.qunce.permi.shiro.web.rest.security.OAuth2Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SysUserResource
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/17 14:54
 * @ModifyDate 2020/8/17 14:54
 * @Version 1.0
 */
@RestController
@RequestMapping("/sys")
public class SysUserResource {

    private static final Logger log = LoggerFactory.getLogger(OAuth2Realm.class);

    /**
     * 登录
     */
    @PostMapping("/login")
    public Map<String, Object> doLogin(@RequestBody SysLoginForm form) {
        String userName = form.getUsername().trim();
        //用户信息

        //账号锁定
        log.info("form:{}", form);

        Map<String, Object> map = new HashMap<>();
        map.put("msg", "登录成功");

        return map;
    }
}
