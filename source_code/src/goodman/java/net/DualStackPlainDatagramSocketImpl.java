/*
 * Copyright (c) 2007, 2015, Oracle and/or its affiliates. All rights reserved.
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
import goodman.java.net.AbstractPlainDatagramSocketImpl;
import goodman.java.net.DatagramPacket;
import goodman.java.net.InetAddress;
import goodman.java.net.NetworkInterface;
import goodman.java.net.SocketException;

import sun.misc.SharedSecrets;
import sun.misc.JavaIOFileDescriptorAccess;

/**
 * This class defines the plain DatagramSocketImpl that is used on
 * Windows platforms greater than or equal to Windows Vista. These
 * platforms have a dual layer TCP/IP stack and can handle both IPv4
 * and IPV6 through a single file descriptor.
 * <p>
 * Note: Multicasting on a dual layer TCP/IP stack is always done with
 * TwoStacksPlainDatagramSocketImpl. This is to overcome the lack
 * of behavior defined for multicasting over a dual layer socket by the RFC.
 *
 * @author Chris Hegarty
 */

class DualStackPlainDatagramSocketImpl extends java.net.AbstractPlainDatagramSocketImpl
{
    static JavaIOFileDescriptorAccess fdAccess = SharedSecrets.getJavaIOFileDescriptorAccess();

    static {
        initIDs();
    }

    // true if this socket is exclusively bound
    private final boolean exclusiveBind;

    /*
     * Set to true if SO_REUSEADDR is set after the socket is bound to
     * indicate SO_REUSEADDR is being emulated
     */
    private boolean reuseAddressEmulated;

    // emulates SO_REUSEADDR when exclusiveBind is true and socket is bound
    private boolean isReuseAddress;

    DualStackPlainDatagramSocketImpl(boolean exclBind) {
        exclusiveBind = exclBind;
    }

    protected void datagramSocketCreate() throws java.net.SocketException {
        if (fd == null)
            throw new java.net.SocketException("Socket closed");

        int newfd = socketCreate(false /* v6Only */);

        fdAccess.set(fd, newfd);
    }

    protected synchronized void bind0(int lport, java.net.InetAddress laddr)
        throws java.net.SocketException {
        int nativefd = checkAndReturnNativeFD();

        if (laddr == null)
            throw new NullPointerException("argument address");

        socketBind(nativefd, laddr, lport, exclusiveBind);
        if (lport == 0) {
            localPort = socketLocalPort(nativefd);
        } else {
            localPort = lport;
        }
    }

    protected synchronized int peek(java.net.InetAddress address) throws IOException {
        int nativefd = checkAndReturnNativeFD();

        if (address == null)
            throw new NullPointerException("Null address in peek()");

        // Use peekData()
        java.net.DatagramPacket peekPacket = new java.net.DatagramPacket(new byte[1], 1);
        int peekPort = peekData(peekPacket);
        address = peekPacket.getAddress();
        return peekPort;
    }

    protected synchronized int peekData(java.net.DatagramPacket p) throws IOException {
        int nativefd = checkAndReturnNativeFD();

        if (p == null)
            throw new NullPointerException("packet");
        if (p.getData() == null)
            throw new NullPointerException("packet buffer");

        return socketReceiveOrPeekData(nativefd, p, timeout, connected, true /*peek*/);
    }

    protected synchronized void receive0(java.net.DatagramPacket p) throws IOException {
        int nativefd = checkAndReturnNativeFD();

        if (p == null)
            throw new NullPointerException("packet");
        if (p.getData() == null)
            throw new NullPointerException("packet buffer");

        socketReceiveOrPeekData(nativefd, p, timeout, connected, false /*receive*/);
    }

    protected void send(java.net.DatagramPacket p) throws IOException {
        int nativefd = checkAndReturnNativeFD();

        if (p == null)
            throw new NullPointerException("null packet");

        if (p.getAddress() == null ||p.getData() ==null)
            throw new NullPointerException("null address || null buffer");

        socketSend(nativefd, p.getData(), p.getOffset(), p.getLength(),
                   p.getAddress(), p.getPort(), connected);
    }

    protected void connect0(java.net.InetAddress address, int port) throws java.net.SocketException {
        int nativefd = checkAndReturnNativeFD();

        if (address == null)
            throw new NullPointerException("address");

        socketConnect(nativefd, address, port);
    }

    protected void disconnect0(int family /*unused*/) {
        if (fd == null || !fd.valid())
            return;   // disconnect doesn't throw any exceptions

        socketDisconnect(fdAccess.get(fd));
    }

