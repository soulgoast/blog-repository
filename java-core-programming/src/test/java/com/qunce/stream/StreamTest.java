package com.qunce.stream;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class StreamTest {

    @Test
    public void test() {
        IntStream iterateStream = IntStream.iterate(1, n -> n + 1);
        iterateStream.forEach(System.out::println);
    }

}
