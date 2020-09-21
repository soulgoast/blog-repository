/*
 * Copyright (c) 2003, 2005, Oracle and/or its affiliates. All rights reserved.
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
import goodman.javax.swing.plaf.basic.*;
import goodman.javax.swing.plaf.synth.Region;
import goodman.javax.swing.plaf.synth.SynthArrowButton;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthLookAndFeel;
import goodman.javax.swing.plaf.synth.SynthPainter;
import goodman.javax.swing.plaf.synth.SynthSplitPaneUI;

import sun.swing.DefaultLookup;

/**
 * Synth's SplitPaneDivider.
 *
 * @author Scott Violet
 */
class SynthSplitPaneDivider extends BasicSplitPaneDivider {
    public SynthSplitPaneDivider(BasicSplitPaneUI ui) {
        super(ui);
    }

    protected void setMouseOver(boolean mouseOver) {
        if (isMouseOver() != mouseOver) {
            repaint();
        }
        super.setMouseOver(mouseOver);
    }

    public void propertyChange(PropertyChangeEvent e) {
        super.propertyChange(e);
        if (e.getSource() == splitPane) {
            if (e.getPropertyName() == JSplitPane.ORIENTATION_PROPERTY) {
                if (leftButton instanceof javax.swing.plaf.synth.SynthArrowButton) {
                    ((javax.swing.plaf.synth.SynthArrowButton)leftButton).setDirection(
                                       mapDirection(true));
                }
                if (rightButton instanceof javax.swing.plaf.synth.SynthArrowButton) {
                    ((javax.swing.plaf.synth.SynthArrowButton)rightButton).setDirection(
                                       mapDirection(false));
                }
            }
        }
    }

    public void paint(Graphics g) {
        Graphics g2 = g.create();

        SynthContext context = ((SynthSplitPaneUI)splitPaneUI).getContext(
                               splitPane, Region.SPLIT_PANE_DIVIDER);
        Rectangle bounds = getBounds();
        bounds.x = bounds.y = 0;
        SynthLookAndFeel.updateSubregion(context, g, bounds);
        context.getPainter().paintSplitPaneDividerBackground(context,
                          g, 0, 0, bounds.width, bounds.height,
                          splitPane.getOrientation());

        SynthPainter foreground = null;

        context.getPainter().paintSplitPaneDividerForeground(context, g, 0, 0,
                getWidth(), getHeight(), splitPane.getOrientation());
        context.dispose();

        // super.paint(g2);
        for (int counter = 0; counter < getComponentCount(); counter++) {
            Component child = getComponent(counter);
            Rectangle childBounds = child.getBounds();
            Graphics childG = g.create(childBounds.x, childBounds.y,
                                       childBounds.width, childBounds.height);
            child.paint(childG);
            childG.dispose();
        }
        g2.dispose();
    }

    private int mapDirection(boolean isLeft) {
        if (isLeft) {
            if (splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT){
                return SwingConstants.WEST;
            }
            return SwingConstants.NORTH;
        }
        if (splitPane.getOrientation() == JSplitPane.HORIZONTAL_SPLIT){
            return SwingConstants.EAST;
        }
        return SwingConstants.SOUTH;
    }


    /**
     * Creates and return an instance of JButton that can be used to
     * collapse the left component in the split pane.
     */
    protected JButton createLeftOneTouchButton() {
        javax.swing.plaf.synth.SynthArrowButton b = new javax.swing.plaf.synth.SynthArrowButton(SwingConstants.NORTH);
        int oneTouchSize = lookupOneTouchSize();

        b.setName("SplitPaneDivider.leftOneTouchButton");
        b.setMinimumSize(new Dimension(oneTouchSize, oneTouchSize));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setRequestFocusEnabled(false);
        b.setDirection(mapDirection(true));
        return b;
    }

    private int lookupOneTouchSize() {
        return DefaultLookup.getInt(splitPaneUI.getSplitPane(), splitPaneUI,
              "SplitPaneDivider.oneTouchButtonSize", ONE_TOUCH_SIZE);
    }

    /**
     * Creates and return an instance of JButton that can be used to
     * collapse the right component in the split pane.
     */
    protected JButton createRightOneTouchButton() {
        javax.swing.plaf.synth.SynthArrowButton b = new javax.swing.plaf.synth.SynthArrowButton(SwingConstants.NORTH);
        int oneTouchSize = lookupOneTouchSize();

        b.setName("SplitPaneDivider.rightOneTouchButton");
        b.setMinimumSize(new Dimension(oneTouchSize, oneTouchSize));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setRequestFocusEnabled(false);
        b.setDirection(mapDirection(false));
        return b;
    }
}
