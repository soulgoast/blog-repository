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

import com.sun.corba.se.pept.transport.Acceptor;
import com.sun.corba.se.pept.transport.Connection;
import com.sun.corba.se.pept.transport.ConnectionCache;

/**
 * @author Harold Carr
 */
public interface InboundConnectionCache
    extends ConnectionCache
{
    public com.sun.corba.se.pept.transport.Connection get(com.sun.corba.se.pept.transport.Acceptor acceptor); // REVISIT

    public void put(com.sun.corba.se.pept.transport.Acceptor acceptor, com.sun.corba.se.pept.transport.Connection connection);

    public void remove(Connection connection);

    public Acceptor getAcceptor();
}

// End of file.
