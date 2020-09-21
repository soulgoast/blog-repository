/*
 * Copyright (c) 1998, Oracle and/or its affiliates. All rights reserved.
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
package goodman.javax.swing.event;


import goodman.javax.swing.event.MenuDragMouseEvent;
import goodman.java.util.EventListener;


/**
 * Defines a menu mouse-drag listener.
 *
 * @author Georges Saab
 */
public interface MenuDragMouseListener extends EventListener {
    /**
     * Invoked when the dragged mouse has entered a menu component's
     * display area.
     *
     * @param e  a MenuDragMouseEvent object
     */
    void menuDragMouseEntered(javax.swing.event.MenuDragMouseEvent e);
    /**
     * Invoked when the dragged mouse has left a menu component's
     * display area.
     *
     * @param e  a MenuDragMouseEvent object
     */
    void menuDragMouseExited(javax.swing.event.MenuDragMouseEvent e);
    /**
     * Invoked when the mouse is being dragged in a menu component's
     * display area.
     *
     * @param e  a MenuDragMouseEvent object
     */
    void menuDragMouseDragged(javax.swing.event.MenuDragMouseEvent e);
    /**
     * Invoked when a dragged mouse is release in a menu component's
     * display area.
     *
     * @param e  a MenuDragMouseEvent object
     */
    void menuDragMouseReleased(MenuDragMouseEvent e);
}
