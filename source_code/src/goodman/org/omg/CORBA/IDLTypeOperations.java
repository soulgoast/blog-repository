/*
 * Copyright (c) 1999, 2001, Oracle and/or its affiliates. All rights reserved.
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
package goodman.org.omg.CORBA;

/**
* The interface for <tt>IDLType</tt>.  For more information on
* Operations interfaces, see <a href="doc-files/generatedfiles.html#operations">
* "Generated Files: Operations files"</a>.
*/

/*
 tempout/org/omg/CORBA/IDLTypeOperations.java
 Generated by the IBM IDL-to-Java compiler, version 1.0
 from ../../Lib/ir.idl
 Thursday, February 25, 1999 2:11:23 o'clock PM PST
*/

import org.omg.CORBA.IDLType;
import org.omg.CORBA.IRObject;
import org.omg.CORBA.IRObjectOperations;

/**
 * This interface must be implemented by all IDLType objects.
 * The IDLType is inherited by all IR objects that
 * represent IDL types, including interfaces, typedefs, and
 * anonymous types.
 * @see IDLType
 * @see IRObject
 * @see IRObjectOperations
 */

public interface IDLTypeOperations  extends IRObjectOperations
{
    /**
     * The type attribute describes the type defined by an object
     * derived from <code>IDLType</code>.
     * @return the <code>TypeCode</code> defined by this object.
     */
    org.omg.CORBA.TypeCode type();
} // interface IDLTypeOperations
