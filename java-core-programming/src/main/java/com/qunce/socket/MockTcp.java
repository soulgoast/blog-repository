package com.qunce.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Description TODO
 * @Author hu zhongxi
 */
public class MockTcp {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("10.8.11.66", 4321);
        SocketAddress remoteSocketAddress = socket.getRemoteSocketAddress();
        System.out.println(remoteSocketAddress);
        SocketChannel channel = socket.getChannel();
        channel.write(ByteBuffer.wrap("hello".getBytes()));
        channel.close();
    }

}
