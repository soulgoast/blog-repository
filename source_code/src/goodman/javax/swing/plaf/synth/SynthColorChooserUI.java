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
import goodman.javax.swing.colorchooser.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.BasicColorChooserUI;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthLookAndFeel;
import goodman.javax.swing.plaf.synth.SynthStyle;
import goodman.javax.swing.plaf.synth.SynthUI;
import goodman.java.awt.*;
import goodman.java.beans.PropertyChangeEvent;
import goodman.java.beans.PropertyChangeListener;


/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JColorChooser}.
 *
 * @author Tom Santos
 * @author Steve Wilson
 * @since 1.7
 */
public class SynthColorChooserUI extends BasicColorChooserUI implements
        PropertyChangeListener, SynthUI {
    private SynthStyle style;

    /**
     * Creates a new UI object for the given component.
     *
     * @param c component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent c) {
        return new SynthColorChooserUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AbstractColorChooserPanel[] createDefaultChoosers() {
        javax.swing.plaf.synth.SynthContext context = getContext(chooser, ENABLED);
        AbstractColorChooserPanel[] panels = (AbstractColorChooserPanel[])
                     context.getStyle().get(context, "ColorChooser.panels");
        context.dispose();

        if (panels == null) {
            panels = ColorChooserComponentFactory.getDefaultChooserPanels();
        }
        return panels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installDefaults() {
        super.installDefaults();
        updateStyle(chooser);
    }

    private void updateStyle(JComponent c) {
        javax.swing.plaf.synth.SynthContext context = getContext(c, ENABLED);
        style = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallDefaults() {
        javax.swing.plaf.synth.SynthContext context = getContext(chooser, ENABLED);

        style.uninstallDefaults(context);
        context.dispose();
        style = null;
        super.uninstallDefaults();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installListeners() {
        super.installListeners();
        chooser.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallListeners() {
        chooser.removePropertyChangeListener(this);
        super.uninstallListeners();
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
        context.getPainter().paintColorChooserBackground(context, g, 0, 0,
                                                  c.getWidth(), c.getHeight());
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
     * This implementation does not perform any actions.
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
        context.getPainter().paintColorChooserBorder(context, g, x, y,w,h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (SynthLookAndFeel.shouldUpdateStyle(e)) {
            updateStyle((JColorChooser)e.getSource());
        }
    }
}
