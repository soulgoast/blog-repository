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

package goodman.com.sun.jmx.remote.protocol.rmi;

import goodman.java.io.IOException;
import goodman.java.net.MalformedURLException;
import goodman.java.util.Map;

import goodman.javax.management.MBeanServer;
import goodman.javax.management.remote.JMXConnectorServer;
import goodman.javax.management.remote.JMXConnectorServerProvider;
import goodman.javax.management.remote.JMXServiceURL;
import goodman.javax.management.remote.rmi.RMIConnectorServer;

public class ServerProvider implements JMXConnectorServerProvider {

    public JMXConnectorServer newJMXConnectorServer(JMXServiceURL serviceURL,
                                                    Map<String,?> environment,
                                                    MBeanServer mbeanServer)
            throws IOException {
        if (!serviceURL.getProtocol().equals("rmi")) {
            throw new MalformedURLException("Protocol not rmi: " +
                                            serviceURL.getProtocol());
        }
        return new RMIConnectorServer(serviceURL, environment, mbeanServer);
    }

}
