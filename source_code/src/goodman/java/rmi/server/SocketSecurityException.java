/*
 * Copyright (c) 1996, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.rmi.server;

import goodman.java.rmi.server.ExportException;

/**
 * An obsolete subclass of {@link java.rmi.server.ExportException}.
 *
 * @author  Ann Wollrath
 * @since   JDK1.1
 * @deprecated This class is obsolete. Use {@link java.rmi.server.ExportException} instead.
 */
@Deprecated
public class SocketSecurityException extends ExportException {

    /* indicate compatibility with JDK 1.1.x version of class */
    private static final long serialVersionUID = -7622072999407781979L;

    /**
     * Constructs an <code>SocketSecurityException</code> with the specified
     * detail message.
     *
     * @param s the detail message.
     * @since JDK1.1
     */
    public SocketSecurityException(String s) {
        super(s);
    }

    /**
     * Constructs an <code>SocketSecurityException</code> with the specified
     * detail message and nested exception.
     *
     * @param s the detail message.
     * @param ex the nested exception
     * @since JDK1.1
     */
    public SocketSecurityException(String s, Exception ex) {
        super(s, ex);
    }

}
