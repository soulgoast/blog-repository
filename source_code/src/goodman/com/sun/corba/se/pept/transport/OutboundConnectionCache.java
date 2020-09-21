/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.pept.transport;

import com.sun.corba.se.pept.transport.Connection;
import com.sun.corba.se.pept.transport.ConnectionCache;
import com.sun.corba.se.pept.transport.ContactInfo;

/**
 * @author Harold Carr
 */
public interface OutboundConnectionCache
    extends ConnectionCache
{
    public com.sun.corba.se.pept.transport.Connection get(com.sun.corba.se.pept.transport.ContactInfo contactInfo);

    public void put(com.sun.corba.se.pept.transport.ContactInfo contactInfo, Connection connection);

    public void remove(ContactInfo contactInfo);
}

// End of file.
