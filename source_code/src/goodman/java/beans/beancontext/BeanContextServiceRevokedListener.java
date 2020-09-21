/*
 * Copyright (c) 1998, 2013, Oracle and/or its affiliates. All rights reserved.
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
package goodman.java.beans.beancontext;

import goodman.java.beans.beancontext.BeanContextServiceRevokedEvent;

import goodman.java.util.EventListener;

/**
 *  The listener interface for receiving
 * <code>BeanContextServiceRevokedEvent</code> objects. A class that is
 * interested in processing a <code>BeanContextServiceRevokedEvent</code>
 * implements this interface.
 */
public interface BeanContextServiceRevokedListener extends EventListener {

    /**
     * The service named has been revoked. getService requests for
     * this service will no longer be satisfied.
     * @param bcsre the <code>BeanContextServiceRevokedEvent</code> received
     * by this listener.
     */
    void serviceRevoked(BeanContextServiceRevokedEvent bcsre);
}
