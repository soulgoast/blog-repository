/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.spi.transport;

import goodman.java.io.IOException;
import goodman.java.net.InetSocketAddress;
import goodman.java.net.Socket;
import goodman.java.net.SocketException;
import goodman.java.net.ServerSocket;

import com.sun.corba.se.pept.transport.Acceptor;
import com.sun.corba.se.spi.orb.ORB;

/**
 * @author Harold Carr
 */
public interface ORBSocketFactory
{
    public void setORB(ORB orb);

    public ServerSocket createServerSocket(String type,
                                           InetSocketAddress inetSocketAddress)
        throws IOException;

    public Socket createSocket(String type,
                               InetSocketAddress inetSocketAddress)
        throws IOException;

    public void setAcceptedSocketOptions(Acceptor acceptor,
                                         ServerSocket serverSocket,
                                         Socket socket)
        throws SocketException;

}

// End of file.
