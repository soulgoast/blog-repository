/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * @ClassName InitialConversion
 * @Description
 * 问题springmvc将对象转换位json格式字符串的时候，如果实体类中包含字段xDistance，前段收到该字段将会变成xdistance。下面就对这个问题出现的原因一探究竟。
 *
 * springboot中使用jackson来进行实体列与json字符串之间的转换。难道这个问题是由于jackson引起的？
 * 通过以下例子证实，还真是。{"ydistance":"200","xdistance":"100"}
 *
 *
 *
 *
 *
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/6/22 14:13
 * @ModifyDate 2020/6/22 14:13
 * @Version 1.0
 */
public class InitialConversion {

    @Test
    public void reason() throws JsonProcessingException {
        Position position = new Position("100", "200");

        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(position);
        System.out.println(result);

    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Position {

    private String xDistance;

    private String yDistance;
}
