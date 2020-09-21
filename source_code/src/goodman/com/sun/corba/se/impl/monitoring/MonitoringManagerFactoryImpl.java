/*
 * Copyright (c) 2003, 2012, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.impl.monitoring;

import goodman.java.util.HashMap;

import com.sun.corba.se.impl.monitoring.MonitoringManagerImpl;
import com.sun.corba.se.spi.monitoring.MonitoringManagerFactory;
import com.sun.corba.se.spi.monitoring.MonitoringManager;

public class MonitoringManagerFactoryImpl implements MonitoringManagerFactory {

    private HashMap monitoringManagerTable = new HashMap();

    public synchronized MonitoringManager createMonitoringManager(
        String nameOfTheRoot, String description)
    {
        com.sun.corba.se.impl.monitoring.MonitoringManagerImpl m = null;
        m = (com.sun.corba.se.impl.monitoring.MonitoringManagerImpl)monitoringManagerTable.get(nameOfTheRoot);
        if (m == null) {
            m = new MonitoringManagerImpl(nameOfTheRoot, description);
            monitoringManagerTable.put(nameOfTheRoot, m);
        }
        return m;
    }

    public synchronized void remove(String nameOfTheRoot) {
        monitoringManagerTable.remove(nameOfTheRoot);
    }
}
