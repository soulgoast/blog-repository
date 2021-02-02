/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.qunce.spring.data;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName InfluxdbApplication
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/12/12 19:27
 * @ModifyDate 2020/12/12 19:27
 * @Version 1.0
 */
@SpringBootApplication
public class InfluxdbApplication {

    public static void main(String[] args) {
        // SpringApplication.run(InfluxdbApplication.class, args);
    }

    public void add() {
        InfluxDB connect = InfluxDBFactory.connect("http://114.215.86.216:8086");
        InfluxDB influxDB = connect.enableBatch();
        influxDB.setDatabase("mydb");
        Point point = Point.measurement("").Builder()

    }
}