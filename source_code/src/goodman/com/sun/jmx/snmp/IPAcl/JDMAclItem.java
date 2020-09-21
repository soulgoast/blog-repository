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


/* Generated By:JJTree: Do not edit this line. JDMAclItem.java */

package goodman.com.sun.jmx.snmp.IPAcl;

import com.sun.jmx.snmp.IPAcl.JDMAccess;
import com.sun.jmx.snmp.IPAcl.JDMCommunities;
import com.sun.jmx.snmp.IPAcl.Node;
import com.sun.jmx.snmp.IPAcl.Parser;
import com.sun.jmx.snmp.IPAcl.SimpleNode;

class JDMAclItem extends SimpleNode {
  protected com.sun.jmx.snmp.IPAcl.JDMAccess access= null;
  protected JDMCommunities com= null;

  JDMAclItem(int id) {
    super(id);
  }

  JDMAclItem(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
    super(p, id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(int id) {
      return new JDMAclItem(id);
  }

  public static com.sun.jmx.snmp.IPAcl.Node jjtCreate(com.sun.jmx.snmp.IPAcl.Parser p, int id) {
      return new JDMAclItem(p, id);
  }

  public com.sun.jmx.snmp.IPAcl.JDMAccess getAccess() {
    return access;
  }

  public JDMCommunities getCommunities() {
    return com;
  }
}