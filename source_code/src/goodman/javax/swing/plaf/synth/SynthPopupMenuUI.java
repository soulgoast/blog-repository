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

import goodman.javax.swing.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.*;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthLookAndFeel;
import goodman.javax.swing.plaf.synth.SynthMenuLayout;
import goodman.javax.swing.plaf.synth.SynthStyle;
import goodman.javax.swing.plaf.synth.SynthUI;
import goodman.java.awt.Graphics;
import goodman.java.beans.PropertyChangeListener;
import goodman.java.beans.PropertyChangeEvent;

/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JPopupMenu}.
 *
 * @author Georges Saab
 * @author David Karlton
 * @author Arnaud Weber
 * @since 1.7
 */
public class SynthPopupMenuUI extends BasicPopupMenuUI
                              implements PropertyChangeListener, SynthUI {
    private javax.swing.plaf.synth.SynthStyle style;

    /**
     * Creates a new UI object for the given component.
     *
     * @param x component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent x) {
        return new SynthPopupMenuUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void installDefaults() {
        if (popupMenu.getLayout() == null ||
            popupMenu.getLayout() instanceof UIResource) {
            popupMenu.setLayout(new javax.swing.plaf.synth.SynthMenuLayout(popupMenu, BoxLayout.Y_AXIS));
        }
        updateStyle(popupMenu);
    }

    private void updateStyle(JComponent c) {
        javax.swing.plaf.synth.SynthContext context = getContext(c, ENABLED);
        SynthStyle oldStyle = style;
        style = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);
        if (style != oldStyle) {
            if (oldStyle != null) {
                uninstallKeyboardActions();
                installKeyboardActions();
            }
        }
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installListeners() {
        super.installListeners();
        popupMenu.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallDefaults() {
        javax.swing.plaf.synth.SynthContext context = getContext(popupMenu, ENABLED);

        style.uninstallDefaults(context);
        context.dispose();
        style = null;

        if (popupMenu.getLayout() instanceof UIResource) {
            popupMenu.setLayout(null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        popupMenu.removePropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public javax.swing.plaf.synth.SynthContext getContext(JComponent c) {
        return getContext(c, getComponentState(c));
    }

    private javax.swing.plaf.synth.SynthContext getContext(JComponent c, int state) {
        return javax.swing.plaf.synth.SynthContext.getContext(c, style, state);
    }

    private int getComponentState(JComponent c) {
        return javax.swing.plaf.synth.SynthLookAndFeel.getComponentState(c);
    }

    /**
     * Notifies this UI delegate to repaint the specified component.
     * This method paints the component background, then calls
     * the {@link #paint(javax.swing.plaf.synth.SynthContext,Graphics)} method.
     *
     * <p>In general, this method does not need to be overridden by subclasses.
     * All Look and Feel rendering code should reside in the {@code paint} method.
     *
     * @param g the {@code Graphics} object used for painting
     * @param c the component being painted
     * @see #paint(javax.swing.plaf.synth.SynthContext,Graphics)
     */
    @Override
    public void update(Graphics g, JComponent c) {
        javax.swing.plaf.synth.SynthContext context = getContext(c);

        javax.swing.plaf.synth.SynthLookAndFeel.update(context, g);
        context.getPainter().paintPopupMenuBackground(context,
                          g, 0, 0, c.getWidth(), c.getHeight());
        paint(context, g);
        context.dispose();
    }

    /**
     * Paints the specified component according to the Look and Feel.
     * <p>This method is not used by Synth Look and Feel.
     * Painting is handled by the {@link #paint(javax.swing.plaf.synth.SynthContext,Graphics)} method.
     *
     * @param g the {@code Graphics} object used for painting
     * @param c the component being painted
     * @see #paint(javax.swing.plaf.synth.SynthContext,Graphics)
     */
    @Override
    public void paint(Graphics g, JComponent c) {
        javax.swing.plaf.synth.SynthContext context = getContext(c);

        paint(context, g);
        context.dispose();
    }

    /**
     * Paints the specified component. This implementation does nothing.
     *
     * @param context context for the component being painted
     * @param g the {@code Graphics} object used for painting
     * @see #update(Graphics,JComponent)
     */
    protected void paint(javax.swing.plaf.synth.SynthContext context, Graphics g) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintPopupMenuBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (SynthLookAndFeel.shouldUpdateStyle(e)) {
            updateStyle(popupMenu);
        }
    }
}
