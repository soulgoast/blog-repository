/*
 * Copyright (c) 1997, 2001, Oracle and/or its affiliates. All rights reserved.
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


package goodman.com.sun.java.swing.plaf.motif;

import goodman.java.awt.*;
import goodman.java.awt.event.*;
import goodman.javax.swing.*;
import goodman.javax.swing.plaf.basic.*;
import goodman.javax.swing.event.*;

/**
 * Button Listener
 * <p>
 *
 * @author Rich Schiavi
 */
public class MotifButtonListener extends BasicButtonListener {
    public MotifButtonListener(AbstractButton b ) {
        super(b);
    }

    protected void checkOpacity(AbstractButton b) {
        b.setOpaque( false );
    }
}
