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

import goodman.java.awt.event.*;
import goodman.javax.swing.MenuSelectionManager;

/**
 * A default MouseListener for menu elements
 *
 * @author Arnaud Weber
 */
class MotifMenuMouseListener extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
    public void mouseReleased(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
    public void mouseEntered(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
    public void mouseExited(MouseEvent e) {
        MenuSelectionManager.defaultManager().processMouseEvent(e);
    }
}
