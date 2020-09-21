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

package goodman.com.sun.corba.se.impl.orb;

import goodman.java.applet.Applet ;
import goodman.java.util.Properties ;
import goodman.java.net.URL ;

import com.sun.corba.se.impl.orb.AppletDataCollector;
import com.sun.corba.se.impl.orb.NormalDataCollector;
import com.sun.corba.se.impl.orb.PropertyOnlyDataCollector;
import com.sun.corba.se.spi.orb.DataCollector ;

public abstract class DataCollectorFactory {
    private DataCollectorFactory() {}

    public static DataCollector create( Applet app, Properties props,
        String localHostName )
    {
        String appletHost = localHostName ;

        if (app != null) {
            URL appletCodeBase = app.getCodeBase() ;

            if (appletCodeBase != null)
                appletHost = appletCodeBase.getHost() ;
        }

        return new AppletDataCollector( app, props, localHostName,
            appletHost ) ;
    }

    public static DataCollector create( String[] args, Properties props,
        String localHostName )
    {
        return new NormalDataCollector( args, props, localHostName,
            localHostName ) ;
    }

    public static DataCollector create( Properties props,
        String localHostName )
    {
        return new PropertyOnlyDataCollector( props, localHostName,
            localHostName ) ;
    }
}
