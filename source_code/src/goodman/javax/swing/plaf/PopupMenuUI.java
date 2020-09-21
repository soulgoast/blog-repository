/*
 * Copyright (c) 1997, 2000, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.swing.plaf;

import goodman.java.awt.event.MouseEvent;
import goodman.javax.swing.Popup;
import goodman.javax.swing.PopupFactory;
import goodman.javax.swing.JPopupMenu;
import goodman.javax.swing.plaf.ComponentUI;

/**
 * Pluggable look and feel interface for JPopupMenu.
 *
 * @author Georges Saab
 * @author David Karlton
 */

public abstract class PopupMenuUI extends ComponentUI {
    /**
     * @since 1.3
     */
    public boolean isPopupTrigger(MouseEvent e) {
        return e.isPopupTrigger();
    }

    /**
     * Returns the <code>Popup</code> that will be responsible for
     * displaying the <code>JPopupMenu</code>.
     *
     * @param popup JPopupMenu requesting Popup
     * @param x     Screen x location Popup is to be shown at
     * @param y     Screen y location Popup is to be shown at.
     * @return Popup that will show the JPopupMenu
     * @since 1.4
     */
    public Popup getPopup(JPopupMenu popup, int x, int y) {
        PopupFactory popupFactory = PopupFactory.getSharedInstance();

        return popupFactory.getPopup(popup.getInvoker(), popup, x, y);
    }
}
