/*
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
 * Copyright (c) 2009 by Oracle Corporation. All Rights Reserved.
 */

/*
 * $Id: PropertyState.java 3024 2011-03-01 03:46:13Z joehw $
 */
package goodman.com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.util.Status;

public class PropertyState {

    public final com.sun.org.apache.xerces.internal.util.Status status;
    public final Object state;

    public static final PropertyState UNKNOWN = new PropertyState(com.sun.org.apache.xerces.internal.util.Status.UNKNOWN, null);
    public static final PropertyState RECOGNIZED = new PropertyState(com.sun.org.apache.xerces.internal.util.Status.RECOGNIZED, null);
    public static final PropertyState NOT_SUPPORTED = new PropertyState(com.sun.org.apache.xerces.internal.util.Status.NOT_SUPPORTED, null);
    public static final PropertyState NOT_RECOGNIZED = new PropertyState(com.sun.org.apache.xerces.internal.util.Status.NOT_RECOGNIZED, null);
    public static final PropertyState NOT_ALLOWED = new PropertyState(com.sun.org.apache.xerces.internal.util.Status.NOT_ALLOWED, null);


    public PropertyState(com.sun.org.apache.xerces.internal.util.Status status, Object state) {
        this.status = status;
        this.state = state;
    }

    public static PropertyState of(com.sun.org.apache.xerces.internal.util.Status status) {
        return new PropertyState(status, null);
    }

    public static PropertyState is(Object value) {
        return new PropertyState(Status.SET, value);
    }

    public boolean isExceptional() {
        return this.status.isExceptional();
    }
}
