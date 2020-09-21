package com.qunce.java.core.programming.collection;

public class ListNoGeneric {

    public static void main(String[] args) {
        System.out.println(Math.abs(-2147483648));
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY);
        System.out.println(1.0 / 0.0);

        int a = 10;
        int b = 6;
        System.out.println(a & b);
        System.out.println(a | b);
        System.out.println(a ^ b);
        System.out.println(~a);
        System.out.println(~b);
        System.out.println(true && false || true && true);
    }
}
