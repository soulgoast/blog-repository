/*
 * Copyright (c) 1997, 2007, Oracle and/or its affiliates. All rights reserved.
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


/* Generated By:JJTree: Do not edit this line. JDMIpMask.java */

package goodman.com.sun.jmx.snmp.IPAcl;

import com.sun.jmx.snmp.IPAcl.GroupImpl;
import com.sun.jmx.snmp.IPAcl.Host;
import com.sun.jmx.snmp.IPAcl.Node;
import com.sun.jmx.snmp.IPAcl.Parser;
import com.sun.jmx.snmp.IPAcl.PrincipalImpl;

import goodman.java.lang.StringBuffer;
import goodman.java.net.UnknownHostException;

class JDMIpMask extends com.sun.jmx.snmp.IPAcl.Host {
  private static final long serialVersionUID = -8211312690652331386L;

  protected StringBuffer address= new StringBuffer();

  JDMIpMask(int id) {
    super(id);
  }

  JDMIpMask(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
    super(p, id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(int id) {
      return new JDMIpMask(id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
      return new JDMIpMask(p, id);
  }

  protected String getHname() {
        return address.toString();
  }

  protected PrincipalImpl createAssociatedPrincipal()
    throws UnknownHostException {
      return new com.sun.jmx.snmp.IPAcl.GroupImpl(address.toString());
  }
}