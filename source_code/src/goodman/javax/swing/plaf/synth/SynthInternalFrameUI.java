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
import goodman.java.awt.event.*;
import goodman.javax.swing.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.BasicInternalFrameUI;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthDesktopPaneUI;
import goodman.javax.swing.plaf.synth.SynthInternalFrameTitlePane;
import goodman.javax.swing.plaf.synth.SynthLookAndFeel;
import goodman.javax.swing.plaf.synth.SynthStyle;
import goodman.javax.swing.plaf.synth.SynthUI;
import goodman.java.beans.*;


/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JInternalFrame}.
 *
 * @author David Kloba
 * @author Joshua Outwater
 * @author Rich Schiavi
 * @since 1.7
 */
public class SynthInternalFrameUI extends BasicInternalFrameUI
                                  implements SynthUI, PropertyChangeListener {
    private javax.swing.plaf.synth.SynthStyle style;

    /**
     * Creates a new UI object for the given component.
     *
     * @param b component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent b) {
        return new SynthInternalFrameUI((JInternalFrame)b);
    }

    protected SynthInternalFrameUI(JInternalFrame b) {
        super(b);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void installDefaults() {
        frame.setLayout(internalFrameLayout = createLayoutManager());
        updateStyle(frame);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installListeners() {
        super.installListeners();
        frame.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallComponents() {
        if (frame.getComponentPopupMenu() instanceof UIResource) {
            frame.setComponentPopupMenu(null);
        }
        super.uninstallComponents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallListeners() {
        frame.removePropertyChangeListener(this);
        super.uninstallListeners();
    }

    private void updateStyle(JComponent c) {
        javax.swing.plaf.synth.SynthContext context = getContext(c, ENABLED);
        javax.swing.plaf.synth.SynthStyle oldStyle = style;

        style = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);
        if (style != oldStyle) {
            Icon frameIcon = frame.getFrameIcon();
            if (frameIcon == null || frameIcon instanceof UIResource) {
                frame.setFrameIcon(context.getStyle().getIcon(
                                   context, "InternalFrame.icon"));
            }
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
    protected void uninstallDefaults() {
        javax.swing.plaf.synth.SynthContext context = getContext(frame, ENABLED);
        style.uninstallDefaults(context);
        context.dispose();
        style = null;
        if(frame.getLayout() == internalFrameLayout) {
            frame.setLayout(null);
        }

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
     * {@inheritDoc}
     */
    @Override
    protected JComponent createNorthPane(JInternalFrame w) {
        titlePane = new SynthInternalFrameTitlePane(w);
        titlePane.setName("InternalFrame.northPane");
        return titlePane;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ComponentListener createComponentListener() {
        if (UIManager.getBoolean("InternalFrame.useTaskBar")) {
            return new ComponentHandler() {
                @Override public void componentResized(ComponentEvent e) {
                    if (frame != null && frame.isMaximum()) {
                        JDesktopPane desktop = (JDesktopPane)e.getSource();
                        for (Component comp : desktop.getComponents()) {
                            if (comp instanceof SynthDesktopPaneUI.TaskBar) {
                                frame.setBounds(0, 0,
                                                desktop.getWidth(),
                                                desktop.getHeight() - comp.getHeight());
                                frame.revalidate();
                                break;
                            }
                        }
                    }

                    // Update the new parent bounds for next resize, but don't
                    // let the super method touch this frame
                    JInternalFrame f = frame;
                    frame = null;
                    super.componentResized(e);
                    frame = f;
                }
            };
        } else {
            return super.createComponentListener();
        }
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
        context.getPainter().paintInternalFrameBackground(context,
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
    public void paintBorder(javax.swing.plaf.synth.SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintInternalFrameBorder(context,
                                                            g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SynthStyle oldStyle = style;
        JInternalFrame f = (JInternalFrame)evt.getSource();
        String prop = evt.getPropertyName();

        if (SynthLookAndFeel.shouldUpdateStyle(evt)) {
            updateStyle(f);
        }

        if (style == oldStyle &&
            (prop == JInternalFrame.IS_MAXIMUM_PROPERTY ||
             prop == JInternalFrame.IS_SELECTED_PROPERTY)) {
            // Border (and other defaults) may need to change
            SynthContext context = getContext(f, ENABLED);
            style.uninstallDefaults(context);
            style.installDefaults(context, this);
        }
    }
}
