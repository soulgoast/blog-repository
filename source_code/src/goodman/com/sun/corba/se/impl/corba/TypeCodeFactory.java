/*
 * Copyright (c) 1999, 2003, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.impl.corba;

import com.sun.corba.se.impl.corba.TypeCodeImpl;

public interface TypeCodeFactory {
    void setTypeCode(String id, com.sun.corba.se.impl.corba.TypeCodeImpl code);

    com.sun.corba.se.impl.corba.TypeCodeImpl getTypeCode(String id);

    void setTypeCodeForClass(Class c, com.sun.corba.se.impl.corba.TypeCodeImpl tcimpl) ;

    TypeCodeImpl getTypeCodeForClass(Class c) ;
}
