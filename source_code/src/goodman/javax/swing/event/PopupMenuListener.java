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
package goodman.javax.swing.event;

import goodman.javax.swing.event.PopupMenuEvent;
import goodman.java.util.EventListener;

/**
 * A popup menu listener
 *
 * @author Arnaud Weber
 */
public interface PopupMenuListener extends EventListener {

    /**
     *  This method is called before the popup menu becomes visible
     */
    void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent e);

    /**
     * This method is called before the popup menu becomes invisible
     * Note that a JPopupMenu can become invisible any time
     */
    void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent e);

    /**
     * This method is called when the popup menu is canceled
     */
    void popupMenuCanceled(PopupMenuEvent e);
}
