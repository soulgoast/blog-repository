package com.qunce.javatuples;

import org.javatuples.Unit;
import org.junit.Test;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/11/3 18:25
 * @ModifyDate 2020/11/3 18:25
 * @Version 1.0
 */
public class Demo {

    @Test
    public void UnitTest() {
        Unit<String> unit = new Unit<>("google");
        String value0 = unit.getValue0();
        System.out.println(value0);
    }

}
