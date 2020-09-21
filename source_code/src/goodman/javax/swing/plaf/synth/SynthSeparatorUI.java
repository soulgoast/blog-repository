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

import goodman.java.beans.*;
import goodman.javax.swing.*;
import goodman.java.awt.Dimension;
import goodman.java.awt.Graphics;
import goodman.java.awt.Insets;
import goodman.javax.swing.plaf.ComponentUI;
import goodman.javax.swing.plaf.SeparatorUI;
import goodman.javax.swing.plaf.UIResource;
import goodman.javax.swing.plaf.DimensionUIResource;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthLookAndFeel;
import goodman.javax.swing.plaf.synth.SynthStyle;
import goodman.javax.swing.plaf.synth.SynthUI;

/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JSeparator}.
 *
 * @author Shannon Hickey
 * @author Joshua Outwater
 * @since 1.7
 */
public class SynthSeparatorUI extends SeparatorUI
                              implements PropertyChangeListener, SynthUI {
    private javax.swing.plaf.synth.SynthStyle style;

    /**
     * Creates a new UI object for the given component.
     *
     * @param c component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent c) {
        return new SynthSeparatorUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void installUI(JComponent c) {
        installDefaults((JSeparator)c);
        installListeners((JSeparator)c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uninstallUI(JComponent c) {
        uninstallListeners((JSeparator)c);
        uninstallDefaults((JSeparator)c);
    }

    /**
     * Installs default setting. This method is called when a
     * {@code LookAndFeel} is installed.
     */
    public void installDefaults(JSeparator c) {
        updateStyle(c);
    }

    private void updateStyle(JSeparator sep) {
        javax.swing.plaf.synth.SynthContext context = getContext(sep, ENABLED);
        SynthStyle oldStyle = style;

        style = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);

        if (style != oldStyle) {
            if (sep instanceof JToolBar.Separator) {
                Dimension size = ((JToolBar.Separator)sep).getSeparatorSize();
                if (size == null || size instanceof UIResource) {
                    size = (DimensionUIResource)style.get(
                                      context, "ToolBar.separatorSize");
                    if (size == null) {
                        size = new DimensionUIResource(10, 10);
                    }
                    ((JToolBar.Separator)sep).setSeparatorSize(size);
                }
            }
        }

        context.dispose();
    }

    /**
     * Uninstalls default setting. This method is called when a
     * {@code LookAndFeel} is uninstalled.
     */
    public void uninstallDefaults(JSeparator c) {
        javax.swing.plaf.synth.SynthContext context = getContext(c, ENABLED);

        style.uninstallDefaults(context);
        context.dispose();
        style = null;
    }

    /**
     * Installs listeners. This method is called when a
     * {@code LookAndFeel} is installed.
     */
    public void installListeners(JSeparator c) {
        c.addPropertyChangeListener(this);
    }

    /**
     * Uninstalls listeners. This method is called when a
     * {@code LookAndFeel} is uninstalled.
     */
    public void uninstallListeners(JSeparator c) {
        c.removePropertyChangeListener(this);
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

        JSeparator separator = (JSeparator)context.getComponent();
        javax.swing.plaf.synth.SynthLookAndFeel.update(context, g);
        context.getPainter().paintSeparatorBackground(context,
                          g, 0, 0, c.getWidth(), c.getHeight(),
                          separator.getOrientation());
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
     * Paints the specified component.
     *
     * @param context context for the component being painted
     * @param g the {@code Graphics} object used for painting
     * @see #update(Graphics,JComponent)
     */
    protected void paint(javax.swing.plaf.synth.SynthContext context, Graphics g) {
        JSeparator separator = (JSeparator)context.getComponent();
        context.getPainter().paintSeparatorForeground(context, g, 0, 0,
                             separator.getWidth(), separator.getHeight(),
                             separator.getOrientation());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(javax.swing.plaf.synth.SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        JSeparator separator = (JSeparator)context.getComponent();
        context.getPainter().paintSeparatorBorder(context, g, x, y, w, h,
                                                  separator.getOrientation());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        javax.swing.plaf.synth.SynthContext context = getContext(c);

        int thickness = style.getInt(context, "Separator.thickness", 2);
        Insets insets = c.getInsets();
        Dimension size;

        if (((JSeparator)c).getOrientation() == JSeparator.VERTICAL) {
            size = new Dimension(insets.left + insets.right + thickness,
                                 insets.top + insets.bottom);
        } else {
            size = new Dimension(insets.left + insets.right,
                                 insets.top + insets.bottom + thickness);
        }
        context.dispose();
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMinimumSize(JComponent c) {
        return getPreferredSize(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Dimension getMaximumSize(JComponent c) {
        return new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public javax.swing.plaf.synth.SynthContext getContext(JComponent c) {
        return getContext(c, javax.swing.plaf.synth.SynthLookAndFeel.getComponentState(c));
    }

    private javax.swing.plaf.synth.SynthContext getContext(JComponent c, int state) {
        return SynthContext.getContext(c, style, state);
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if (SynthLookAndFeel.shouldUpdateStyle(evt)) {
            updateStyle((JSeparator)evt.getSource());
        }
    }
}
