package goodman.org.omg.PortableServer.POAPackage;


import org.omg.PortableServer.POAPackage.ServantNotActiveHelper;

/**
* org/omg/PortableServer/POAPackage/ServantNotActive.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/re/workspace/8-2-build-windows-amd64-cygwin/jdk8u201/12322/corba/src/share/classes/org/omg/PortableServer/poa.idl
* Saturday, December 15, 2018 6:38:40 PM PST
*/

public final class ServantNotActive extends org.omg.CORBA.UserException
{

  public ServantNotActive ()
  {
    super(ServantNotActiveHelper.id());
  } // ctor


  public ServantNotActive (String $reason)
  {
    super(ServantNotActiveHelper.id() + "  " + $reason);
  } // ctor

} // class ServantNotActive
