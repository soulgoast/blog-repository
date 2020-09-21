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
 * $Id: FeatureState.java 3024 2011-03-01 03:46:13Z joehw $
 */

package goodman.com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.util.Status;

public class FeatureState {

    public final com.sun.org.apache.xerces.internal.util.Status status;
    public final boolean state;

    public static final FeatureState SET_ENABLED = new FeatureState(com.sun.org.apache.xerces.internal.util.Status.SET, true);
    public static final FeatureState SET_DISABLED = new FeatureState(com.sun.org.apache.xerces.internal.util.Status.SET, false);
    public static final FeatureState UNKNOWN = new FeatureState(com.sun.org.apache.xerces.internal.util.Status.UNKNOWN, false);
    public static final FeatureState RECOGNIZED = new FeatureState(com.sun.org.apache.xerces.internal.util.Status.RECOGNIZED, false);
    public static final FeatureState NOT_SUPPORTED = new FeatureState(com.sun.org.apache.xerces.internal.util.Status.NOT_SUPPORTED, false);
    public static final FeatureState NOT_RECOGNIZED = new FeatureState(com.sun.org.apache.xerces.internal.util.Status.NOT_RECOGNIZED, false);
    public static final FeatureState NOT_ALLOWED = new FeatureState(com.sun.org.apache.xerces.internal.util.Status.NOT_ALLOWED, false);

    public FeatureState(com.sun.org.apache.xerces.internal.util.Status status, boolean state) {
        this.status = status;
        this.state = state;
    }

    public static FeatureState of(com.sun.org.apache.xerces.internal.util.Status status) {
        return new FeatureState(status, false);
    }

    public static FeatureState is(boolean value) {
        return new FeatureState(Status.SET, value);
    }

    public boolean isExceptional() {
        return this.status.isExceptional();
    }
}
