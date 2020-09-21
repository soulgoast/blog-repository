/*
 * Copyright (c) 2002, 2006, Oracle and/or its affiliates. All rights reserved.
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

/* Generated By:JJTree: Do not edit this line. JDMNetMaskV6.java */

package goodman.com.sun.jmx.snmp.IPAcl;

import com.sun.jmx.snmp.IPAcl.JDMNetMask;
import com.sun.jmx.snmp.IPAcl.NetMaskImpl;
import com.sun.jmx.snmp.IPAcl.Parser;
import com.sun.jmx.snmp.IPAcl.PrincipalImpl;

import goodman.java.net.UnknownHostException;

class JDMNetMaskV6 extends com.sun.jmx.snmp.IPAcl.JDMNetMask {
  private static final long serialVersionUID = 4505256777680576645L;

  public JDMNetMaskV6(int id) {
    super(id);
  }

  public JDMNetMaskV6(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
    super(p, id);
  }
    protected PrincipalImpl createAssociatedPrincipal()
    throws UnknownHostException {
      return new com.sun.jmx.snmp.IPAcl.NetMaskImpl(address.toString(), Integer.parseInt(mask));
  }
}
