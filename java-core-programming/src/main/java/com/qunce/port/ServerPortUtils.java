package com.qunce.port;


import java.util.Random;

/**
 * @ClassName ServerPortUtils
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/11/3 19:02
 * @ModifyDate 2020/11/3 19:02
 * @Version 1.0
 */
public class ServerPortUtils {

    private static final int MAX = 65535;

    private static final int MIN = 2000;

    public static int getAvailablePort(int port) {
        boolean using = NetUtils.isLocalPortUsing(port);
        if (using) {
            return getAvailablePort(++port);
        } else {
            return port;
        }
    }

    public static void main(String[] args) {
        int port = getAvailablePort(8848);
        System.out.println(port);
    }

}
