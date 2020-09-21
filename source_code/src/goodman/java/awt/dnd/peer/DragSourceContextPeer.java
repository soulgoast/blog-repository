/*
 * Copyright (c) 1997, 2007, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.awt.dnd.peer;

import goodman.java.awt.Cursor;
import goodman.java.awt.Image;
import goodman.java.awt.Point;
import goodman.java.awt.dnd.DragSourceContext;
import goodman.java.awt.dnd.InvalidDnDOperationException;


/**
 * <p>
 * This interface is supplied by the underlying window system platform to
 * expose the behaviors of the Drag and Drop system to an originator of
 * the same
 * </p>
 *
 * @since 1.2
 *
 */

public interface DragSourceContextPeer {

    /**
     * start a drag
     */

    void startDrag(DragSourceContext dsc, Cursor c, Image dragImage, Point imageOffset) throws InvalidDnDOperationException;

    /**
     * return the current drag cursor
     */

    Cursor getCursor();

    /**
     * set the current drag cursor
     */

    void setCursor(Cursor c) throws InvalidDnDOperationException;

    /**
     * notify the peer that the Transferables DataFlavors have changed
     */

    void transferablesFlavorsChanged();
}
