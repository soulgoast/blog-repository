package goodman.com.sun.corba.se.PortableActivationIDL;


/**
* com/sun/corba/se/PortableActivationIDL/RepositoryHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/re/workspace/8-2-build-windows-amd64-cygwin/jdk8u201/12322/corba/src/share/classes/com/sun/corba/se/PortableActivationIDL/activation.idl
* Saturday, December 15, 2018 6:38:37 PM PST
*/

abstract public class RepositoryHelper
{
  private static String  _id = "IDL:PortableActivationIDL/Repository:1.0";

  public static void insert (org.omg.CORBA.Any a, com.sun.corba.se.PortableActivationIDL.Repository that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static com.sun.corba.se.PortableActivationIDL.Repository extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (com.sun.corba.se.PortableActivationIDL.RepositoryHelper.id (), "Repository");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static com.sun.corba.se.PortableActivationIDL.Repository read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_RepositoryStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, com.sun.corba.se.PortableActivationIDL.Repository value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static com.sun.corba.se.PortableActivationIDL.Repository narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.sun.corba.se.PortableActivationIDL.Repository)
      return (com.sun.corba.se.PortableActivationIDL.Repository)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.sun.corba.se.PortableActivationIDL._RepositoryStub stub = new com.sun.corba.se.PortableActivationIDL._RepositoryStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static com.sun.corba.se.PortableActivationIDL.Repository unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof com.sun.corba.se.PortableActivationIDL.Repository)
      return (com.sun.corba.se.PortableActivationIDL.Repository)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      com.sun.corba.se.PortableActivationIDL._RepositoryStub stub = new com.sun.corba.se.PortableActivationIDL._RepositoryStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
