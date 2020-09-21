/*
 * Copyright (c) 1999, 2001, Oracle and/or its affiliates. All rights reserved.
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
/*
 * Licensed Materials - Property of IBM
 * RMI-IIOP v1.0
 * Copyright IBM Corp. 1998 1999  All Rights Reserved
 *
 */

package goodman.javax.rmi.CORBA;

import goodman.java.io.Serializable;
import goodman.java.rmi.Remote;
import goodman.java.rmi.RemoteException;
import goodman.javax.rmi.CORBA.Stub;
import goodman.javax.rmi.CORBA.Tie;
import goodman.javax.rmi.CORBA.Util;
import goodman.javax.rmi.CORBA.ValueHandler;
import org.omg.CORBA.ORB;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.SystemException;

/**
 * Supports delegation for method implementations in {@link javax.rmi.CORBA.Util}.  The
 * delegate is a singleton instance of a class that implements this
 * interface and provides a replacement implementation for all the
 * methods of <code>javax.rmi.CORBA.Util</code>.
 *
 * Delegation is enabled by providing the delegate's class name as the
 * value of the
 * <code>javax.rmi.CORBA.UtilClass</code>
 * system property.
 *
 * @see javax.rmi.CORBA.Util
 */
public interface UtilDelegate {

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#mapSystemException}.
     */
    RemoteException mapSystemException(SystemException ex);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#writeAny}.
     */
    void writeAny(OutputStream out, Object obj);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#readAny}.
     */
    Object readAny(InputStream in);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#writeRemoteObject}.
     */
    void writeRemoteObject(OutputStream out, Object obj);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#writeAbstractObject}.
     */
    void writeAbstractObject(OutputStream out, Object obj);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#registerTarget}.
     */
    void registerTarget(Tie tie, Remote target);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#unexportObject}.
     */
    void unexportObject(Remote target) throws java.rmi.NoSuchObjectException;

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#getTie}.
     */
    Tie getTie(Remote target);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#createValueHandler}.
     */
    ValueHandler createValueHandler();

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#getCodebase}.
     */
    String getCodebase(Class clz);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#loadClass}.
     */
    Class loadClass(String className, String remoteCodebase, ClassLoader loader)
        throws ClassNotFoundException;

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#isLocal}.
     */
    boolean isLocal(Stub stub) throws RemoteException;

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#wrapException}.
     */
    RemoteException wrapException(Throwable obj);

    /**
     * Delegation call for {@link javax.rmi.CORBA.Util#copyObject}.
     */
    Object copyObject(Object obj, ORB orb) throws RemoteException;

    /**
     * Delegation call for {@link Util#copyObjects}.
     */
    Object[] copyObjects(Object[] obj, ORB orb) throws RemoteException;

}
