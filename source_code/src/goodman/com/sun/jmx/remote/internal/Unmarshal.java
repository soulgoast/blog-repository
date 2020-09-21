/*
 * Copyright (c) 2003, 2008, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.jmx.remote.internal;

import goodman.java.io.IOException;
import goodman.java.rmi.MarshalledObject;

public interface Unmarshal {
    public Object get(MarshalledObject<?> mo)
            throws IOException, ClassNotFoundException;
}
