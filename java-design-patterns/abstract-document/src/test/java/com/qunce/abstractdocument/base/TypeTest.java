package com.qunce.abstractdocument.base;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @ClassName TypeTest
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/9/21 14:16
 * @ModifyDate 2020/9/21 14:16
 * @Version 1.0
 */
public class TypeTest {

    @Test
    public void testList() {
        String source = "[{'identifier': '属性唯一标识符（产品下唯一）'},{'name': '属性名称'}]";
        Gson gson = new Gson();
        Object object = gson.fromJson(source, Object.class);
        System.out.println(object.getClass().getName());
    }

    @Test
    public void testMap() {
        String source = "{'identifier': '属性唯一标识符（产品下唯一）'}";
        Gson gson = new Gson();
        Object object = gson.fromJson(source, Object.class);
        System.out.println(object.getClass().getSuperclass().getName());
    }

    @Test
    public void testInstanceOf() {
        String source = "[{'identifier': '属性唯一标识符（产品下唯一）'},{'name': '属性名称'}]";
        Gson gson = new Gson();
        Object object = gson.fromJson(source, Object.class);

        if (object instanceof List) {
            System.out.println("该对象的List类型");
        }
    }

}
