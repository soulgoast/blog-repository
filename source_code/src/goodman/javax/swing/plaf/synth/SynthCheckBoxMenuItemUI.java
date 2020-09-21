/*
 * Copyright (c) 2002, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.swing.plaf.synth;


import goodman.java.awt.*;
import goodman.javax.swing.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthMenuItemUI;


/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JCheckBoxMenuItem}.
 *
 * @author Leif Samuelsson
 * @author Georges Saab
 * @author David Karlton
 * @author Arnaud Weber
 * @since 1.7
 */
public class SynthCheckBoxMenuItemUI extends SynthMenuItemUI {

    /**
     * Creates a new UI object for the given component.
     *
     * @param c component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent c) {
        return new SynthCheckBoxMenuItemUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyPrefix() {
        return "CheckBoxMenuItem";
    }

    @Override
    void paintBackground(javax.swing.plaf.synth.SynthContext context, Graphics g, JComponent c) {
        context.getPainter().paintCheckBoxMenuItemBackground(context, g, 0, 0,
                                                  c.getWidth(), c.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintCheckBoxMenuItemBorder(context, g, x, y, w, h);
    }
}
