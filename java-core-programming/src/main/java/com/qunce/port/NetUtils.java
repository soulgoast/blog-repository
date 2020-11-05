package com.qunce.port;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName NetUtils
 * @Description TODO
 * @Author hu zhongxi
 * @email m18967896507_1@163.com
 * @Date 2020/11/3 19:02
 * @ModifyDate 2020/11/3 19:02
 * @Version 1.0
 */
public class NetUtils {


    public static boolean isLocalPortUsing(int port){
        boolean flag = true;
        try {
            flag = isUsingPort("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    public static boolean isUsingPort(String host, int port) {
        System.out.println("host:" + host);
        System.out.println("port:" + port);
        boolean flag = false;
        try {
            InetAddress theAddress = InetAddress.getByName(host);
            Socket socket = new Socket(theAddress,port);
            flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8849;
        //InetAddress theAddress = InetAddress.getByName(host);
         ServerSocket serverSocket = new ServerSocket(port);
        //Socket socket = new Socket(host, port);
    }
}
