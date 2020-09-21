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

package goodman.com.sun.corba.se.spi.orbutil.closure;

import com.sun.corba.se.impl.orbutil.closure.Constant ;
import com.sun.corba.se.impl.orbutil.closure.Future ;
import com.sun.corba.se.spi.orbutil.closure.Closure;

public abstract class ClosureFactory {
    private ClosureFactory() {}

    public static com.sun.corba.se.spi.orbutil.closure.Closure makeConstant(Object value )
    {
        return new Constant( value ) ;
    }

    public static com.sun.corba.se.spi.orbutil.closure.Closure makeFuture(Closure value )
    {
        return new Future( value ) ;
    }
}
