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

import goodman.javax.swing.plaf.synth.SynthContext;
import goodman.java.awt.*;

/**
 * <code>SynthPainter</code> is used for painting portions of
 * <code>JComponent</code>s. At a minimum each <code>JComponent</code>
 * has two paint methods: one for the border and one for the background. Some
 * <code>JComponent</code>s have more than one <code>Region</code>, and as
 * a consequence more paint methods.
 * <p>
 * Instances of <code>SynthPainter</code> are obtained from the
 * {@link javax.swing.plaf.synth.SynthStyle#getPainter} method.
 * <p>
 * You typically supply a <code>SynthPainter</code> by way of Synth's
 * <a href="doc-files/synthFileFormat.html">file</a> format. The following
 * example registers a painter for all <code>JButton</code>s that will
 * render the image <code>myImage.png</code>:
 * <pre>
 *  &lt;style id="buttonStyle"&gt;
 *    &lt;imagePainter path="myImage.png" sourceInsets="2 2 2 2"
 *                  paintCenter="true" stretch="true"/&gt;
 *    &lt;insets top="2" bottom="2" left="2" right="2"/&gt;
 *  &lt;/style&gt;
 *  &lt;bind style="buttonStyle" type="REGION" key="button"/&gt;
 *</pre>
 * <p>
 * <code>SynthPainter</code> is abstract in so far as it does no painting,
 * all the methods
 * are empty. While none of these methods are typed to throw an exception,
 * subclasses can assume that valid arguments are passed in, and if not
 * they can throw a <code>NullPointerException</code> or
 * <code>IllegalArgumentException</code> in response to invalid arguments.
 *
 * @since 1.5
 * @author Scott Violet
 */
public abstract class SynthPainter {
    /**
     * Used to avoid null painter checks everywhere.
     */
    static SynthPainter NULL_PAINTER = new SynthPainter() {};


