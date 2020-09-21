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
import goodman.javax.swing.border.*;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.basic.*;
import goodman.javax.swing.plaf.synth.SynthBorder;
import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.javax.swing.plaf.synth.SynthLabelUI;
import goodman.javax.swing.plaf.synth.SynthLookAndFeel;
import goodman.javax.swing.plaf.synth.SynthStyle;
import goodman.javax.swing.plaf.synth.SynthUI;
import goodman.javax.swing.table.*;
import sun.swing.table.*;

/**
 * Provides the Synth L&amp;F UI delegate for
 * {@link JTableHeader}.
 *
 * @author Alan Chung
 * @author Philip Milne
 * @since 1.7
 */
public class SynthTableHeaderUI extends BasicTableHeaderUI
                                implements PropertyChangeListener, SynthUI {

//
// Instance Variables
//

    private TableCellRenderer prevRenderer = null;

    private javax.swing.plaf.synth.SynthStyle style;

    /**
     * Creates a new UI object for the given component.
     *
     * @param h component to create UI object for
     * @return the UI object
     */
    public static ComponentUI createUI(JComponent h) {
        return new SynthTableHeaderUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void installDefaults() {
        prevRenderer = header.getDefaultRenderer();
        if (prevRenderer instanceof UIResource) {
            header.setDefaultRenderer(new HeaderRenderer());
        }
        updateStyle(header);
    }

    private void updateStyle(JTableHeader c) {
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
        header.addPropertyChangeListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallDefaults() {
        if (header.getDefaultRenderer() instanceof HeaderRenderer) {
            header.setDefaultRenderer(prevRenderer);
        }

        javax.swing.plaf.synth.SynthContext context = getContext(header, ENABLED);

        style.uninstallDefaults(context);
        context.dispose();
        style = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void uninstallListeners() {
        header.removePropertyChangeListener(this);
        super.uninstallListeners();
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
        context.getPainter().paintTableHeaderBackground(context,
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
     * Paints the specified component.
     *
     * @param context context for the component being painted
     * @param g the {@code Graphics} object used for painting
     * @see #update(Graphics,JComponent)
     */
    protected void paint(javax.swing.plaf.synth.SynthContext context, Graphics g) {
        super.paint(g, context.getComponent());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void paintBorder(javax.swing.plaf.synth.SynthContext context, Graphics g, int x,
                            int y, int w, int h) {
        context.getPainter().paintTableHeaderBorder(context, g, x, y, w, h);
    }
//
// SynthUI
//
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

    /**
     * {@inheritDoc}
     */
    @Override
    protected void rolloverColumnUpdated(int oldColumn, int newColumn) {
        header.repaint(header.getHeaderRect(oldColumn));
        header.repaint(header.getHeaderRect(newColumn));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (javax.swing.plaf.synth.SynthLookAndFeel.shouldUpdateStyle(evt)) {
            updateStyle((JTableHeader)evt.getSource());
        }
    }

    private class HeaderRenderer extends DefaultTableCellHeaderRenderer {
        HeaderRenderer() {
            setHorizontalAlignment(JLabel.LEADING);
            setName("TableHeader.renderer");
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row, int column) {

            boolean hasRollover = (column == getRolloverColumn());
            if (isSelected || hasRollover || hasFocus) {
                javax.swing.plaf.synth.SynthLookAndFeel.setSelectedUI((javax.swing.plaf.synth.SynthLabelUI) javax.swing.plaf.synth.SynthLookAndFeel.
                             getUIOfType(getUI(), SynthLabelUI.class),
                             isSelected, hasFocus, table.isEnabled(),
                             hasRollover);
            } else {
                SynthLookAndFeel.resetSelectedUI();
            }

            //stuff a variable into the client property of this renderer indicating the sort order,
            //so that different rendering can be done for the header based on sorted state.
            RowSorter rs = table == null ? null : table.getRowSorter();
            java.util.List<? extends RowSorter.SortKey> sortKeys = rs == null ? null : rs.getSortKeys();
            if (sortKeys != null && sortKeys.size() > 0 && sortKeys.get(0).getColumn() ==
                    table.convertColumnIndexToModel(column)) {
                switch(sortKeys.get(0).getSortOrder()) {
                    case ASCENDING:
                        putClientProperty("Table.sortOrder", "ASCENDING");
                        break;
                    case DESCENDING:
                        putClientProperty("Table.sortOrder", "DESCENDING");
                        break;
                    case UNSORTED:
                        putClientProperty("Table.sortOrder", "UNSORTED");
                        break;
                    default:
                        throw new AssertionError("Cannot happen");
                }
            } else {
                putClientProperty("Table.sortOrder", "UNSORTED");
            }

            super.getTableCellRendererComponent(table, value, isSelected,
                                                hasFocus, row, column);

            return this;
        }

        @Override
        public void setBorder(Border border) {
            if (border instanceof javax.swing.plaf.synth.SynthBorder) {
                super.setBorder(border);
            }
        }
    }
}
