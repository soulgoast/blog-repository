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


/* Generated By:JJTree: Do not edit this line. JDMAccess.java */

package goodman.com.sun.jmx.snmp.IPAcl;


import com.sun.jmx.snmp.IPAcl.*;
import com.sun.jmx.snmp.IPAcl.Node;
import com.sun.jmx.snmp.IPAcl.Parser;

class JDMAccess extends SimpleNode {
  protected int access= -1;

  JDMAccess(int id) {
    super(id);
  }

  JDMAccess(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
    super(p, id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(int id) {
      return new JDMAccess(id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
      return new JDMAccess(p, id);
  }

  protected void putPermission(AclEntryImpl entry) {
    if (access == ParserConstants.RO) {
       // We have a read-only access.
       //
       entry.addPermission(com.sun.jmx.snmp.IPAcl.SnmpAcl.getREAD());
    }
    if (access == ParserConstants.RW) {
       // We have a read-write access.
       //
       entry.addPermission(com.sun.jmx.snmp.IPAcl.SnmpAcl.getREAD());
       entry.addPermission(com.sun.jmx.snmp.IPAcl.SnmpAcl.getWRITE());
    }
  }
}
