/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package com.qunce.lambda.example;

import com.qunce.lambda.Artist;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @ClassName CDemo
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/6/20 21:23
 * @ModifyDate 2020/6/20 21:23
 * @Version 1.0
 */
public class CDemo {

    public static void main(String[] args) {
        List<Artist> allArtist = new ArrayList<>();
        Artist artist = new Artist("Flower", new ArrayList<>(), "London");
        allArtist.add(artist);
/*        allArtist.stream().filter(item -> {
            System.out.println("from:" + item.getNationality());
            return item.getNationality().equals("London");
        });*/
        Long count = allArtist.stream().filter(item -> {
            System.out.println("from:" + item.getNationality());
            return item.getNationality().equals("London");
        }).count();
        System.out.println("来自伦敦的艺术家的数量为：" + count);
    }

    @Test
    public void test() {
        List<String> collected = Stream.of("a", "b", "c") .collect(Collectors.toList());

        assertEquals(Arrays.asList("a", "b", "c"), collected);
    }

    /**
     * 合并stream
     */
    @Test
    public void flatMap() {
        List<String> together = Stream.of(Arrays.asList("google", "baidu"), Arrays.asList("facebook"))
                .flatMap(members -> members.stream()).collect(Collectors.toList());
        System.out.println(together);

        together = Stream.of("google", "baidu")
                .collect(Collectors.toList());

        int count = Stream.of(1, 2).reduce(0, (acc, element) -> acc + element);
        System.out.println(count);
    }

    @Test
    public void reduce() {
        Stream<Integer> numbers = Stream.of(1, 2, 3);
        numbers.reduce(0, Integer::sum);
    }

}