/*
 * Copyright (c) 2000, 2012, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.impl.orbutil;

import com.sun.corba.se.impl.orbutil.RepIdDelegator;
import com.sun.corba.se.impl.orbutil.RepositoryIdStrings;
import com.sun.corba.se.impl.orbutil.RepositoryIdUtility;
import com.sun.corba.se.spi.orb.ORBVersion;
import com.sun.corba.se.spi.orb.ORB;

public abstract class RepositoryIdFactory
{
    private static final com.sun.corba.se.impl.orbutil.RepIdDelegator currentDelegator
        = new RepIdDelegator();

    /**
     * Returns the latest version RepositoryIdStrings instance
     */
    public static RepositoryIdStrings getRepIdStringsFactory()
    {
        return currentDelegator;
    }

    /**
     * Returns the latest version RepositoryIdUtility instance
     */
    public static RepositoryIdUtility getRepIdUtility()
    {
        return currentDelegator;
    }

}
