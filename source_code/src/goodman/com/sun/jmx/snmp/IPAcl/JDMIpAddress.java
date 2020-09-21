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


/* Generated By:JJTree: Do not edit this line. JDMIpAddress.java */

package goodman.com.sun.jmx.snmp.IPAcl;

import com.sun.jmx.snmp.IPAcl.Host;
import com.sun.jmx.snmp.IPAcl.Node;
import com.sun.jmx.snmp.IPAcl.Parser;
import com.sun.jmx.snmp.IPAcl.PrincipalImpl;

import goodman.java.lang.StringBuffer;
import goodman.java.net.UnknownHostException;

class JDMIpAddress extends com.sun.jmx.snmp.IPAcl.Host {
  private static final long serialVersionUID = 849729919486384484L;

  protected StringBuffer address= new StringBuffer();

  JDMIpAddress(int id) {
    super(id);
  }

  JDMIpAddress(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
    super(p, id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(int id) {
      return new JDMIpAddress(id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
      return new JDMIpAddress(p, id);
  }

  protected String getHname() {
          return address.toString();
  }

  protected PrincipalImpl createAssociatedPrincipal()
    throws UnknownHostException {
      return new PrincipalImpl(address.toString());
  }
}
