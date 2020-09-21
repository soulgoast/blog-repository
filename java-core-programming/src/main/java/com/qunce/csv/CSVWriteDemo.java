/*
 * Copyright (c) 1995, 2100, QunCe and/or its affiliates. All rights reserved.
 * QUNCE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */

package com.qunce.csv;

import java.io.*;
import java.util.Random;

/**
 * @ClassName CSVWriteDemo
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/8/20 16:55
 * @ModifyDate 2020/8/20 16:55
 * @Version 1.0
 */
public class CSVWriteDemo {

    public static void main(String[] args) {
        //第一步：设置输出的文件路径
        //如果该目录下不存在该文件，则文件会被创建到指定目录下。如果该目录有同名文件，那么该文件将被覆盖。
        File writeFile = new File("E:\\任务汇总数据.csv");

        try{
            Random random = new Random();
            //第二步：通过BufferedReader类创建一个使用默认大小输出缓冲区的缓冲字符输出流
            BufferedWriter writeText = new BufferedWriter(new FileWriter(writeFile));

            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            writeText.write("taskNo,failNum,pushNum,successNum,unpushNum,count");
            for(int i=1;i<=1000000;i++){
                writeText.newLine();    //换行
                //调用write的方法将字符串写到流中
                writeText.write("taskNoHZX00"+random.nextInt(10) +",1,1,1,1,1");
            }

            //使用缓冲区的刷新方法将数据刷到目的地中
            writeText.flush();
            //关闭缓冲区，缓冲区没有调用系统底层资源，真正调用底层资源的是FileWriter对象，缓冲区仅仅是一个提高效率的作用
            //因此，此处的close()方法关闭的是被缓存的流对象
            writeText.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
    }

}
