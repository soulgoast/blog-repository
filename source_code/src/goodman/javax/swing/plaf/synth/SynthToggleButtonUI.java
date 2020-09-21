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

import goodman.java.awt.Graphics;
import goodman.javax.swing.AbstractButton;
import goodman.javax.swing.JComponent;
import goodman.javax.swing.plaf.ComponentUI;
import goodman.javax.swing.plaf.synth.SynthButtonUI;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthPainter;

/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link javax.swing.JToggleButton}.
 *
 * @author Jeff Dinkins
 * @since 1.7
 */
public class SynthToggleButtonUI extends SynthButtonUI {
    // ********************************
    //          Create PLAF
    // ********************************

    /**
     * Creates a new UI object for the given component.
     *
     * @param b component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent b) {
        return new SynthToggleButtonUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPropertyPrefix() {
        return "ToggleButton.";
    }

    @Override
    void paintBackground(javax.swing.plaf.synth.SynthContext context, Graphics g, JComponent c) {
        if (((AbstractButton) c).isContentAreaFilled()) {
            int x = 0, y = 0, w = c.getWidth(), h = c.getHeight();
            SynthPainter painter = context.getPainter();
            painter.paintToggleButtonBackground(context, g, x, y, w, h);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintToggleButtonBorder(context, g, x, y, w, h);
    }
}
