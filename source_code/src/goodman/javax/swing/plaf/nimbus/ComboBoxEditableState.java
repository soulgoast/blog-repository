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


class ComboBoxEditableState extends State {
    ComboBoxEditableState() {
        super("Editable");
    }

    @Override protected boolean isInState(JComponent c) {

        return c instanceof JComboBox && ((JComboBox)c).isEditable();
                
    }
}

