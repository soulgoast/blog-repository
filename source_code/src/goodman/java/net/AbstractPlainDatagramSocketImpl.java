/*
 * Copyright (c) 1996, 2015, Oracle and/or its affiliates. All rights reserved.
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

import goodman.java.io.FileDescriptor;
import goodman.java.io.IOException;
import goodman.java.net.DatagramPacket;
import goodman.java.net.DatagramSocketImpl;
import goodman.java.net.InetAddress;
import goodman.java.net.InetSocketAddress;
import goodman.java.net.NetworkInterface;
import goodman.java.net.SocketAddress;
import goodman.java.net.SocketException;
import goodman.java.security.AccessController;
import sun.net.ResourceManager;

/**
 * Abstract datagram and multicast socket implementation base class.
 * Note: This is not a public class, so that applets cannot call
 * into the implementation directly and hence cannot bypass the
 * security checks present in the DatagramSocket and MulticastSocket
 * classes.
 *
 * @author Pavani Diwanji
 */

abstract class AbstractPlainDatagramSocketImpl extends DatagramSocketImpl
{
    /* timeout value for receive() */
    int timeout = 0;
    boolean connected = false;
    private int trafficClass = 0;
    protected java.net.InetAddress connectedAddress = null;
    private int connectedPort = -1;

    private static final String os = AccessController.doPrivileged(
        new sun.security.action.GetPropertyAction("os.name")
    );

    /**
     * flag set if the native connect() call not to be used
     */
    private final static boolean connectDisabled = os.contains("OS X");

