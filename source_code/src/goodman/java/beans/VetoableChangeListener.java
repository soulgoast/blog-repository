/*
 * Copyright (c) 1996, 1997, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.beans;

import goodman.java.beans.PropertyChangeEvent;
import goodman.java.beans.PropertyVetoException;

/**
 * A VetoableChange event gets fired whenever a bean changes a "constrained"
 * property.  You can register a VetoableChangeListener with a source bean
 * so as to be notified of any constrained property updates.
 */
public interface VetoableChangeListener extends java.util.EventListener {
    /**
     * This method gets called when a constrained property is changed.
     *
     * @param     evt a <code>PropertyChangeEvent</code> object describing the
     *                event source and the property that has changed.
     * @exception java.beans.PropertyVetoException if the recipient wishes the property
     *              change to be rolled back.
     */
    void vetoableChange(PropertyChangeEvent evt)
                                throws PropertyVetoException;
}
