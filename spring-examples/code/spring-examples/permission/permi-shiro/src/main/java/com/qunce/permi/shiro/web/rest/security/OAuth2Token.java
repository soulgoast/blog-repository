/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.permi.shiro.web.rest.security;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName OAuth2Token
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/17 15:03
 * @ModifyDate 2020/8/17 15:03
 * @Version 1.0
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    public String getPrincipal() {
        return token;
    }

    public String getCredentials() {
        return token;
    }
}
