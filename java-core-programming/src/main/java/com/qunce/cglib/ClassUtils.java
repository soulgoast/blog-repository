/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.cglib;

import com.fasterxml.jackson.databind.util.ClassUtil;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * @ClassName ClassUtils
 * @Description TODO
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/4/14 13:33
 * @ModifyDate 2020/4/14 13:33
 * @Version 1.0
 */
public class ClassUtils {
    private String filePath = "/config/"; //配置文件路径

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Object dynamicClass(Object object) throws Exception {
        HashMap returnMap = new HashMap();
        HashMap typeMap = new HashMap();
        //读取配置文件
        Properties prop = new Properties();
        String sourcepackage = object.getClass().getName();
        String classname = sourcepackage.substring(sourcepackage.lastIndexOf(".") + 1);
        InputStream in = ClassUtil.class.getResourceAsStream(filePath + classname + ".properties");
        prop.load(in);

        Set<String> keylist = prop.stringPropertyNames();

        Class type = object.getClass();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for(int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if(!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(object, new Object[0]);
                if(result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
                typeMap.put(propertyName, descriptor.getPropertyType());
            }
        }
        //加载配置文件中的属性
        Iterator<String> iterator = keylist.iterator();
        while(iterator.hasNext()) {
            String key = iterator.next();
            returnMap.put(key, prop.getProperty(key));
            typeMap.put(key, Class.forName("java.lang.String"));
        }
        //map转换成实体对象
        DynamicBean bean = new DynamicBean(typeMap);
        //赋值
        Set keys = typeMap.keySet();
        for(Iterator it = keys.iterator(); it.hasNext(); ) {
            String key = (String) it.next();
            bean.setValue(key, returnMap.get(key));
        }
        Object obj = bean.getObject();
        return obj;
    }

    public static void main(String[] args) throws Exception {
        new ClassUtils().dynamicClass(new LeapRole()/*LeapRole是个普通类，未贴源码*/);
    }
}

class LeapRole {

}
