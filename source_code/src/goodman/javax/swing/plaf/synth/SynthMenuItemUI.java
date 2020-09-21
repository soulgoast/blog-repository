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
import goodman.java.beans.PropertyChangeEvent;
import goodman.java.beans.PropertyChangeListener;
import goodman.javax.swing.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.*;
import goodman.javax.swing.plaf.synth.*;
import goodman.javax.swing.plaf.synth.Region;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthStyle;
import goodman.javax.swing.plaf.synth.SynthUI;

import sun.swing.MenuItemLayoutHelper;


/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JMenuItem}.
 *
 * @author Georges Saab
 * @author David Karlton
 * @author Arnaud Weber
 * @author Fredrik Lagerblad
 * @since 1.7
 */
public class SynthMenuItemUI extends BasicMenuItemUI implements
                                   PropertyChangeListener, SynthUI {
    private javax.swing.plaf.synth.SynthStyle style;
    private javax.swing.plaf.synth.SynthStyle accStyle;

    /**
     * Creates a new UI object for the given component.
     *
     * @param c component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent c) {
        return new SynthMenuItemUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void uninstallUI(JComponent c) {
        super.uninstallUI(c);
        // Remove values from the parent's Client Properties.
        JComponent p = MenuItemLayoutHelper.getMenuItemParent((JMenuItem) c);
        if (p != null) {
            p.putClientProperty(
                    SynthMenuItemLayoutHelper.MAX_ACC_OR_ARROW_WIDTH, null);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installDefaults() {
        updateStyle(menuItem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installListeners() {
        super.installListeners();
        menuItem.addPropertyChangeListener(this);
    }

    private void updateStyle(JMenuItem mi) {
        javax.swing.plaf.synth.SynthContext context = getContext(mi, ENABLED);
        SynthStyle oldStyle = style;

        style = SynthLookAndFeel.updateStyle(context, this);
        if (oldStyle != style) {
            String prefix = getPropertyPrefix();

            Object value = style.get(context, prefix + ".textIconGap");
            if (value != null) {
                LookAndFeel.installProperty(mi, "iconTextGap", value);
            }
            defaultTextIconGap = mi.getIconTextGap();

            if (menuItem.getMargin() == null ||
                         (menuItem.getMargin() instanceof UIResource)) {
                Insets insets = (Insets)style.get(context, prefix + ".margin");

                if (insets == null) {
                    // Some places assume margins are non-null.
                    insets = SynthLookAndFeel.EMPTY_UIRESOURCE_INSETS;
                }
                menuItem.setMargin(insets);
            }
            acceleratorDelimiter = style.getString(context, prefix +
                                            ".acceleratorDelimiter", "+");

            arrowIcon = style.getIcon(context, prefix + ".arrowIcon");

            checkIcon = style.getIcon(context, prefix + ".checkIcon");
            if (oldStyle != null) {
                uninstallKeyboardActions();
                installKeyboardActions();
            }
        }
        context.dispose();

        javax.swing.plaf.synth.SynthContext accContext = getContext(mi, javax.swing.plaf.synth.Region.MENU_ITEM_ACCELERATOR,
                                             ENABLED);

        accStyle = SynthLookAndFeel.updateStyle(accContext, this);
        accContext.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallDefaults() {
        javax.swing.plaf.synth.SynthContext context = getContext(menuItem, ENABLED);
        style.uninstallDefaults(context);
        context.dispose();
        style = null;

        javax.swing.plaf.synth.SynthContext accContext = getContext(menuItem,
                                     javax.swing.plaf.synth.Region.MENU_ITEM_ACCELERATOR, ENABLED);
        accStyle.uninstallDefaults(accContext);
        accContext.dispose();
        accStyle = null;

        super.uninstallDefaults();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        menuItem.removePropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public javax.swing.plaf.synth.SynthContext getContext(JComponent c) {
        return getContext(c, getComponentState(c));
    }

    javax.swing.plaf.synth.SynthContext getContext(JComponent c, int state) {
        return javax.swing.plaf.synth.SynthContext.getContext(c, style, state);
    }

    javax.swing.plaf.synth.SynthContext getContext(JComponent c, javax.swing.plaf.synth.Region region) {
        return getContext(c, region, getComponentState(c, region));
    }

    private javax.swing.plaf.synth.SynthContext getContext(JComponent c, javax.swing.plaf.synth.Region region, int state) {
        return javax.swing.plaf.synth.SynthContext.getContext(c, region, accStyle, state);
    }

    private int getComponentState(JComponent c) {
        int state;

        if (!c.isEnabled()) {
            state = DISABLED;
        }
        else if (menuItem.isArmed()) {
            state = MOUSE_OVER;
        }
        else {
            state = SynthLookAndFeel.getComponentState(c);
        }
        if (menuItem.isSelected()) {
            state |= SELECTED;
        }
        return state;
    }

    private int getComponentState(JComponent c, javax.swing.plaf.synth.Region region) {
        return getComponentState(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension getPreferredMenuItemSize(JComponent c,
                                                     Icon checkIcon,
                                                     Icon arrowIcon,
                                                     int defaultTextIconGap) {
        javax.swing.plaf.synth.SynthContext context = getContext(c);
        javax.swing.plaf.synth.SynthContext accContext = getContext(c, javax.swing.plaf.synth.Region.MENU_ITEM_ACCELERATOR);
        Dimension value = SynthGraphicsUtils.getPreferredMenuItemSize(
                context, accContext, c, checkIcon, arrowIcon,
                defaultTextIconGap, acceleratorDelimiter,
                MenuItemLayoutHelper.useCheckAndArrow(menuItem),
                getPropertyPrefix());
        context.dispose();
        accContext.dispose();
        return value;
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
        paintBackground(context, g, c);
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
        javax.swing.plaf.synth.SynthContext accContext = getContext(menuItem,
                                             Region.MENU_ITEM_ACCELERATOR);

        // Refetch the appropriate check indicator for the current state
        String prefix = getPropertyPrefix();
        Icon checkIcon = style.getIcon(context, prefix + ".checkIcon");
        Icon arrowIcon = style.getIcon(context, prefix + ".arrowIcon");
        SynthGraphicsUtils.paint(context, accContext, g, checkIcon, arrowIcon,
              acceleratorDelimiter, defaultTextIconGap, getPropertyPrefix());
        accContext.dispose();
    }

    void paintBackground(javax.swing.plaf.synth.SynthContext context, Graphics g, JComponent c) {
        SynthGraphicsUtils.paintBackground(context, g, c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintMenuItemBorder(context, g, x, y, w, h);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if (SynthLookAndFeel.shouldUpdateStyle(e)) {
            updateStyle((JMenuItem)e.getSource());
        }
    }
}
