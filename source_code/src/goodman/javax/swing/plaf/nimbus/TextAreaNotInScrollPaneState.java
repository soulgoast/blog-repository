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


class TextAreaNotInScrollPaneState extends State {
    TextAreaNotInScrollPaneState() {
        super("NotInScrollPane");
    }

    @Override protected boolean isInState(JComponent c) {

                          return !(c.getParent() instanceof JViewport);
    }
}

