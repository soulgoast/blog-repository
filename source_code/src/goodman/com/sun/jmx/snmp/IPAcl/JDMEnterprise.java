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


/* Generated By:JJTree: Do not edit this line. JDMEnterprise.java */

package goodman.com.sun.jmx.snmp.IPAcl;

import com.sun.jmx.snmp.IPAcl.Node;
import com.sun.jmx.snmp.IPAcl.Parser;
import com.sun.jmx.snmp.IPAcl.SimpleNode;

class JDMEnterprise extends com.sun.jmx.snmp.IPAcl.SimpleNode {
  protected String enterprise= "";

  JDMEnterprise(int id) {
    super(id);
  }

  JDMEnterprise(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
    super(p, id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(int id) {
      return new JDMEnterprise(id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
      return new JDMEnterprise(p, id);
  }
}
