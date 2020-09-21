/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package goodman.java.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.*;

/**
 * @ClassName ObjectOutputStreamTest
 * @Description
 * 1 下载HexEditor
 * https://github.com/chcg/NPP_HexEdit/releases
 * 还是github靠谱
 * 1、Notepad++可以编辑PE文件（二进制文件即HEX码），2进制、16进制都可以，通过附加的组件HexEditor即可实现。另外一款Notepad++自带插件TextFX也有这个功能，
 * 但实现效果不如Hex Editor。下载地址：https://sourceforge.net/projects/npp-plugins/files/Hex%20Editor/Hex%20Editor%20Plugin%20v0.9.5/HexEditor_0_9_5_UNI_dll.zip/download?use_mirror=nchc
 *
 * 2、以Hex Editor为例， 下载插件后，解压缩后把HexEditor.dll文件复制到安装目录（如C:\Program Files\Notepad++\plugins），
 * 退出NotePad++重新打开即可！需要以十六进制显示时，点击菜单的：【插件】－【Hex-Editor】－【View in Hex】即可
 * 参阅：https://blog.csdn.net/hongshuling1996/article/details/77430783
 *
 *
 * 2
 *
 * @Author soul goodman
 * @email m18967896507_1@163.com
 * @Date 2020/8/22 15:20
 * @ModifyDate 2020/8/22 15:20
 * @Version 1.0
 */
public class ObjectOutputStreamTest {

    @Test
    public void writeObjectPerson() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\software\\aa.txt");
        ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
        Person person = new Person("张三", 12);
        objectInputStream.writeObject(person);
        objectInputStream.close();
    }

    @Test
    public void writeObjectSon() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\software\\bb.txt");
        ObjectOutputStream objectInputStream = new ObjectOutputStream(fileOutputStream);
        Son son = new Son("张三", 12);
        objectInputStream.writeObject(son);
        objectInputStream.close();
    }

    @Test
    public void test() {
        System.out.println(ObjectStreamConstants.STREAM_VERSION);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person implements Serializable {

    public static final long serialVersionUID = 1L;

    private String name;

    private int age;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Son implements Serializable {

    public static final long serialVersionUID = 1L;

    private String parent;

    private int age;


}