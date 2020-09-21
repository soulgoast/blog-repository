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
import goodman.java.beans.*;
import goodman.java.util.*;
import goodman.javax.swing.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.*;
import goodman.javax.swing.plaf.synth.Region;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthLookAndFeel;
import goodman.javax.swing.plaf.synth.SynthSplitPaneDivider;
import goodman.javax.swing.plaf.synth.SynthStyle;
import goodman.javax.swing.plaf.synth.SynthUI;


/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JSplitPane}.
 *
 * @author Scott Violet
 * @since 1.7
 */
public class SynthSplitPaneUI extends BasicSplitPaneUI
                              implements PropertyChangeListener, SynthUI {
    /**
     * Keys to use for forward focus traversal when the JComponent is
     * managing focus.
     */
    private static Set<KeyStroke> managingFocusForwardTraversalKeys;

    /**
     * Keys to use for backward focus traversal when the JComponent is
     * managing focus.
     */
    private static Set<KeyStroke> managingFocusBackwardTraversalKeys;

    /**
     * Style for the JSplitPane.
     */
    private javax.swing.plaf.synth.SynthStyle style;
    /**
     * Style for the divider.
     */
    private javax.swing.plaf.synth.SynthStyle dividerStyle;


    /**
     * Creates a new SynthSplitPaneUI instance
     *
     * @param x component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent x) {
        return new SynthSplitPaneUI();
    }

    /**
     * Installs the UI defaults.
     */
    @Override
    protected void installDefaults() {
        updateStyle(splitPane);

        setOrientation(splitPane.getOrientation());
        setContinuousLayout(splitPane.isContinuousLayout());

        resetLayoutManager();

        /* Install the nonContinuousLayoutDivider here to avoid having to
        add/remove everything later. */
        if(nonContinuousLayoutDivider == null) {
            setNonContinuousLayoutDivider(
                                createDefaultNonContinuousLayoutDivider(),
                                true);
        } else {
            setNonContinuousLayoutDivider(nonContinuousLayoutDivider, true);
        }

        // focus forward traversal key
        if (managingFocusForwardTraversalKeys==null) {
            managingFocusForwardTraversalKeys = new HashSet<KeyStroke>();
            managingFocusForwardTraversalKeys.add(
                KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
        }
        splitPane.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS,
                                        managingFocusForwardTraversalKeys);
        // focus backward traversal key
        if (managingFocusBackwardTraversalKeys==null) {
            managingFocusBackwardTraversalKeys = new HashSet<KeyStroke>();
            managingFocusBackwardTraversalKeys.add(
                KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK));
        }
        splitPane.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS,
                                        managingFocusBackwardTraversalKeys);
    }

    private void updateStyle(JSplitPane splitPane) {
        javax.swing.plaf.synth.SynthContext context = getContext(splitPane, javax.swing.plaf.synth.Region.SPLIT_PANE_DIVIDER,
                                          ENABLED);
        javax.swing.plaf.synth.SynthStyle oldDividerStyle = dividerStyle;
        dividerStyle = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);
        context.dispose();

        context = getContext(splitPane, ENABLED);
        SynthStyle oldStyle = style;

        style = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);

        if (style != oldStyle) {
            Object value = style.get(context, "SplitPane.size");
            if (value == null) {
                value = Integer.valueOf(6);
            }
            LookAndFeel.installProperty(splitPane, "dividerSize", value);

            value = style.get(context, "SplitPane.oneTouchExpandable");
            if (value != null) {
                LookAndFeel.installProperty(splitPane, "oneTouchExpandable", value);
            }

            if (divider != null) {
                splitPane.remove(divider);
                divider.setDividerSize(splitPane.getDividerSize());
            }
            if (oldStyle != null) {
                uninstallKeyboardActions();
                installKeyboardActions();
            }
        }
        if (style != oldStyle || dividerStyle != oldDividerStyle) {
            // Only way to force BasicSplitPaneDivider to reread the
            // necessary properties.
            if (divider != null) {
                splitPane.remove(divider);
            }
            divider = createDefaultDivider();
            divider.setBasicSplitPaneUI(this);
            splitPane.add(divider, JSplitPane.DIVIDER);
        }
        context.dispose();
    }

    /**
     * Installs the event listeners for the UI.
     */
    @Override
    protected void installListeners() {
        super.installListeners();
        splitPane.addPropertyChangeListener(this);
    }

    /**
     * Uninstalls the UI defaults.
     */
    @Override
    protected void uninstallDefaults() {
        javax.swing.plaf.synth.SynthContext context = getContext(splitPane, ENABLED);

        style.uninstallDefaults(context);
        context.dispose();
        style = null;

        context = getContext(splitPane, javax.swing.plaf.synth.Region.SPLIT_PANE_DIVIDER, ENABLED);
        dividerStyle.uninstallDefaults(context);
        context.dispose();
        dividerStyle = null;

        super.uninstallDefaults();
    }


    /**
     * Uninstalls the event listeners from the UI.
     */
    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        splitPane.removePropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public javax.swing.plaf.synth.SynthContext getContext(JComponent c) {
        return getContext(c, javax.swing.plaf.synth.SynthLookAndFeel.getComponentState(c));
    }

    private javax.swing.plaf.synth.SynthContext getContext(JComponent c, int state) {
        return javax.swing.plaf.synth.SynthContext.getContext(c, style, state);
    }

    javax.swing.plaf.synth.SynthContext getContext(JComponent c, javax.swing.plaf.synth.Region region) {
        return getContext(c, region, getComponentState(c, region));
    }

    private javax.swing.plaf.synth.SynthContext getContext(JComponent c, javax.swing.plaf.synth.Region region, int state) {
        if (region == javax.swing.plaf.synth.Region.SPLIT_PANE_DIVIDER) {
            return javax.swing.plaf.synth.SynthContext.getContext(c, region, dividerStyle, state);
        }
        return javax.swing.plaf.synth.SynthContext.getContext(c, region, style, state);
    }

    private int getComponentState(JComponent c, javax.swing.plaf.synth.Region subregion) {
        int state = javax.swing.plaf.synth.SynthLookAndFeel.getComponentState(c);

        if (divider.isMouseOver()) {
            state |= MOUSE_OVER;
        }
        return state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (javax.swing.plaf.synth.SynthLookAndFeel.shouldUpdateStyle(e)) {
            updateStyle((JSplitPane)e.getSource());
        }
    }

    /**
     * Creates the default divider.
     */
    @Override
    public BasicSplitPaneDivider createDefaultDivider() {
        SynthSplitPaneDivider divider = new SynthSplitPaneDivider(this);

        divider.setDividerSize(splitPane.getDividerSize());
        return divider;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Component createDefaultNonContinuousLayoutDivider() {
        return new Canvas() {
            public void paint(Graphics g) {
                paintDragDivider(g, 0, 0, getWidth(), getHeight());
            }
        };
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

        SynthLookAndFeel.update(context, g);
        context.getPainter().paintSplitPaneBackground(context,
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
        // This is done to update package private variables in
        // BasicSplitPaneUI
        super.paint(g, splitPane);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(javax.swing.plaf.synth.SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintSplitPaneBorder(context, g, x, y, w, h);
    }

    private void paintDragDivider(Graphics g, int x, int y, int w, int h) {
        SynthContext context = getContext(splitPane, Region.SPLIT_PANE_DIVIDER);
        context.setComponentState(((context.getComponentState() | MOUSE_OVER) ^
                                   MOUSE_OVER) | PRESSED);
        Shape oldClip = g.getClip();
        g.clipRect(x, y, w, h);
        context.getPainter().paintSplitPaneDragDivider(context, g, x, y, w, h,
                                           splitPane.getOrientation());
        g.setClip(oldClip);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void finishedPaintingChildren(JSplitPane jc, Graphics g) {
        if(jc == splitPane && getLastDragLocation() != -1 &&
                              !isContinuousLayout() && !draggingHW) {
            if(jc.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
                paintDragDivider(g, getLastDragLocation(), 0, dividerSize - 1,
                                 splitPane.getHeight() - 1);
            } else {
                paintDragDivider(g, 0, getLastDragLocation(),
                                 splitPane.getWidth() - 1, dividerSize - 1);
            }
        }
    }
}
