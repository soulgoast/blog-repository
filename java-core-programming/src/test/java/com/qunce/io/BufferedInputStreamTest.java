package com.qunce.io;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class BufferedInputStreamTest {

    @Test
    public void test() throws IOException {
        byte[] byteArray = new byte[] {1, 2, 3, 4, 5, 6, 7, 8};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
        byte[] bytes = IOUtils.toByteArray(bufferedInputStream);
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
    }

}
