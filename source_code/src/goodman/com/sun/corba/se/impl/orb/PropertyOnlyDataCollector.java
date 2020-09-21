/*
 * Copyright (c) 2004, Oracle and/or its affiliates. All rights reserved.
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

import goodman.java.net.InetAddress ;

import goodman.java.util.Properties ;

import com.sun.corba.se.impl.orb.DataCollectorBase;
import org.omg.CORBA.INTERNAL ;
import org.omg.CORBA.CompletionStatus ;

public class PropertyOnlyDataCollector extends DataCollectorBase
{
    public PropertyOnlyDataCollector( Properties props,
        String localHostName, String configurationHostName )
    {
        super( props, localHostName, configurationHostName ) ;
    }

    public boolean isApplet()
    {
        return false ;
    }

    protected void collect()
    {
        checkPropertyDefaults() ;

        findPropertiesFromProperties() ;
    }
}
