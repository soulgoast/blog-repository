package com.qunce.lambda;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleStream {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(new Dish("apple", 200),
                new Dish("banana", 300), new Dish("milk", 400),
                new Dish("orange", 500));
        List<String> dishNames = getDishNames(menu);
        System.out.println(dishNames);

        List<String> names = menu.stream().filter(dish -> dish.getCalories() <= 300).sorted(Comparator.comparing(Dish::getCalories)).map(dish -> dish.getName()).collect(Collectors.toList());
        System.out.println(names);
    }

    private static List<String> getDishNames(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();
        for (Dish dish : menu) {
            if (300 >= dish.getCalories()) {
                lowCalories.add(dish);
            }
        }
        Collections.sort(lowCalories, Comparator.comparing(Dish::getCalories));

        List<String> names = new ArrayList<>();
        for (Dish dish : lowCalories) {
            names.add(dish.getName());
        }
        return names;
    }
}
