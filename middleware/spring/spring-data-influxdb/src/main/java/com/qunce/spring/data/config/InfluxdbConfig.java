/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.qunce.spring.data.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName InfluxdbConfig
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/12/12 19:27
 * @ModifyDate 2020/12/12 19:27
 * @Version 1.0
 */
@Configuration
public class InfluxdbConfig {

    @Value("${spring.influx.url}")
    private String influxdbUrl;


}