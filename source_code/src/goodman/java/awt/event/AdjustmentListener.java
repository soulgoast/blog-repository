/*
 * Copyright (c) 1996, 1999, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.awt.event;

import goodman.java.awt.event.AdjustmentEvent;
import goodman.java.util.EventListener;

/**
 * The listener interface for receiving adjustment events.
 *
 * @author Amy Fowler
 * @since 1.1
 */
public interface AdjustmentListener extends EventListener {

    /**
     * Invoked when the value of the adjustable has changed.
     */
    public void adjustmentValueChanged(AdjustmentEvent e);

}
