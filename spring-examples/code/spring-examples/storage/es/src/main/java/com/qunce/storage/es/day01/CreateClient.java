/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.storage.es.day01;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.CreateIndex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @ClassName CreateClient
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/18 9:06
 * @ModifyDate 2020/8/18 9:06
 * @Version 1.0
 */
public class CreateClient {

    private JestClient client;

    @BeforeEach
    public void createTransportClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                //.multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                //.defaultMaxTotalConnectionPerRoute(<YOUR_DESIRED_LEVEL_OF_CONCURRENCY_PER_ROUTE>)
                // and no more 20 connections in total
                // .maxTotalConnection(<YOUR_DESIRED_LEVEL_OF_CONCURRENCY_TOTAL>)
                        .build());
        JestClient client = factory.getObject();
        this.client = client;
    }

    @Test
    public void createIndex() throws Exception {
        client.execute(new CreateIndex.Builder("articles").build());
    }

    @Test
    public void createIndexWithSetting() throws Exception {
        String settings = "\"settings\" : {\n" +
                "        \"number_of_shards\" : 5,\n" +
                "        \"number_of_replicas\" : 1\n" +
                "    }\n";

        // client.execute(new CreateIndex.Builder("articles").settings(Settings.builder().loadFromSource(settings, XContentType.JSON).build().getAs).build());
    }
}
