/*
 * Copyright (c) 1998, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.swing.colorchooser;

import goodman.javax.swing.JComponent;
import goodman.javax.swing.colorchooser.AbstractColorChooserPanel;
import goodman.javax.swing.colorchooser.ColorChooserPanel;
import goodman.javax.swing.colorchooser.ColorModel;
import goodman.javax.swing.colorchooser.ColorModelCMYK;
import goodman.javax.swing.colorchooser.ColorModelHSL;
import goodman.javax.swing.colorchooser.ColorModelHSV;
import goodman.javax.swing.colorchooser.DefaultPreviewPanel;
import goodman.javax.swing.colorchooser.DefaultSwatchChooserPanel;

/**
 * A class designed to produce preconfigured "accessory" objects to
 * insert into color choosers.
 *
 * <p>
 * <strong>Warning:</strong>
 * Serialized objects of this class will not be compatible with
 * future Swing releases. The current serialization support is
 * appropriate for short term storage or RMI between applications running
 * the same version of Swing.  As of 1.4, support for long term storage
 * of all JavaBeans&trade;
 * has been added to the <code>java.beans</code> package.
 * Please see {@link java.beans.XMLEncoder}.
 *
 * @author Steve Wilson
 */
public class ColorChooserComponentFactory {

    private ColorChooserComponentFactory() { } // can't instantiate

    public static javax.swing.colorchooser.AbstractColorChooserPanel[] getDefaultChooserPanels() {
        return new AbstractColorChooserPanel[] {
                new javax.swing.colorchooser.DefaultSwatchChooserPanel(),
                new javax.swing.colorchooser.ColorChooserPanel(new javax.swing.colorchooser.ColorModelHSV()),
                new javax.swing.colorchooser.ColorChooserPanel(new javax.swing.colorchooser.ColorModelHSL()),
                new javax.swing.colorchooser.ColorChooserPanel(new javax.swing.colorchooser.ColorModel()),
                new javax.swing.colorchooser.ColorChooserPanel(new javax.swing.colorchooser.ColorModelCMYK()),
        };
    }

    public static JComponent getPreviewPanel() {
        return new javax.swing.colorchooser.DefaultPreviewPanel();
    }
}
