/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package goodman.java.net;

import goodman.java.io.IOException;
import goodman.java.net.DatagramSocket;
import goodman.java.net.ServerSocket;
import goodman.java.net.Socket;
import goodman.java.net.SocketImpl;
import goodman.java.net.SocketOption;

class SocketSecrets {

    /* accessed by reflection from jdk.net.Sockets */

    /* obj must be a Socket or ServerSocket */

    private static <T> void setOption(Object obj, java.net.SocketOption<T> name, T value) throws IOException {
        java.net.SocketImpl impl;

        if (obj instanceof java.net.Socket) {
            impl = ((java.net.Socket)obj).getImpl();
        } else if (obj instanceof java.net.ServerSocket) {
            impl = ((java.net.ServerSocket)obj).getImpl();
        } else {
            throw new IllegalArgumentException();
        }
        impl.setOption(name, value);
    }

    private static <T> T getOption(Object obj, java.net.SocketOption<T> name) throws IOException {
        SocketImpl impl;

        if (obj instanceof java.net.Socket) {
            impl = ((Socket)obj).getImpl();
        } else if (obj instanceof java.net.ServerSocket) {
            impl = ((ServerSocket)obj).getImpl();
        } else {
            throw new IllegalArgumentException();
        }
        return impl.getOption(name);
    }

    private static <T> void setOption(DatagramSocket s, java.net.SocketOption<T> name, T value) throws IOException {
        s.getImpl().setOption(name, value);
    }

    private static <T> T getOption(DatagramSocket s, SocketOption<T> name) throws IOException {
        return s.getImpl().getOption(name);
    }

}
