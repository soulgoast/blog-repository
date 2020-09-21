/*
 * Copyright (c) 1997, 2006, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.java.swing.plaf.windows;

import com.sun.java.swing.plaf.windows.WindowsButtonUI;
import com.sun.java.swing.plaf.windows.XPStyle;

import goodman.java.awt.*;

import goodman.javax.swing.AbstractButton;
import goodman.javax.swing.JComponent;
import goodman.javax.swing.JToggleButton;
import goodman.javax.swing.UIDefaults;
import goodman.javax.swing.UIManager;

import goodman.javax.swing.border.Border;
import goodman.javax.swing.border.CompoundBorder;
import goodman.javax.swing.border.EmptyBorder;

import goodman.javax.swing.plaf.*;

import goodman.javax.swing.plaf.basic.BasicBorders;
import goodman.javax.swing.plaf.basic.BasicToolBarUI;

import static com.sun.java.swing.plaf.windows.TMSchema.Part;


public class WindowsToolBarUI extends BasicToolBarUI {

    public static ComponentUI createUI(JComponent c) {
        return new WindowsToolBarUI();
    }

    protected void installDefaults() {
        if (com.sun.java.swing.plaf.windows.XPStyle.getXP() != null) {
            setRolloverBorders(true);
        }
        super.installDefaults();
    }

    protected Border createRolloverBorder() {
        if (com.sun.java.swing.plaf.windows.XPStyle.getXP() != null) {
            return new EmptyBorder(3, 3, 3, 3);
        } else {
            return super.createRolloverBorder();
        }
    }

    protected Border createNonRolloverBorder() {
        if (com.sun.java.swing.plaf.windows.XPStyle.getXP() != null) {
            return new EmptyBorder(3, 3, 3, 3);
        } else {
            return super.createNonRolloverBorder();
        }
    }

    public void paint(Graphics g, JComponent c) {
        com.sun.java.swing.plaf.windows.XPStyle xp = com.sun.java.swing.plaf.windows.XPStyle.getXP();
        if (xp != null) {
            xp.getSkin(c, Part.TP_TOOLBAR).paintSkin(g, 0, 0,
                        c.getWidth(), c.getHeight(), null, true);
        } else {
            super.paint(g, c);
        }
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    protected Border getRolloverBorder(AbstractButton b) {
        com.sun.java.swing.plaf.windows.XPStyle xp = com.sun.java.swing.plaf.windows.XPStyle.getXP();
        if (xp != null) {
            return xp.getBorder(b, WindowsButtonUI.getXPButtonType(b));
        } else {
            return super.getRolloverBorder(b);
        }
    }
}
