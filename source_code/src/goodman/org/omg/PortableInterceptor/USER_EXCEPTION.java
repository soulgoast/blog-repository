package goodman.org.omg.PortableInterceptor;


import org.omg.PortableInterceptor.*;
import org.omg.PortableInterceptor.RequestInfo;
import org.omg.PortableInterceptor.SUCCESSFUL;

/**
* org/omg/PortableInterceptor/USER_EXCEPTION.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from c:/re/workspace/8-2-build-windows-amd64-cygwin/jdk8u201/12322/corba/src/share/classes/org/omg/PortableInterceptor/Interceptors.idl
* Saturday, December 15, 2018 6:38:38 PM PST
*/

public interface USER_EXCEPTION
{

  /**
   * Indicates a UserException reply status. One possible value for 
   * <code>RequestInfo.reply_status</code>.
   * @see RequestInfo#reply_status
   * @see SUCCESSFUL
   * @see SYSTEM_EXCEPTION
   * @see LOCATION_FORWARD
   * @see TRANSPORT_RETRY
   */
  public static final short value = (short)(2);
}
