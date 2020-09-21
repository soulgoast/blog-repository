/*
 * Copyright (c) 2002, 2004, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.jmx.remote.protocol.rmi;

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
        if (!serviceURL.getProtocol().equals("rmi")) {
            throw new MalformedURLException("Protocol not rmi: " +
                                            serviceURL.getProtocol());
        }
        return new RMIConnector(serviceURL, environment);
    }
}
