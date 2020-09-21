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

import goodman.java.awt.*;
import goodman.javax.swing.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.*;

import com.sun.java.swing.plaf.windows.TMSchema.Part;
import com.sun.java.swing.plaf.windows.TMSchema.State;
import com.sun.java.swing.plaf.windows.WindowsGraphicsUtils;
import com.sun.java.swing.plaf.windows.WindowsMenuItemUI;
import com.sun.java.swing.plaf.windows.WindowsMenuItemUIAccessor;


/**
 * Windows check box menu item.
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases.  The current serialization support is appropriate
 * for short term storage or RMI between applications running the same
 * version of Swing.  A future release of Swing will provide support for
 * long term persistence.
 */
public class WindowsCheckBoxMenuItemUI extends BasicCheckBoxMenuItemUI {

    final WindowsMenuItemUIAccessor accessor =
        new WindowsMenuItemUIAccessor() {

            public JMenuItem getMenuItem() {
                return menuItem;
            }

            public State getState(JMenuItem menuItem) {
                return com.sun.java.swing.plaf.windows.WindowsMenuItemUI.getState(this, menuItem);
            }

            public Part getPart(JMenuItem menuItem) {
                return com.sun.java.swing.plaf.windows.WindowsMenuItemUI.getPart(this, menuItem);
            }
    };
    public static ComponentUI createUI(JComponent b) {
        return new WindowsCheckBoxMenuItemUI();
    }

    @Override
    protected  void paintBackground(Graphics g, JMenuItem menuItem,
            Color bgColor) {
        if (com.sun.java.swing.plaf.windows.WindowsMenuItemUI.isVistaPainting()) {
            com.sun.java.swing.plaf.windows.WindowsMenuItemUI.paintBackground(accessor, g, menuItem, bgColor);
            return;
        }
        super.paintBackground(g, menuItem, bgColor);
    }
    /**
     * Method which renders the text of the current menu item.
     * <p>
     * @param g Graphics context
     * @param menuItem Current menu item to render
     * @param textRect Bounding rectangle to render the text.
     * @param text String to render
     * @since 1.4
     */
    protected void paintText(Graphics g, JMenuItem menuItem,
                             Rectangle textRect, String text) {
        if (com.sun.java.swing.plaf.windows.WindowsMenuItemUI.isVistaPainting()) {
            WindowsMenuItemUI.paintText(accessor, g, menuItem,
                                        textRect, text);
            return;
        }
        ButtonModel model = menuItem.getModel();
        Color oldColor = g.getColor();

        if(model.isEnabled() && model.isArmed()) {
            g.setColor(selectionForeground); // Uses protected field.
        }

        WindowsGraphicsUtils.paintText(g, menuItem, textRect, text, 0);

        g.setColor(oldColor);
    }
}
