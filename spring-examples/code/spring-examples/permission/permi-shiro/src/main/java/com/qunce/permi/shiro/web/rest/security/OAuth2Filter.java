/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.permi.shiro.web.rest.security;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName OAuth2Filter
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/17 15:02
 * @ModifyDate 2020/8/17 15:02
 * @Version 1.0
 */
public class OAuth2Filter extends AuthenticatingFilter {

    private static final Logger log = LoggerFactory.getLogger(OAuth2Filter.class);

    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        log.info("执行到方法OAuth2Filter.createToken");
        return null;
    }

    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        log.info("执行到方法OAuth2Filter.onAccessDenied");
        return false;
    }
}
