/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package goodman.java.nio;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @ClassName MappedByteBufferTest
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/8/24 21:47
 * @ModifyDate 2020/8/24 21:47
 * @Version 1.0
 */
public class MappedByteBufferTest {

    public static void main(String[] args) {
        File file = new File("D:\\software\\aa.txt");
        long len = file.length();
        byte[] ds = new byte[(int) len];

        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r").getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, len);
            for (int offset = 0; offset < len; offset++) {
                byte b = mappedByteBuffer.get();
                ds[offset] = b;
            }

            Scanner scanner = new Scanner(new ByteArrayInputStream(ds)).useDelimiter(" ");
            while (scanner.hasNext()) {
                System.out.println(scanner.next() + " ");
            }
        } catch (Exception e) {

        }
    }
}