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

package goodman.javax.swing.plaf.metal;

import goodman.javax.swing.*;
import goodman.java.awt.Color;
import goodman.java.awt.Dimension;
import goodman.java.awt.Graphics;
import goodman.java.awt.Insets;
import goodman.java.awt.Rectangle;
import goodman.javax.swing.plaf.*;
import goodman.javax.swing.plaf.metal.MetalSeparatorUI;


/**
 * A Metal L&amp;F implementation of PopupMenuSeparatorUI.  This implementation
 * is a "combined" view/controller.
 *
 * @author Jeff Shapiro
 */

public class MetalPopupMenuSeparatorUI extends MetalSeparatorUI
{
    public static ComponentUI createUI( JComponent c )
    {
        return new MetalPopupMenuSeparatorUI();
    }

    public void paint( Graphics g, JComponent c )
    {
        Dimension s = c.getSize();

        g.setColor( c.getForeground() );
        g.drawLine( 0, 1, s.width, 1 );

        g.setColor( c.getBackground() );
        g.drawLine( 0, 2, s.width, 2 );
        g.drawLine( 0, 0, 0, 0 );
        g.drawLine( 0, 3, 0, 3 );
    }

    public Dimension getPreferredSize( JComponent c )
    {
        return new Dimension( 0, 4 );
    }
}
