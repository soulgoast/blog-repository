package goodman.org.omg.CosNaming.NamingContextPackage;


import org.omg.CosNaming.NamingContextPackage.AlreadyBoundHelper;

/**
* org/omg/CosNaming/NamingContextPackage/AlreadyBound.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/re/workspace/8-2-build-windows-amd64-cygwin/jdk8u201/12322/corba/src/share/classes/org/omg/CosNaming/nameservice.idl
* Saturday, December 15, 2018 6:38:37 PM PST
*/

public final class AlreadyBound extends org.omg.CORBA.UserException
{

  public AlreadyBound ()
  {
    super(AlreadyBoundHelper.id());
  } // ctor


  public AlreadyBound (String $reason)
  {
    super(AlreadyBoundHelper.id() + "  " + $reason);
  } // ctor

} // class AlreadyBound
