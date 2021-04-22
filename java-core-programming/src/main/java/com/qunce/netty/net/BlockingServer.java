package com.qunce.netty.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class BlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8878);
        Socket accept = serverSocket.accept();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(accept.getOutputStream()));
        String request, response;
        while ((request = bufferedReader.readLine()) != null) {

        }
    }

}
