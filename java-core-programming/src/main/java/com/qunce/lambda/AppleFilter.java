package com.qunce.lambda;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppleFilter {


    interface AppleFilterInterface {
        boolean filter(Apple apple);
    }

    static class YellowAppleFilterImpl implements AppleFilterInterface {
        @Override
        public boolean filter(Apple apple) {
            return "yellow".equals(apple.getColor());
        }
    }

    public static List<Apple> filterGreen(List<Apple> apples) {
        List<Apple> greenApples = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor())) {
                greenApples.add(apple);
            }
        }
        return greenApples;
    }

    public static List<Apple> filterGreenThan150(List<Apple> apples) {
        List<Apple> greenAnd150Apples = new ArrayList<>();
        for (Apple apple : apples) {
            if ("green".equals(apple.getColor()) && (new BigDecimal(apple.getWeight()).compareTo(new BigDecimal(150)) >= 0)) {
                greenAnd150Apples.add(apple);
            }
        }
        return greenAnd150Apples;
    }

    public static List<Apple> filterApples(List<Apple> apples, AppleFilterInterface appleFilter) {
        List<Apple> screenedApples = new ArrayList<>();
        for (Apple apple : apples) {
            if (appleFilter.filter(apple)) {
                screenedApples.add(apple);
            }
        }
        return screenedApples;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green", 130), new Apple("green", 150), new Apple("yellow", 160));
        List<Apple> greenApples = filterGreen(apples);
        System.out.println(greenApples);

        List<Apple> greemThan150Apples = filterGreenThan150(apples);
        System.out.println(greemThan150Apples);

        List<Apple> yellowApples = filterApples(apples, new YellowAppleFilterImpl());
        System.out.println(yellowApples);
    }
}