    protected void datagramSocketClose() {
        if (fd == null || !fd.valid())
            return;   // close doesn't throw any exceptions

        socketClose(fdAccess.get(fd));
        fdAccess.set(fd, -1);
    }

    @SuppressWarnings("fallthrough")
    protected void socketSetOption(int opt, Object val) throws java.net.SocketException {
        int nativefd = checkAndReturnNativeFD();

        int optionValue = 0;

        switch(opt) {
            case IP_TOS :
            case SO_RCVBUF :
            case SO_SNDBUF :
                optionValue = ((Integer)val).intValue();
                break;
            case SO_REUSEADDR :
                if (exclusiveBind && localPort != 0)  {
                    // socket already bound, emulate SO_REUSEADDR
                    reuseAddressEmulated = true;
                    isReuseAddress = (Boolean)val;
                    return;
                }
                //Intentional fallthrough
            case SO_BROADCAST :
                optionValue = ((Boolean)val).booleanValue() ? 1 : 0;
                break;
            default: /* shouldn't get here */
                throw new java.net.SocketException("Option not supported");
        }

        socketSetIntOption(nativefd, opt, optionValue);
    }

    protected Object socketGetOption(int opt) throws java.net.SocketException {
        int nativefd = checkAndReturnNativeFD();

         // SO_BINDADDR is not a socket option.
        if (opt == SO_BINDADDR) {
            return socketLocalAddress(nativefd);
        }
        if (opt == SO_REUSEADDR && reuseAddressEmulated)
            return isReuseAddress;

        int value = socketGetIntOption(nativefd, opt);
        Object returnValue = null;

        switch (opt) {
            case SO_REUSEADDR :
            case SO_BROADCAST :
                returnValue =  (value == 0) ? Boolean.FALSE : Boolean.TRUE;
                break;
            case IP_TOS :
            case SO_RCVBUF :
            case SO_SNDBUF :
                returnValue = new Integer(value);
                break;
            default: /* shouldn't get here */
                throw new java.net.SocketException("Option not supported");
        }

        return returnValue;
    }

    /* Multicast specific methods.
     * Multicasting on a dual layer TCP/IP stack is always done with
     * TwoStacksPlainDatagramSocketImpl. This is to overcome the lack
     * of behavior defined for multicasting over a dual layer socket by the RFC.
     */
    protected void join(java.net.InetAddress inetaddr, java.net.NetworkInterface netIf)
        throws IOException {
        throw new IOException("Method not implemented!");
    }

    protected void leave(java.net.InetAddress inetaddr, NetworkInterface netIf)
        throws IOException {
        throw new IOException("Method not implemented!");
    }

    protected void setTimeToLive(int ttl) throws IOException {
        throw new IOException("Method not implemented!");
    }

    protected int getTimeToLive() throws IOException {
        throw new IOException("Method not implemented!");
    }

    @Deprecated
    protected void setTTL(byte ttl) throws IOException {
        throw new IOException("Method not implemented!");
    }

    @Deprecated
    protected byte getTTL() throws IOException {
        throw new IOException("Method not implemented!");
    }
    /* END Multicast specific methods */

    private int checkAndReturnNativeFD() throws java.net.SocketException {
        if (fd == null || !fd.valid())
            throw new java.net.SocketException("Socket closed");

        return fdAccess.get(fd);
    }

    /* Native methods */

    private static native void initIDs();

    private static native int socketCreate(boolean v6Only);

    private static native void socketBind(int fd, java.net.InetAddress localAddress,
            int localport, boolean exclBind) throws java.net.SocketException;

    private static native void socketConnect(int fd, java.net.InetAddress address, int port)
        throws java.net.SocketException;

    private static native void socketDisconnect(int fd);

    private static native void socketClose(int fd);

    private static native int socketLocalPort(int fd) throws java.net.SocketException;

    private static native Object socketLocalAddress(int fd) throws java.net.SocketException;

    private static native int socketReceiveOrPeekData(int fd, DatagramPacket packet,
        int timeout, boolean connected, boolean peek) throws IOException;

    private static native void socketSend(int fd, byte[] data, int offset, int length,
                                          InetAddress address, int port, boolean connected) throws IOException;

    private static native void socketSetIntOption(int fd, int cmd,
        int optionValue) throws java.net.SocketException;

    private static native int socketGetIntOption(int fd, int cmd) throws SocketException;

    native int dataAvailable();
}
