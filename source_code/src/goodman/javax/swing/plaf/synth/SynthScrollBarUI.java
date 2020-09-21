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
import goodman.java.beans.*;
import goodman.javax.swing.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.*;
import goodman.javax.swing.plaf.synth.Region;
import goodman.javax.swing.plaf.synth.SynthArrowButton;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthLookAndFeel;
import goodman.javax.swing.plaf.synth.SynthStyle;
import goodman.javax.swing.plaf.synth.SynthUI;


/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JScrollBar}.
 *
 * @author Scott Violet
 * @since 1.7
 */
public class SynthScrollBarUI extends BasicScrollBarUI
                              implements PropertyChangeListener, SynthUI {

    private javax.swing.plaf.synth.SynthStyle style;
    private javax.swing.plaf.synth.SynthStyle thumbStyle;
    private javax.swing.plaf.synth.SynthStyle trackStyle;

    private boolean validMinimumThumbSize;

    public static ComponentUI createUI(JComponent c)    {
        return new SynthScrollBarUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installDefaults() {
        super.installDefaults();
        trackHighlight = NO_HIGHLIGHT;
        if (scrollbar.getLayout() == null ||
                     (scrollbar.getLayout() instanceof UIResource)) {
            scrollbar.setLayout(this);
        }
        configureScrollBarColors();
        updateStyle(scrollbar);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configureScrollBarColors() {
    }

    private void updateStyle(JScrollBar c) {
        javax.swing.plaf.synth.SynthStyle oldStyle = style;
        javax.swing.plaf.synth.SynthContext context = getContext(c, ENABLED);
        style = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);
        if (style != oldStyle) {
            scrollBarWidth = style.getInt(context,"ScrollBar.thumbHeight", 14);
            minimumThumbSize = (Dimension)style.get(context,
                                                "ScrollBar.minimumThumbSize");
            if (minimumThumbSize == null) {
                minimumThumbSize = new Dimension();
                validMinimumThumbSize = false;
            }
            else {
                validMinimumThumbSize = true;
            }
            maximumThumbSize = (Dimension)style.get(context,
                        "ScrollBar.maximumThumbSize");
            if (maximumThumbSize == null) {
                maximumThumbSize = new Dimension(4096, 4097);
            }

            incrGap = style.getInt(context, "ScrollBar.incrementButtonGap", 0);
            decrGap = style.getInt(context, "ScrollBar.decrementButtonGap", 0);

            // handle scaling for sizeVarients for special case components. The
            // key "JComponent.sizeVariant" scales for large/small/mini
            // components are based on Apples LAF
            String scaleKey = (String)scrollbar.getClientProperty(
                    "JComponent.sizeVariant");
            if (scaleKey != null){
                if ("large".equals(scaleKey)){
                    scrollBarWidth *= 1.15;
                    incrGap *= 1.15;
                    decrGap *= 1.15;
                } else if ("small".equals(scaleKey)){
                    scrollBarWidth *= 0.857;
                    incrGap *= 0.857;
                    decrGap *= 0.857;
                } else if ("mini".equals(scaleKey)){
                    scrollBarWidth *= 0.714;
                    incrGap *= 0.714;
                    decrGap *= 0.714;
                }
            }

            if (oldStyle != null) {
                uninstallKeyboardActions();
                installKeyboardActions();
            }
        }
        context.dispose();

        context = getContext(c, javax.swing.plaf.synth.Region.SCROLL_BAR_TRACK, ENABLED);
        trackStyle = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);
        context.dispose();

        context = getContext(c, javax.swing.plaf.synth.Region.SCROLL_BAR_THUMB, ENABLED);
        thumbStyle = javax.swing.plaf.synth.SynthLookAndFeel.updateStyle(context, this);
        context.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installListeners() {
        super.installListeners();
        scrollbar.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallListeners() {
        super.uninstallListeners();
        scrollbar.removePropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallDefaults(){
        javax.swing.plaf.synth.SynthContext context = getContext(scrollbar, ENABLED);
        style.uninstallDefaults(context);
        context.dispose();
        style = null;

        context = getContext(scrollbar, javax.swing.plaf.synth.Region.SCROLL_BAR_TRACK, ENABLED);
        trackStyle.uninstallDefaults(context);
        context.dispose();
        trackStyle = null;

        context = getContext(scrollbar, javax.swing.plaf.synth.Region.SCROLL_BAR_THUMB, ENABLED);
        thumbStyle.uninstallDefaults(context);
        context.dispose();
        thumbStyle = null;

        super.uninstallDefaults();
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

    private javax.swing.plaf.synth.SynthContext getContext(JComponent c, javax.swing.plaf.synth.Region region) {
        return getContext(c, region, getComponentState(c, region));
    }

    private javax.swing.plaf.synth.SynthContext getContext(JComponent c, javax.swing.plaf.synth.Region region, int state) {
        SynthStyle style = trackStyle;

        if (region == javax.swing.plaf.synth.Region.SCROLL_BAR_THUMB) {
            style = thumbStyle;
        }
        return javax.swing.plaf.synth.SynthContext.getContext(c, region, style, state);
    }

    private int getComponentState(JComponent c, javax.swing.plaf.synth.Region region) {
        if (region == javax.swing.plaf.synth.Region.SCROLL_BAR_THUMB && isThumbRollover() &&
                                                 c.isEnabled()) {
            return MOUSE_OVER;
        }
        return javax.swing.plaf.synth.SynthLookAndFeel.getComponentState(c);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean getSupportsAbsolutePositioning() {
        javax.swing.plaf.synth.SynthContext context = getContext(scrollbar);
        boolean value = style.getBoolean(context,
                      "ScrollBar.allowsAbsolutePositioning", false);
        context.dispose();
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

        javax.swing.plaf.synth.SynthLookAndFeel.update(context, g);
        context.getPainter().paintScrollBarBackground(context,
                          g, 0, 0, c.getWidth(), c.getHeight(),
                          scrollbar.getOrientation());
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
        javax.swing.plaf.synth.SynthContext subcontext = getContext(scrollbar,
                                             javax.swing.plaf.synth.Region.SCROLL_BAR_TRACK);
        paintTrack(subcontext, g, getTrackBounds());
        subcontext.dispose();

        subcontext = getContext(scrollbar, Region.SCROLL_BAR_THUMB);
        paintThumb(subcontext, g, getThumbBounds());
        subcontext.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(javax.swing.plaf.synth.SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintScrollBarBorder(context, g, x, y, w, h,
                                                  scrollbar.getOrientation());
    }

    /**
     * Paints the scrollbar track.
     *
     * @param context context for the component being painted
     * @param g {@code Graphics} object used for painting
     * @param trackBounds bounding box for the track
     */
    protected void paintTrack(javax.swing.plaf.synth.SynthContext context, Graphics g,
                              Rectangle trackBounds) {
        javax.swing.plaf.synth.SynthLookAndFeel.updateSubregion(context, g, trackBounds);
        context.getPainter().paintScrollBarTrackBackground(context, g, trackBounds.x,
                        trackBounds.y, trackBounds.width, trackBounds.height,
                        scrollbar.getOrientation());
        context.getPainter().paintScrollBarTrackBorder(context, g, trackBounds.x,
                        trackBounds.y, trackBounds.width, trackBounds.height,
                        scrollbar.getOrientation());
    }

    /**
     * Paints the scrollbar thumb.
     *
     * @param context context for the component being painted
     * @param g {@code Graphics} object used for painting
     * @param thumbBounds bounding box for the thumb
     */
    protected void paintThumb(SynthContext context, Graphics g,
                              Rectangle thumbBounds) {
        javax.swing.plaf.synth.SynthLookAndFeel.updateSubregion(context, g, thumbBounds);
        int orientation = scrollbar.getOrientation();
        context.getPainter().paintScrollBarThumbBackground(context, g, thumbBounds.x,
                        thumbBounds.y, thumbBounds.width, thumbBounds.height,
                        orientation);
        context.getPainter().paintScrollBarThumbBorder(context, g, thumbBounds.x,
                        thumbBounds.y, thumbBounds.width, thumbBounds.height,
                        orientation);
    }

    /**
     * A vertical scrollbar's preferred width is the maximum of
     * preferred widths of the (non <code>null</code>)
     * increment/decrement buttons,
     * and the minimum width of the thumb. The preferred height is the
     * sum of the preferred heights of the same parts.  The basis for
     * the preferred size of a horizontal scrollbar is similar.
     * <p>
     * The <code>preferredSize</code> is only computed once, subsequent
     * calls to this method just return a cached size.
     *
     * @param c the <code>JScrollBar</code> that's delegating this method to us
     * @return the preferred size of a Basic JScrollBar
     * @see #getMaximumSize
     * @see #getMinimumSize
     */
    @Override
    public Dimension getPreferredSize(JComponent c) {
        Insets insets = c.getInsets();
        return (scrollbar.getOrientation() == JScrollBar.VERTICAL)
            ? new Dimension(scrollBarWidth + insets.left + insets.right, 48)
            : new Dimension(48, scrollBarWidth + insets.top + insets.bottom);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Dimension getMinimumThumbSize() {
        if (!validMinimumThumbSize) {
            if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                minimumThumbSize.width = scrollBarWidth;
                minimumThumbSize.height = 7;
            } else {
                minimumThumbSize.width = 7;
                minimumThumbSize.height = scrollBarWidth;
            }
        }
        return minimumThumbSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JButton createDecreaseButton(int orientation)  {
        javax.swing.plaf.synth.SynthArrowButton synthArrowButton = new javax.swing.plaf.synth.SynthArrowButton(orientation) {
            @Override
            public boolean contains(int x, int y) {
                if (decrGap < 0) { //there is an overlap between the track and button
                    int width = getWidth();
                    int height = getHeight();
                    if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                        //adjust the height by decrGap
                        //Note: decrGap is negative!
                        height += decrGap;
                    } else {
                        //adjust the width by decrGap
                        //Note: decrGap is negative!
                        width += decrGap;
                    }
                    return (x >= 0) && (x < width) && (y >= 0) && (y < height);
                }
                return super.contains(x, y);
            }
        };
        synthArrowButton.setName("ScrollBar.button");
        return synthArrowButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JButton createIncreaseButton(int orientation)  {
        javax.swing.plaf.synth.SynthArrowButton synthArrowButton = new javax.swing.plaf.synth.SynthArrowButton(orientation) {
            @Override
            public boolean contains(int x, int y) {
                if (incrGap < 0) { //there is an overlap between the track and button
                    int width = getWidth();
                    int height = getHeight();
                    if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
                        //adjust the height and y by incrGap
                        //Note: incrGap is negative!
                        height += incrGap;
                        y += incrGap;
                    } else {
                        //adjust the width and x by incrGap
                        //Note: incrGap is negative!
                        width += incrGap;
                        x += incrGap;
                    }
                    return (x >= 0) && (x < width) && (y >= 0) && (y < height);
                }
                return super.contains(x, y);
            }
        };
        synthArrowButton.setName("ScrollBar.button");
        return synthArrowButton;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setThumbRollover(boolean active) {
        if (isThumbRollover() != active) {
            scrollbar.repaint(getThumbBounds());
            super.setThumbRollover(active);
        }
    }

    private void updateButtonDirections() {
        int orient = scrollbar.getOrientation();
        if (scrollbar.getComponentOrientation().isLeftToRight()) {
            ((javax.swing.plaf.synth.SynthArrowButton)incrButton).setDirection(
                        orient == HORIZONTAL? EAST : SOUTH);
            ((javax.swing.plaf.synth.SynthArrowButton)decrButton).setDirection(
                        orient == HORIZONTAL? WEST : NORTH);
        }
        else {
            ((javax.swing.plaf.synth.SynthArrowButton)incrButton).setDirection(
                        orient == HORIZONTAL? WEST : SOUTH);
            ((javax.swing.plaf.synth.SynthArrowButton)decrButton).setDirection(
                        orient == HORIZONTAL ? EAST : NORTH);
        }
    }

    //
    // PropertyChangeListener
    //
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();

        if (SynthLookAndFeel.shouldUpdateStyle(e)) {
            updateStyle((JScrollBar)e.getSource());
        }

        if ("orientation" == propertyName) {
            updateButtonDirections();
        }
        else if ("componentOrientation" == propertyName) {
            updateButtonDirections();
        }
    }
}
