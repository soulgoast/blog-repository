/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.utils;

import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.Base64;

/**
 * @ClassName Base64Utils
 * @Description 使用base64进行编解码
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/11/22 9:33
 * @ModifyDate 2019/11/22 9:33
 * @Version 1.0
 */
public class Base64Utils {

    public static String base64Encode(String encodeText) throws IOException{
        return new String(Base64.getEncoder().encode(encodeText.getBytes("UTF-8")));
    }


    /**
     * Base64 解码
     * @param decodeText
     * @return
     * @throws IOException
     */
    public static byte[] base64Decode(String decodeText) throws IOException {
        return Base64.getDecoder().decode(decodeText);
    }

    @Test
    public void base64Encode() throws IOException {
        String password = "admin";
        String encodeStirng = Base64Utils.base64Encode(password);
        System.out.println(encodeStirng);
    }


}
