/*
 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.spi.orbutil.proxy;

import goodman.java.io.Serializable ;

import goodman.java.util.Map ;
import goodman.java.util.LinkedHashMap ;

import goodman.java.lang.reflect.Proxy ;
import goodman.java.lang.reflect.Method ;
import goodman.java.lang.reflect.InvocationHandler ;

import com.sun.corba.se.spi.logging.CORBALogDomains ;
import com.sun.corba.se.impl.logging.ORBUtilSystemException ;
import com.sun.corba.se.impl.presentation.rmi.DynamicAccessPermission;
import com.sun.corba.se.spi.orbutil.proxy.CompositeInvocationHandler;

public class CompositeInvocationHandlerImpl implements
        CompositeInvocationHandler
{
    private Map classToInvocationHandler = new LinkedHashMap() ;
    private InvocationHandler defaultHandler = null ;

    public void addInvocationHandler( Class interf,
        InvocationHandler handler )
    {
        checkAccess();
        classToInvocationHandler.put( interf, handler ) ;
    }

    public void setDefaultHandler( InvocationHandler handler )
    {
        checkAccess();
        defaultHandler = handler ;
    }

    public Object invoke( Object proxy, Method method, Object[] args )
        throws Throwable
    {
        // Note that the declaring class in method is the interface
        // in which the method was defined, not the proxy class.
        Class cls = method.getDeclaringClass() ;
        InvocationHandler handler =
            (InvocationHandler)classToInvocationHandler.get( cls ) ;

        if (handler == null) {
            if (defaultHandler != null)
                handler = defaultHandler ;
            else {
                ORBUtilSystemException wrapper = ORBUtilSystemException.get(
                    CORBALogDomains.UTIL ) ;
                throw wrapper.noInvocationHandler( "\"" + method.toString() +
                    "\"" ) ;
            }
        }

        // handler should never be null here.

        return handler.invoke( proxy, method, args ) ;
    }

    private static final DynamicAccessPermission perm = new DynamicAccessPermission("access");
    private void checkAccess() {
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(perm);
}
    }

    private static final long serialVersionUID = 4571178305984833743L;
}
