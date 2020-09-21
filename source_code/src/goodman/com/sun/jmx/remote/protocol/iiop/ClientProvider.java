/*
 * Copyright (c) 2003, 2004, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.jmx.remote.protocol.iiop;

import goodman.java.io.IOException;
import goodman.java.net.MalformedURLException;
import goodman.java.util.Map;

import goodman.javax.management.remote.JMXConnectorProvider;
import goodman.javax.management.remote.JMXConnector;
import goodman.javax.management.remote.JMXServiceURL;
import goodman.javax.management.remote.rmi.RMIConnector;

public class ClientProvider implements JMXConnectorProvider {

    public JMXConnector newJMXConnector(JMXServiceURL serviceURL,
                                        Map<String,?> environment)
            throws IOException {
        if (!serviceURL.getProtocol().equals("iiop")) {
            throw new MalformedURLException("Protocol not iiop: " +
                                            serviceURL.getProtocol());
        }
        return new RMIConnector(serviceURL, environment);
    }
}
