/*
 * Copyright (c) 1998, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.java.swing.plaf.motif;

import com.sun.java.swing.plaf.motif.MotifSeparatorUI;

import goodman.javax.swing.*;
import goodman.java.awt.Color;
import goodman.java.awt.Dimension;
import goodman.java.awt.Graphics;
import goodman.java.awt.Insets;
import goodman.java.awt.Rectangle;
import goodman.javax.swing.plaf.*;

/**
 * A Motif L&F implementation of PopupMenuSeparatorUI.  This implementation
 * is a "combined" view/controller.
 *
 * @author Jeff Shapiro
 */

public class MotifPopupMenuSeparatorUI extends MotifSeparatorUI
{
    public static ComponentUI createUI( JComponent c )
    {
        return new MotifPopupMenuSeparatorUI();
    }

    public void paint( Graphics g, JComponent c )
    {
        Dimension s = c.getSize();

        g.setColor( c.getForeground() );
        g.drawLine( 0, 0, s.width, 0 );

        g.setColor( c.getBackground() );
        g.drawLine( 0, 1, s.width, 1 );
    }

    public Dimension getPreferredSize( JComponent c )
    {
        return new Dimension( 0, 2 );
    }

}
