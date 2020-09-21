/*
 * Copyright (c) 2010, Oracle and/or its affiliates. All rights reserved.
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
import goodman.java.io.FileDescriptor;
import goodman.java.net.PlainSocketImpl;

import sun.net.sdp.SdpSupport;

/**
 * SocketImpl that supports the SDP protocol
 */
class SdpSocketImpl extends PlainSocketImpl {
    SdpSocketImpl() { }

    @Override
    protected void create(boolean stream) throws IOException {
        if (!stream)
            throw new UnsupportedOperationException("Must be a stream socket");
        fd = SdpSupport.createSocket();
        if (socket != null)
            socket.setCreated();
        if (serverSocket != null)
            serverSocket.setCreated();
    }
}