    /**
     * Paints the background of an arrow button. Arrow buttons are created by
     * some components, such as <code>JScrollBar</code>.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintArrowButtonBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h) {
    }

    /**
     * Paints the border of an arrow button. Arrow buttons are created by
     * some components, such as <code>JScrollBar</code>.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintArrowButtonBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the foreground of an arrow button. This method is responsible
     * for drawing a graphical representation of a direction, typically
     * an arrow. Arrow buttons are created by
     * some components, such as <code>JScrollBar</code>
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param direction One of SwingConstants.NORTH, SwingConstants.SOUTH
     *                  SwingConstants.EAST or SwingConstants.WEST
     */
    public void paintArrowButtonForeground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h,
                                           int direction) {
    }

    /**
     * Paints the background of a button.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintButtonBackground(javax.swing.plaf.synth.SynthContext context,
                                      Graphics g, int x, int y,
                                      int w, int h) {
    }

    /**
     * Paints the border of a button.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintButtonBorder(javax.swing.plaf.synth.SynthContext context,
                                  Graphics g, int x, int y,
                                  int w, int h) {
    }

    /**
     * Paints the background of a check box menu item.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintCheckBoxMenuItemBackground(javax.swing.plaf.synth.SynthContext context,
                                                Graphics g, int x, int y,
                                                int w, int h) {
    }

    /**
     * Paints the border of a check box menu item.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintCheckBoxMenuItemBorder(javax.swing.plaf.synth.SynthContext context,
                                            Graphics g, int x, int y,
                                            int w, int h) {
    }

    /**
     * Paints the background of a check box.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintCheckBoxBackground(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the border of a check box.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintCheckBoxBorder(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the background of a color chooser.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintColorChooserBackground(javax.swing.plaf.synth.SynthContext context,
                                            Graphics g, int x, int y,
                                            int w, int h) {
    }

    /**
     * Paints the border of a color chooser.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintColorChooserBorder(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the background of a combo box.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintComboBoxBackground(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the border of a combo box.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintComboBoxBorder(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the background of a desktop icon.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintDesktopIconBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h) {
    }

    /**
     * Paints the border of a desktop icon.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintDesktopIconBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the background of a desktop pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintDesktopPaneBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h) {
    }

    /**
     * Paints the background of a desktop pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintDesktopPaneBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the background of an editor pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintEditorPaneBackground(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h) {
    }

    /**
     * Paints the border of an editor pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintEditorPaneBorder(javax.swing.plaf.synth.SynthContext context,
                                      Graphics g, int x, int y,
                                      int w, int h) {
    }

    /**
     * Paints the background of a file chooser.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintFileChooserBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h) {
    }

    /**
     * Paints the border of a file chooser.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintFileChooserBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the background of a formatted text field.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintFormattedTextFieldBackground(javax.swing.plaf.synth.SynthContext context,
                                                  Graphics g, int x, int y,
                                                  int w, int h) {
    }

    /**
     * Paints the border of a formatted text field.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintFormattedTextFieldBorder(javax.swing.plaf.synth.SynthContext context,
                                              Graphics g, int x, int y,
                                              int w, int h) {
    }

    /**
     * Paints the background of an internal frame title pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintInternalFrameTitlePaneBackground(javax.swing.plaf.synth.SynthContext context,
                                                      Graphics g, int x, int y,
                                                      int w, int h) {
    }

    /**
     * Paints the border of an internal frame title pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintInternalFrameTitlePaneBorder(javax.swing.plaf.synth.SynthContext context,
                                                  Graphics g, int x, int y,
                                                  int w, int h) {
    }

    /**
     * Paints the background of an internal frame.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintInternalFrameBackground(javax.swing.plaf.synth.SynthContext context,
                                             Graphics g, int x, int y,
                                             int w, int h) {
    }

    /**
     * Paints the border of an internal frame.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintInternalFrameBorder(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h) {
    }

    /**
     * Paints the background of a label.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintLabelBackground(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h) {
    }

    /**
     * Paints the border of a label.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintLabelBorder(javax.swing.plaf.synth.SynthContext context,
                                 Graphics g, int x, int y,
                                 int w, int h) {
    }

    /**
     * Paints the background of a list.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintListBackground(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the border of a list.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintListBorder(javax.swing.plaf.synth.SynthContext context,
                                Graphics g, int x, int y,
                                int w, int h) {
    }

    /**
     * Paints the background of a menu bar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintMenuBarBackground(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the border of a menu bar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintMenuBarBorder(javax.swing.plaf.synth.SynthContext context,
                                   Graphics g, int x, int y,
                                   int w, int h) {
    }

    /**
     * Paints the background of a menu item.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintMenuItemBackground(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the border of a menu item.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintMenuItemBorder(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the background of a menu.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintMenuBackground(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the border of a menu.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintMenuBorder(javax.swing.plaf.synth.SynthContext context,
                                Graphics g, int x, int y,
                                int w, int h) {
    }

    /**
     * Paints the background of an option pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintOptionPaneBackground(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h) {
    }

    /**
     * Paints the border of an option pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintOptionPaneBorder(javax.swing.plaf.synth.SynthContext context,
                                      Graphics g, int x, int y,
                                      int w, int h) {
    }

    /**
     * Paints the background of a panel.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintPanelBackground(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h) {
    }

    /**
     * Paints the border of a panel.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintPanelBorder(javax.swing.plaf.synth.SynthContext context,
                                 Graphics g, int x, int y,
                                 int w, int h) {
    }

    /**
     * Paints the background of a password field.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintPasswordFieldBackground(javax.swing.plaf.synth.SynthContext context,
                                             Graphics g, int x, int y,
                                             int w, int h) {
    }

    /**
     * Paints the border of a password field.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintPasswordFieldBorder(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h) {
    }

    /**
     * Paints the background of a popup menu.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintPopupMenuBackground(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h) {
    }

    /**
     * Paints the border of a popup menu.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintPopupMenuBorder(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h) {
    }

    /**
     * Paints the background of a progress bar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintProgressBarBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h) {
    }

    /**
     * Paints the background of a progress bar. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation one of <code>JProgressBar.HORIZONTAL</code> or
     *                    <code>JProgressBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintProgressBarBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h, int orientation) {
        paintProgressBarBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of a progress bar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintProgressBarBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the border of a progress bar. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation one of <code>JProgressBar.HORIZONTAL</code> or
     *                    <code>JProgressBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintProgressBarBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h, int orientation) {
        paintProgressBarBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the foreground of a progress bar. is responsible for
     * providing an indication of the progress of the progress bar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation one of <code>JProgressBar.HORIZONTAL</code> or
     *                    <code>JProgressBar.VERTICAL</code>
     */
    public void paintProgressBarForeground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h, int orientation) {
    }

    /**
     * Paints the background of a radio button menu item.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintRadioButtonMenuItemBackground(javax.swing.plaf.synth.SynthContext context,
                                                   Graphics g, int x, int y,
                                                   int w, int h) {
    }

    /**
     * Paints the border of a radio button menu item.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintRadioButtonMenuItemBorder(javax.swing.plaf.synth.SynthContext context,
                                               Graphics g, int x, int y,
                                               int w, int h) {
    }

    /**
     * Paints the background of a radio button.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintRadioButtonBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h) {
    }

    /**
     * Paints the border of a radio button.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintRadioButtonBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the background of a root pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintRootPaneBackground(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the border of a root pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintRootPaneBorder(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the background of a scrollbar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintScrollBarBackground(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h) {
    }

    /**
     * Paints the background of a scrollbar. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation Orientation of the JScrollBar, one of
     *                    <code>JScrollBar.HORIZONTAL</code> or
     *                    <code>JScrollBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintScrollBarBackground(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h, int orientation) {
        paintScrollBarBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of a scrollbar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintScrollBarBorder(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h) {
    }

    /**
     * Paints the border of a scrollbar. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation Orientation of the JScrollBar, one of
     *                    <code>JScrollBar.HORIZONTAL</code> or
     *                    <code>JScrollBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintScrollBarBorder(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h, int orientation) {
        paintScrollBarBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the background of the thumb of a scrollbar. The thumb provides
     * a graphical indication as to how much of the Component is visible in a
     * <code>JScrollPane</code>.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation Orientation of the JScrollBar, one of
     *                    <code>JScrollBar.HORIZONTAL</code> or
     *                    <code>JScrollBar.VERTICAL</code>
     */
    public void paintScrollBarThumbBackground(javax.swing.plaf.synth.SynthContext context,
                                              Graphics g, int x, int y,
                                              int w, int h, int orientation) {
    }

    /**
     * Paints the border of the thumb of a scrollbar. The thumb provides
     * a graphical indication as to how much of the Component is visible in a
     * <code>JScrollPane</code>.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation Orientation of the JScrollBar, one of
     *                    <code>JScrollBar.HORIZONTAL</code> or
     *                    <code>JScrollBar.VERTICAL</code>
     */
    public void paintScrollBarThumbBorder(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h, int orientation) {
    }

    /**
     * Paints the background of the track of a scrollbar. The track contains
     * the thumb.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintScrollBarTrackBackground(javax.swing.plaf.synth.SynthContext context,
                                              Graphics g, int x, int y,
                                              int w, int h) {
    }

    /**
     * Paints the background of the track of a scrollbar. The track contains
     * the thumb. This implementation invokes the method of the same name without
     * the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation Orientation of the JScrollBar, one of
     *                    <code>JScrollBar.HORIZONTAL</code> or
     *                    <code>JScrollBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintScrollBarTrackBackground(javax.swing.plaf.synth.SynthContext context,
                                              Graphics g, int x, int y,
                                              int w, int h, int orientation) {
        paintScrollBarTrackBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of the track of a scrollbar. The track contains
     * the thumb.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintScrollBarTrackBorder(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h) {
    }

    /**
     * Paints the border of the track of a scrollbar. The track contains
     * the thumb. This implementation invokes the method of the same name without
     * the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation Orientation of the JScrollBar, one of
     *                    <code>JScrollBar.HORIZONTAL</code> or
     *                    <code>JScrollBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintScrollBarTrackBorder(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h, int orientation) {
        paintScrollBarTrackBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the background of a scroll pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintScrollPaneBackground(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h) {
    }

    /**
     * Paints the border of a scroll pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintScrollPaneBorder(javax.swing.plaf.synth.SynthContext context,
                                      Graphics g, int x, int y,
                                      int w, int h) {
    }

    /**
     * Paints the background of a separator.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSeparatorBackground(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h) {
    }

    /**
     * Paints the background of a separator. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSeparator.HORIZONTAL</code> or
     *                           <code>JSeparator.VERTICAL</code>
     * @since 1.6
     */
    public void paintSeparatorBackground(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h, int orientation) {
        paintSeparatorBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of a separator.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSeparatorBorder(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h) {
    }

    /**
     * Paints the border of a separator. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSeparator.HORIZONTAL</code> or
     *                           <code>JSeparator.VERTICAL</code>
     * @since 1.6
     */
    public void paintSeparatorBorder(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h, int orientation) {
        paintSeparatorBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the foreground of a separator.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSeparator.HORIZONTAL</code> or
     *                           <code>JSeparator.VERTICAL</code>
     */
    public void paintSeparatorForeground(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h, int orientation) {
    }

    /**
     * Paints the background of a slider.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSliderBackground(javax.swing.plaf.synth.SynthContext context,
                                      Graphics g, int x, int y,
                                      int w, int h) {
    }

    /**
     * Paints the background of a slider. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     * @since 1.6
     */
    public void paintSliderBackground(javax.swing.plaf.synth.SynthContext context,
                                      Graphics g, int x, int y,
                                      int w, int h, int orientation) {
        paintSliderBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of a slider.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSliderBorder(javax.swing.plaf.synth.SynthContext context,
                                  Graphics g, int x, int y,
                                  int w, int h) {
    }

    /**
     * Paints the border of a slider. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     * @since 1.6
     */
    public void paintSliderBorder(javax.swing.plaf.synth.SynthContext context,
                                  Graphics g, int x, int y,
                                  int w, int h, int orientation) {
        paintSliderBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the background of the thumb of a slider.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     */
    public void paintSliderThumbBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h, int orientation) {
    }

    /**
     * Paints the border of the thumb of a slider.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     */
    public void paintSliderThumbBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h, int orientation) {
    }

    /**
     * Paints the background of the track of a slider.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSliderTrackBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h) {
    }

    /**
     * Paints the background of the track of a slider. This implementation invokes
     * the method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     * @since 1.6
     */
    public void paintSliderTrackBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h, int orientation) {
        paintSliderTrackBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of the track of a slider.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSliderTrackBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the border of the track of a slider. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSlider.HORIZONTAL</code> or
     *                           <code>JSlider.VERTICAL</code>
     * @since 1.6
     */
    public void paintSliderTrackBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h, int orientation) {
        paintSliderTrackBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the background of a spinner.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSpinnerBackground(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the border of a spinner.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSpinnerBorder(javax.swing.plaf.synth.SynthContext context,
                                   Graphics g, int x, int y,
                                   int w, int h) {
    }

    /**
     * Paints the background of the divider of a split pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSplitPaneDividerBackground(javax.swing.plaf.synth.SynthContext context,
                                                Graphics g, int x, int y,
                                                int w, int h) {
    }

    /**
     * Paints the background of the divider of a split pane. This implementation
     * invokes the method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSplitPane.HORIZONTAL_SPLIT</code> or
     *                           <code>JSplitPane.VERTICAL_SPLIT</code>
     * @since 1.6
     */
    public void paintSplitPaneDividerBackground(javax.swing.plaf.synth.SynthContext context,
                                                Graphics g, int x, int y,
                                                int w, int h, int orientation) {
        paintSplitPaneDividerBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the foreground of the divider of a split pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSplitPane.HORIZONTAL_SPLIT</code> or
     *                           <code>JSplitPane.VERTICAL_SPLIT</code>
     */
    public void paintSplitPaneDividerForeground(javax.swing.plaf.synth.SynthContext context,
                                                Graphics g, int x, int y,
                                                int w, int h, int orientation) {
    }

    /**
     * Paints the divider, when the user is dragging the divider, of a
     * split pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JSplitPane.HORIZONTAL_SPLIT</code> or
     *                           <code>JSplitPane.VERTICAL_SPLIT</code>
     */
    public void paintSplitPaneDragDivider(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h, int orientation) {
    }

    /**
     * Paints the background of a split pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSplitPaneBackground(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h) {
    }

    /**
     * Paints the border of a split pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintSplitPaneBorder(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h) {
    }

    /**
     * Paints the background of a tabbed pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTabbedPaneBackground(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h) {
    }

    /**
     * Paints the border of a tabbed pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTabbedPaneBorder(javax.swing.plaf.synth.SynthContext context,
                                      Graphics g, int x, int y,
                                      int w, int h) {
    }

    /**
     * Paints the background of the area behind the tabs of a tabbed pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTabbedPaneTabAreaBackground(javax.swing.plaf.synth.SynthContext context,
                                                 Graphics g, int x, int y,
                                                 int w, int h) {
    }

    /**
     * Paints the background of the area behind the tabs of a tabbed pane.
     * This implementation invokes the method of the same name without the
     * orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JTabbedPane.TOP</code>,
     *                    <code>JTabbedPane.LEFT</code>,
     *                    <code>JTabbedPane.BOTTOM</code>, or
     *                    <code>JTabbedPane.RIGHT</code>
     * @since 1.6
     */
    public void paintTabbedPaneTabAreaBackground(javax.swing.plaf.synth.SynthContext context,
                                                 Graphics g, int x, int y,
                                                 int w, int h, int orientation) {
        paintTabbedPaneTabAreaBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of the area behind the tabs of a tabbed pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTabbedPaneTabAreaBorder(javax.swing.plaf.synth.SynthContext context,
                                             Graphics g, int x, int y,
                                             int w, int h) {
    }

    /**
     * Paints the border of the area behind the tabs of a tabbed pane. This
     * implementation invokes the method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JTabbedPane.TOP</code>,
     *                    <code>JTabbedPane.LEFT</code>,
     *                    <code>JTabbedPane.BOTTOM</code>, or
     *                    <code>JTabbedPane.RIGHT</code>
     * @since 1.6
     */
    public void paintTabbedPaneTabAreaBorder(javax.swing.plaf.synth.SynthContext context,
                                             Graphics g, int x, int y,
                                             int w, int h, int orientation) {
        paintTabbedPaneTabAreaBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the background of a tab of a tabbed pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param tabIndex Index of tab being painted.
     */
    public void paintTabbedPaneTabBackground(javax.swing.plaf.synth.SynthContext context, Graphics g,
                                             int x, int y, int w, int h,
                                             int tabIndex) {
    }

    /**
     * Paints the background of a tab of a tabbed pane. This implementation
     * invokes the method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param tabIndex Index of tab being painted.
     * @param orientation One of <code>JTabbedPane.TOP</code>,
     *                    <code>JTabbedPane.LEFT</code>,
     *                    <code>JTabbedPane.BOTTOM</code>, or
     *                    <code>JTabbedPane.RIGHT</code>
     * @since 1.6
     */
    public void paintTabbedPaneTabBackground(javax.swing.plaf.synth.SynthContext context, Graphics g,
                                             int x, int y, int w, int h,
                                             int tabIndex, int orientation) {
        paintTabbedPaneTabBackground(context, g, x, y, w, h, tabIndex);
    }

    /**
     * Paints the border of a tab of a tabbed pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param tabIndex Index of tab being painted.
     */
    public void paintTabbedPaneTabBorder(javax.swing.plaf.synth.SynthContext context, Graphics g,
                                         int x, int y, int w, int h,
                                         int tabIndex) {
    }

    /**
     * Paints the border of a tab of a tabbed pane. This implementation invokes
     * the method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param tabIndex Index of tab being painted.
     * @param orientation One of <code>JTabbedPane.TOP</code>,
     *                    <code>JTabbedPane.LEFT</code>,
     *                    <code>JTabbedPane.BOTTOM</code>, or
     *                    <code>JTabbedPane.RIGHT</code>
     * @since 1.6
     */
    public void paintTabbedPaneTabBorder(javax.swing.plaf.synth.SynthContext context, Graphics g,
                                         int x, int y, int w, int h,
                                         int tabIndex, int orientation) {
        paintTabbedPaneTabBorder(context, g, x, y, w, h, tabIndex);
    }

    /**
     * Paints the background of the area that contains the content of the
     * selected tab of a tabbed pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTabbedPaneContentBackground(javax.swing.plaf.synth.SynthContext context,
                                                 Graphics g, int x, int y, int w,
                                                 int h) {
    }

    /**
     * Paints the border of the area that contains the content of the
     * selected tab of a tabbed pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTabbedPaneContentBorder(javax.swing.plaf.synth.SynthContext context, Graphics g,
                                             int x, int y, int w, int h) {
    }

    /**
     * Paints the background of the header of a table.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTableHeaderBackground(javax.swing.plaf.synth.SynthContext context,
                                           Graphics g, int x, int y,
                                           int w, int h) {
    }

    /**
     * Paints the border of the header of a table.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTableHeaderBorder(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the background of a table.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTableBackground(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h) {
    }

    /**
     * Paints the border of a table.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTableBorder(javax.swing.plaf.synth.SynthContext context,
                                 Graphics g, int x, int y,
                                 int w, int h) {
    }

    /**
     * Paints the background of a text area.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTextAreaBackground(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the border of a text area.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTextAreaBorder(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the background of a text pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTextPaneBackground(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the border of a text pane.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTextPaneBorder(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the background of a text field.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTextFieldBackground(javax.swing.plaf.synth.SynthContext context,
                                         Graphics g, int x, int y,
                                         int w, int h) {
    }

    /**
     * Paints the border of a text field.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTextFieldBorder(javax.swing.plaf.synth.SynthContext context,
                                     Graphics g, int x, int y,
                                     int w, int h) {
    }

    /**
     * Paints the background of a toggle button.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToggleButtonBackground(javax.swing.plaf.synth.SynthContext context,
                                            Graphics g, int x, int y,
                                            int w, int h) {
    }

    /**
     * Paints the border of a toggle button.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToggleButtonBorder(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the background of a tool bar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToolBarBackground(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the background of a tool bar. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JToolBar.HORIZONTAL</code> or
     *                           <code>JToolBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintToolBarBackground(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h, int orientation) {
        paintToolBarBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of a tool bar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToolBarBorder(javax.swing.plaf.synth.SynthContext context,
                                   Graphics g, int x, int y,
                                   int w, int h) {
    }

    /**
     * Paints the border of a tool bar. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JToolBar.HORIZONTAL</code> or
     *                           <code>JToolBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintToolBarBorder(javax.swing.plaf.synth.SynthContext context,
                                   Graphics g, int x, int y,
                                   int w, int h, int orientation) {
        paintToolBarBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the background of the tool bar's content area.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToolBarContentBackground(javax.swing.plaf.synth.SynthContext context,
                                              Graphics g, int x, int y,
                                              int w, int h) {
    }

    /**
     * Paints the background of the tool bar's content area. This implementation
     * invokes the method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JToolBar.HORIZONTAL</code> or
     *                           <code>JToolBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintToolBarContentBackground(javax.swing.plaf.synth.SynthContext context,
                                              Graphics g, int x, int y,
                                              int w, int h, int orientation) {
        paintToolBarContentBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of the content area of a tool bar.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToolBarContentBorder(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h) {
    }

    /**
     * Paints the border of the content area of a tool bar. This implementation
     * invokes the method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JToolBar.HORIZONTAL</code> or
     *                           <code>JToolBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintToolBarContentBorder(javax.swing.plaf.synth.SynthContext context,
                                          Graphics g, int x, int y,
                                          int w, int h, int orientation) {
        paintToolBarContentBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the background of the window containing the tool bar when it
     * has been detached from its primary frame.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToolBarDragWindowBackground(javax.swing.plaf.synth.SynthContext context,
                                                 Graphics g, int x, int y,
                                                 int w, int h) {
    }

    /**
     * Paints the background of the window containing the tool bar when it
     * has been detached from its primary frame. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JToolBar.HORIZONTAL</code> or
     *                           <code>JToolBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintToolBarDragWindowBackground(javax.swing.plaf.synth.SynthContext context,
                                                 Graphics g, int x, int y,
                                                 int w, int h, int orientation) {
        paintToolBarDragWindowBackground(context, g, x, y, w, h);
    }

    /**
     * Paints the border of the window containing the tool bar when it
     * has been detached from it's primary frame.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToolBarDragWindowBorder(javax.swing.plaf.synth.SynthContext context,
                                             Graphics g, int x, int y,
                                             int w, int h) {
    }

    /**
     * Paints the border of the window containing the tool bar when it
     * has been detached from it's primary frame. This implementation invokes the
     * method of the same name without the orientation.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     * @param orientation One of <code>JToolBar.HORIZONTAL</code> or
     *                           <code>JToolBar.VERTICAL</code>
     * @since 1.6
     */
    public void paintToolBarDragWindowBorder(javax.swing.plaf.synth.SynthContext context,
                                             Graphics g, int x, int y,
                                             int w, int h, int orientation) {
        paintToolBarDragWindowBorder(context, g, x, y, w, h);
    }

    /**
     * Paints the background of a tool tip.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToolTipBackground(javax.swing.plaf.synth.SynthContext context,
                                       Graphics g, int x, int y,
                                       int w, int h) {
    }

    /**
     * Paints the border of a tool tip.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintToolTipBorder(javax.swing.plaf.synth.SynthContext context,
                                   Graphics g, int x, int y,
                                   int w, int h) {
    }

    /**
     * Paints the background of a tree.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTreeBackground(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the border of a tree.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTreeBorder(javax.swing.plaf.synth.SynthContext context,
                                Graphics g, int x, int y,
                                int w, int h) {
    }

    /**
     * Paints the background of the row containing a cell in a tree.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTreeCellBackground(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the border of the row containing a cell in a tree.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTreeCellBorder(javax.swing.plaf.synth.SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }

    /**
     * Paints the focus indicator for a cell in a tree when it has focus.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintTreeCellFocus(javax.swing.plaf.synth.SynthContext context,
                                   Graphics g, int x, int y,
                                   int w, int h) {
    }

    /**
     * Paints the background of the viewport.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintViewportBackground(javax.swing.plaf.synth.SynthContext context,
                                        Graphics g, int x, int y,
                                        int w, int h) {
    }

    /**
     * Paints the border of a viewport.
     *
     * @param context SynthContext identifying the <code>JComponent</code> and
     *        <code>Region</code> to paint to
     * @param g <code>Graphics</code> to paint to
     * @param x X coordinate of the area to paint to
     * @param y Y coordinate of the area to paint to
     * @param w Width of the area to paint to
     * @param h Height of the area to paint to
     */
    public void paintViewportBorder(SynthContext context,
                                    Graphics g, int x, int y,
                                    int w, int h) {
    }
}
