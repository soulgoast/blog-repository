/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.algorith;

import org.junit.jupiter.api.Test;

/**
 * @ClassName GreatestCommonDivisor
 * @Description 求最大公约数
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2019/12/10 14:57
 * @ModifyDate 2019/12/10 14:57
 * @Version 1.0
 */
public class GreatestCommonDivisor {

    /**
     * 使用欧几里得算法求两个自然数的最大公约数
     *
     * 证明c为a、b的因子
     * {a / b = n...c; b / c = m...d; c / d = o...0} =>
     * {a / b = n...c; b / c = m...0;} =>
     * {a = nb + c; b = cm},m、n为整数 =>
     * {a = mnc + c} =>
     * {a = (mn + 1) * c},得出c为a的因子
     *
     * 证明c为a、b最大的因子
     * 假设两个正整数的计算过程是这样的，{a / b = n...c; b / c = m...0;}
     * a = (mn + 1) c;
     * b = mc;
     * 因为c的取值范围为0 < c < b, 所以m为大于1的正整数
     * a、b同时除以c，得到新的正整数i、j
     * i = mn + 1;
     * j = m;
     * 如果a、b还有比c更大的公约数，说明i和j有大于1的公约数。
     * i / j = n...1/m; 因为m为大于1的正整数，所以i、j不存在大于1的公约数。
     * 所以c为a、b的最大公约数。
     *
     * 最大公约数的传递性。
     * {a / b = n...c; b / c = m...d; c / d = o...0} =>
     * c = od;
     * b = mc + d = mod + d = (mo + 1) d;
     * a = nb + c = n(mo + 1)d + od =(mno + n + o)d;
     * a、b同时除以d，得到新的正整数i、j
     * i = mno + n + o;
     * j = mo + 1;
     * 如果a、b还有比d更大的公约数，说明i和j有大于1的公约数。
     * i / j = (n(mo + 1) + o) / (mo + 1) = n + o / (mo + 1)
     * 因为m、o为大于1的正整数，即o / (mo + 1)小于1，所以i、j不存在大于1的公约数。
     * 所以d为a、b的最大公约数。
     *
     * @param a 自然数
     * @param b 自然数
     * @return 最大公约数,若b为0，则最大公约数是a
     */
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        int r = a % b;
        return gcd(b, r);
    }

    @Test
    public void gcd() {
        int r = gcd(54, 40);
        assert r == 2;
    }
}
