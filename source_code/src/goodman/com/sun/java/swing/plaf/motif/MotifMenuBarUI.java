/*
 * Copyright (c) 1997, 1998, Oracle and/or its affiliates. All rights reserved.
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

import goodman.java.awt.Component;
import goodman.java.awt.Container;
import goodman.java.awt.Dimension;
import goodman.java.awt.Graphics;
import goodman.java.awt.Color;
import goodman.java.awt.Insets;
import goodman.java.awt.Point;
import goodman.java.awt.Rectangle;
import goodman.java.awt.event.*;
import goodman.java.io.Serializable;

import goodman.javax.swing.*;
import goodman.javax.swing.event.*;
import goodman.javax.swing.border.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.BasicMenuBarUI;
//REMIND
import goodman.javax.swing.plaf.basic.*;

/**
 * A Windows L&F implementation of MenuBarUI.  This implementation
 * is a "combined" view/controller.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases.  The current serialization support is appropriate
 * for short term storage or RMI between applications running the same
 * version of Swing.  A future release of Swing will provide support for
 * long term persistence.
 *
 * @author Georges Saab
 * @author Rich Schiavi
 */

public class MotifMenuBarUI extends BasicMenuBarUI
{

    public static ComponentUI createUI(JComponent x) {
        return new MotifMenuBarUI();
    }

} // end class
