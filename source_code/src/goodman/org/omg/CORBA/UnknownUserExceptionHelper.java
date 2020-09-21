/*
 * Copyright (c) 2000, 2001, Oracle and/or its affiliates. All rights reserved.
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
* The Helper for <tt>UnknownUserException</tt>.  For more information on
* Helper files, see <a href="doc-files/generatedfiles.html#helper">
* "Generated Files: Helper Files"</a>.<P>
* org/omg/CORBA/UnknownUserExceptionHelper.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from CORBA.idl
* Thursday, August 24, 2000 5:52:22 PM PDT
*/

abstract public class UnknownUserExceptionHelper
{
  private static String  _id = "IDL:omg.org/CORBA/UnknownUserException:1.0";

  public static void insert (org.omg.CORBA.Any a, org.omg.CORBA.UnknownUserException that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static org.omg.CORBA.UnknownUserException extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [1];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_any);
          _members0[0] = new org.omg.CORBA.StructMember (
            "except",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_exception_tc (org.omg.CORBA.UnknownUserExceptionHelper.id (), "UnknownUserException", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static org.omg.CORBA.UnknownUserException read (org.omg.CORBA.portable.InputStream istream)
  {
    org.omg.CORBA.UnknownUserException value = new org.omg.CORBA.UnknownUserException ();
    // read and discard the repository ID
    istream.read_string ();
    value.except = istream.read_any ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, org.omg.CORBA.UnknownUserException value)
  {
    // write the repository ID
    ostream.write_string (id ());
    ostream.write_any (value.except);
  }

}
