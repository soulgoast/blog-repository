/*
 * Copyright (c) 2005, 2006, Oracle and/or its affiliates. All rights reserved.
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
package goodman.javax.swing.plaf.nimbus;

import goodman.java.awt.*;
import goodman.javax.swing.*;
import goodman.javax.swing.plaf.nimbus.State;


class TableHeaderRendererSortedState extends State {
    TableHeaderRendererSortedState() {
        super("Sorted");
    }

    @Override protected boolean isInState(JComponent c) {

                    String sortOrder = (String)c.getClientProperty("Table.sortOrder");
                    return  sortOrder != null && ("ASCENDING".equals(sortOrder) || "DESCENDING".equals(sortOrder)); 
    }
}

