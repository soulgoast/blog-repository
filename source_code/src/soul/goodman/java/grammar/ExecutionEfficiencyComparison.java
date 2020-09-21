/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package goodman.java.grammar;

import org.junit.Test;

/**
 * @ClassName ExecutionEfficiencyComparison
 * @Description 执行效率对比
 *
 * 1. if else 与 switch case  性能相近，没得差异
 * 2. 集中循环遍历的方法对比
 *      a.for(int i = 0; i < len; i++)
 *      b.for(String a : list)
 *      c.foreach
 * 参考：https://www.cnblogs.com/xyyt/p/10815432.html
 * ===============================================================
 * 问题一：原理解析
 * 问题二：原理解析
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/25 9:22
 * @ModifyDate 2020/8/25 9:22
 * @Version 1.0
 */
public class ExecutionEfficiencyComparison {

    @Test // 36300
    public void ifElse() {
        String aaa = "j";
        long t1 = System.nanoTime();
        if("a".equals(aaa)){
            System.out.println(aaa);
        } else if ("b".equals(aaa)) {
            System.out.println(aaa);
        } else if ("c".equals(aaa)) {
            System.out.println(aaa);
        } else if ("d".equals(aaa)) {
            System.out.println(aaa);
        } else if ("e".equals(aaa)) {
            System.out.println(aaa);
        } else if ("f".equals(aaa)) {
            System.out.println(aaa);
        } else if ("g".equals(aaa)) {
            System.out.println(aaa);
        } else if ("h".equals(aaa)) {
            System.out.println(aaa);
        } else if ("i".equals(aaa)) {
            System.out.println(aaa);
        } else if ("j".equals(aaa)) {
            System.out.println(aaa);
        } else if ("k".equals(aaa)) {
            System.out.println(aaa);
        } else if ("l".equals(aaa)) {
            System.out.println(aaa);
        } else if ("m".equals(aaa)) {
            System.out.println(aaa);
        } else if ("n".equals(aaa)) {
            System.out.println(aaa);
        } else {
            System.out.println(aaa);
        }
        long t2 = System.nanoTime();
        System.out.println("if :　" + (t2 - t1));
    }

    @Test // 34999
    public void switchCase() {
        String aaa = "j";
        long t1 = System.nanoTime();
        switch (aaa) {
            case "a":
                System.out.println(aaa);
                break;
            case "b":
                System.out.println(aaa);
                break;
            case "c":
                System.out.println(aaa);
                break;
            case "d":
                System.out.println(aaa);
                break;
            case "e":
                System.out.println(aaa);
                break;
            case "f":
                System.out.println(aaa);
                break;
            case "g":
                System.out.println(aaa);
                break;
            case "h":
                System.out.println(aaa);
                break;
            case "i":
                System.out.println(aaa);
                break;
            case "j":
                System.out.println(aaa);
                break;
            case "k":
                System.out.println(aaa);
                break;
            case "l":
                System.out.println(aaa);
                break;
            case "m":
                System.out.println(aaa);
                break;
            case "n":
                System.out.println(aaa);
                break;
            default:
                System.out.println(aaa);
                break;
        }
        long t2 = System.nanoTime();
        System.out.println("switch :　" + (t2 - t1));
    }
}
