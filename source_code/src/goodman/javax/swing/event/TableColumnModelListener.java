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

import goodman.javax.swing.event.ListSelectionEvent;
import goodman.javax.swing.event.ChangeEvent;
import goodman.javax.swing.event.TableColumnModelEvent;
import goodman.java.util.EventListener;

/**
 * TableColumnModelListener defines the interface for an object that listens
 * to changes in a TableColumnModel.
 *
 * @author Alan Chung
 * @see javax.swing.event.TableColumnModelEvent
 */

public interface TableColumnModelListener extends EventListener
{
    /** Tells listeners that a column was added to the model. */
    public void columnAdded(javax.swing.event.TableColumnModelEvent e);

    /** Tells listeners that a column was removed from the model. */
    public void columnRemoved(javax.swing.event.TableColumnModelEvent e);

    /** Tells listeners that a column was repositioned. */
    public void columnMoved(TableColumnModelEvent e);

    /** Tells listeners that a column was moved due to a margin change. */
    public void columnMarginChanged(ChangeEvent e);

    /**
     * Tells listeners that the selection model of the
     * TableColumnModel changed.
     */
    public void columnSelectionChanged(ListSelectionEvent e);
}