    /**
     * Load net library into runtime.
     */
    static {
        AccessController.doPrivileged(
            new java.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.loadLibrary("net");
                    return null;
                }
            });
    }

    /**
     * Creates a datagram socket
     */
    protected synchronized void create() throws java.net.SocketException {
        ResourceManager.beforeUdpCreate();
        fd = new FileDescriptor();
        try {
            datagramSocketCreate();
        } catch (java.net.SocketException ioe) {
            ResourceManager.afterUdpClose();
            fd = null;
            throw ioe;
        }
    }

    /**
     * Binds a datagram socket to a local port.
     */
    protected synchronized void bind(int lport, java.net.InetAddress laddr)
        throws java.net.SocketException {
        bind0(lport, laddr);
    }

    protected abstract void bind0(int lport, java.net.InetAddress laddr)
        throws java.net.SocketException;

    /**
     * Sends a datagram packet. The packet contains the data and the
     * destination address to send the packet to.
     * @param p the packet to be sent.
     */
    protected abstract void send(java.net.DatagramPacket p) throws IOException;

    /**
     * Connects a datagram socket to a remote destination. This associates the remote
     * address with the local socket so that datagrams may only be sent to this destination
     * and received from this destination.
     * @param address the remote InetAddress to connect to
     * @param port the remote port number
     */
    protected void connect(java.net.InetAddress address, int port) throws java.net.SocketException {
        connect0(address, port);
        connectedAddress = address;
        connectedPort = port;
        connected = true;
    }

    /**
     * Disconnects a previously connected socket. Does nothing if the socket was
     * not connected already.
     */
    protected void disconnect() {
        disconnect0(connectedAddress.holder().getFamily());
        connected = false;
        connectedAddress = null;
        connectedPort = -1;
    }

    /**
     * Peek at the packet to see who it is from.
     * @param i the address to populate with the sender address
     */
    protected abstract int peek(java.net.InetAddress i) throws IOException;
    protected abstract int peekData(java.net.DatagramPacket p) throws IOException;
    /**
     * Receive the datagram packet.
     * @param p the packet to receive into
     */
    protected synchronized void receive(java.net.DatagramPacket p)
        throws IOException {
        receive0(p);
    }

    protected abstract void receive0(DatagramPacket p)
        throws IOException;

    /**
     * Set the TTL (time-to-live) option.
     * @param ttl TTL to be set.
     */
    protected abstract void setTimeToLive(int ttl) throws IOException;

    /**
     * Get the TTL (time-to-live) option.
     */
    protected abstract int getTimeToLive() throws IOException;

    /**
     * Set the TTL (time-to-live) option.
     * @param ttl TTL to be set.
     */
    @Deprecated
    protected abstract void setTTL(byte ttl) throws IOException;

    /**
     * Get the TTL (time-to-live) option.
     */
    @Deprecated
    protected abstract byte getTTL() throws IOException;

    /**
     * Join the multicast group.
     * @param inetaddr multicast address to join.
     */
    protected void join(java.net.InetAddress inetaddr) throws IOException {
        join(inetaddr, null);
    }

    /**
     * Leave the multicast group.
     * @param inetaddr multicast address to leave.
     */
    protected void leave(java.net.InetAddress inetaddr) throws IOException {
        leave(inetaddr, null);
    }
    /**
     * Join the multicast group.
     * @param mcastaddr multicast address to join.
     * @param netIf specifies the local interface to receive multicast
     *        datagram packets
     * @throws  IllegalArgumentException if mcastaddr is null or is a
     *          SocketAddress subclass not supported by this socket
     * @since 1.4
     */

    protected void joinGroup(java.net.SocketAddress mcastaddr, java.net.NetworkInterface netIf)
        throws IOException {
        if (mcastaddr == null || !(mcastaddr instanceof java.net.InetSocketAddress))
            throw new IllegalArgumentException("Unsupported address type");
        join(((java.net.InetSocketAddress)mcastaddr).getAddress(), netIf);
    }

    protected abstract void join(java.net.InetAddress inetaddr, java.net.NetworkInterface netIf)
        throws IOException;

    /**
     * Leave the multicast group.
     * @param mcastaddr  multicast address to leave.
     * @param netIf specified the local interface to leave the group at
     * @throws  IllegalArgumentException if mcastaddr is null or is a
     *          SocketAddress subclass not supported by this socket
     * @since 1.4
     */
    protected void leaveGroup(SocketAddress mcastaddr, java.net.NetworkInterface netIf)
        throws IOException {
        if (mcastaddr == null || !(mcastaddr instanceof java.net.InetSocketAddress))
            throw new IllegalArgumentException("Unsupported address type");
        leave(((InetSocketAddress)mcastaddr).getAddress(), netIf);
    }

    protected abstract void leave(java.net.InetAddress inetaddr, java.net.NetworkInterface netIf)
        throws IOException;

    /**
     * Close the socket.
     */
    protected void close() {
        if (fd != null) {
            datagramSocketClose();
            ResourceManager.afterUdpClose();
            fd = null;
        }
    }

    protected boolean isClosed() {
        return (fd == null) ? true : false;
    }

    protected void finalize() {
        close();
    }

    /**
     * set a value - since we only support (setting) binary options
     * here, o must be a Boolean
     */

     public void setOption(int optID, Object o) throws java.net.SocketException {
         if (isClosed()) {
             throw new java.net.SocketException("Socket Closed");
         }
         switch (optID) {
            /* check type safety b4 going native.  These should never
             * fail, since only java.Socket* has access to
             * PlainSocketImpl.setOption().
             */
         case SO_TIMEOUT:
             if (o == null || !(o instanceof Integer)) {
                 throw new java.net.SocketException("bad argument for SO_TIMEOUT");
             }
             int tmp = ((Integer) o).intValue();
             if (tmp < 0)
                 throw new IllegalArgumentException("timeout < 0");
             timeout = tmp;
             return;
         case IP_TOS:
             if (o == null || !(o instanceof Integer)) {
                 throw new java.net.SocketException("bad argument for IP_TOS");
             }
             trafficClass = ((Integer)o).intValue();
             break;
         case SO_REUSEADDR:
             if (o == null || !(o instanceof Boolean)) {
                 throw new java.net.SocketException("bad argument for SO_REUSEADDR");
             }
             break;
         case SO_BROADCAST:
             if (o == null || !(o instanceof Boolean)) {
                 throw new java.net.SocketException("bad argument for SO_BROADCAST");
             }
             break;
         case SO_BINDADDR:
             throw new java.net.SocketException("Cannot re-bind Socket");
         case SO_RCVBUF:
         case SO_SNDBUF:
             if (o == null || !(o instanceof Integer) ||
                 ((Integer)o).intValue() < 0) {
                 throw new java.net.SocketException("bad argument for SO_SNDBUF or " +
                                           "SO_RCVBUF");
             }
             break;
         case IP_MULTICAST_IF:
             if (o == null || !(o instanceof java.net.InetAddress))
                 throw new java.net.SocketException("bad argument for IP_MULTICAST_IF");
             break;
         case IP_MULTICAST_IF2:
             if (o == null || !(o instanceof NetworkInterface))
                 throw new java.net.SocketException("bad argument for IP_MULTICAST_IF2");
             break;
         case IP_MULTICAST_LOOP:
             if (o == null || !(o instanceof Boolean))
                 throw new java.net.SocketException("bad argument for IP_MULTICAST_LOOP");
             break;
         default:
             throw new java.net.SocketException("invalid option: " + optID);
         }
         socketSetOption(optID, o);
     }

    /*
     * get option's state - set or not
     */

    public Object getOption(int optID) throws java.net.SocketException {
        if (isClosed()) {
            throw new java.net.SocketException("Socket Closed");
        }

        Object result;

        switch (optID) {
            case SO_TIMEOUT:
                result = new Integer(timeout);
                break;

            case IP_TOS:
                result = socketGetOption(optID);
                if ( ((Integer)result).intValue() == -1) {
                    result = new Integer(trafficClass);
                }
                break;

            case SO_BINDADDR:
            case IP_MULTICAST_IF:
            case IP_MULTICAST_IF2:
            case SO_RCVBUF:
            case SO_SNDBUF:
            case IP_MULTICAST_LOOP:
            case SO_REUSEADDR:
            case SO_BROADCAST:
                result = socketGetOption(optID);
                break;

            default:
                throw new java.net.SocketException("invalid option: " + optID);
        }

        return result;
    }

    protected abstract void datagramSocketCreate() throws java.net.SocketException;
    protected abstract void datagramSocketClose();
    protected abstract void socketSetOption(int opt, Object val)
        throws java.net.SocketException;
    protected abstract Object socketGetOption(int opt) throws java.net.SocketException;

    protected abstract void connect0(InetAddress address, int port) throws SocketException;
    protected abstract void disconnect0(int family);

    protected boolean nativeConnectDisabled() {
        return connectDisabled;
    }

    abstract int dataAvailable();
}
