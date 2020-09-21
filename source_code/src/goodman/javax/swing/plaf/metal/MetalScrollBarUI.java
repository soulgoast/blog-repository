/*
 * Copyright (c) 1998, 2014, Oracle and/or its affiliates. All rights reserved.
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

import goodman.java.awt.Color;
import goodman.java.awt.Dimension;
import goodman.java.awt.Graphics;
import goodman.java.awt.Rectangle;
import goodman.java.beans.PropertyChangeEvent;
import goodman.java.beans.PropertyChangeListener;

import goodman.javax.swing.JButton;
import goodman.javax.swing.JComponent;
import goodman.javax.swing.JScrollBar;
import goodman.javax.swing.UIManager;
import goodman.javax.swing.plaf.ComponentUI;
import goodman.javax.swing.plaf.basic.BasicScrollBarUI;
import goodman.javax.swing.plaf.metal.MetalBumps;
import goodman.javax.swing.plaf.metal.MetalLookAndFeel;
import goodman.javax.swing.plaf.metal.MetalScrollButton;
import goodman.javax.swing.plaf.metal.MetalUtils;

import static sun.swing.SwingUtilities2.drawHLine;
import static sun.swing.SwingUtilities2.drawRect;
import static sun.swing.SwingUtilities2.drawVLine;


/**
 * Implementation of ScrollBarUI for the Metal Look and Feel
 * <p>
 *
 * @author Tom Santos
 * @author Steve Wilson
 */
public class MetalScrollBarUI extends BasicScrollBarUI
{
    private static Color shadowColor;
    private static Color highlightColor;
    private static Color darkShadowColor;
    private static Color thumbColor;
    private static Color thumbShadow;
    private static Color thumbHighlightColor;


    protected javax.swing.plaf.metal.MetalBumps bumps;

    protected MetalScrollButton increaseButton;
    protected MetalScrollButton decreaseButton;

    protected  int scrollBarWidth;

    public static final String FREE_STANDING_PROP = "JScrollBar.isFreeStanding";
    protected boolean isFreeStanding = true;

    public static ComponentUI createUI( JComponent c )
    {
        return new MetalScrollBarUI();
    }

    protected void installDefaults() {
        scrollBarWidth = ((Integer)(UIManager.get( "ScrollBar.width" ))).intValue();
        super.installDefaults();
        bumps = new javax.swing.plaf.metal.MetalBumps( 10, 10, thumbHighlightColor, thumbShadow, thumbColor );
    }

    protected void installListeners(){
        super.installListeners();
        ((ScrollBarListener)propertyChangeListener).handlePropertyChange( scrollbar.getClientProperty( FREE_STANDING_PROP ) );
    }

    protected PropertyChangeListener createPropertyChangeListener(){
        return new ScrollBarListener();
    }

    protected void configureScrollBarColors()
    {
        super.configureScrollBarColors();
        shadowColor         = UIManager.getColor("ScrollBar.shadow");
        highlightColor      = UIManager.getColor("ScrollBar.highlight");
        darkShadowColor     = UIManager.getColor("ScrollBar.darkShadow");
        thumbColor          = UIManager.getColor("ScrollBar.thumb");
        thumbShadow         = UIManager.getColor("ScrollBar.thumbShadow");
        thumbHighlightColor = UIManager.getColor("ScrollBar.thumbHighlight");


    }

    public Dimension getPreferredSize( JComponent c )
    {
        if ( scrollbar.getOrientation() == JScrollBar.VERTICAL )
        {
            return new Dimension( scrollBarWidth, scrollBarWidth * 3 + 10 );
        }
        else  // Horizontal
        {
            return new Dimension( scrollBarWidth * 3 + 10, scrollBarWidth );
        }

    }

    /** Returns the view that represents the decrease view.
      */
    protected JButton createDecreaseButton( int orientation )
    {
        decreaseButton = new MetalScrollButton( orientation, scrollBarWidth, isFreeStanding );
        return decreaseButton;
    }

    /** Returns the view that represents the increase view. */
    protected JButton createIncreaseButton( int orientation )
    {
        increaseButton =  new MetalScrollButton( orientation, scrollBarWidth, isFreeStanding );
        return increaseButton;
    }

    protected void paintTrack( Graphics g, JComponent c, Rectangle trackBounds )
    {
        g.translate( trackBounds.x, trackBounds.y );

        boolean leftToRight = javax.swing.plaf.metal.MetalUtils.isLeftToRight(c);

        if ( scrollbar.getOrientation() == JScrollBar.VERTICAL )
        {
            if ( !isFreeStanding ) {
                trackBounds.width += 2;
                if ( !leftToRight ) {
                    g.translate( -1, 0 );
                }
            }

            if ( c.isEnabled() ) {
                g.setColor( darkShadowColor );
                drawVLine(g, 0, 0, trackBounds.height - 1);
                drawVLine(g, trackBounds.width - 2, 0, trackBounds.height - 1);
                drawHLine(g, 2, trackBounds.width - 1, trackBounds.height - 1);
                drawHLine(g, 2, trackBounds.width - 2, 0);

                g.setColor( shadowColor );
                //      g.setColor( Color.red);
                drawVLine(g, 1, 1, trackBounds.height - 2);
                drawHLine(g, 1, trackBounds.width - 3, 1);
                if (scrollbar.getValue() != scrollbar.getMaximum()) {  // thumb shadow
                    int y = thumbRect.y + thumbRect.height - trackBounds.y;
                    drawHLine(g, 1, trackBounds.width - 1, y);
                }
                g.setColor(highlightColor);
                drawVLine(g, trackBounds.width - 1, 0, trackBounds.height - 1);
            } else {
                javax.swing.plaf.metal.MetalUtils.drawDisabledBorder(g, 0, 0, trackBounds.width, trackBounds.height );
            }

            if ( !isFreeStanding ) {
                trackBounds.width -= 2;
                if ( !leftToRight ) {
                    g.translate( 1, 0 );
                }
            }
        }
        else  // HORIZONTAL
        {
            if ( !isFreeStanding ) {
                trackBounds.height += 2;
            }

            if ( c.isEnabled() ) {
                g.setColor( darkShadowColor );
                drawHLine(g, 0, trackBounds.width - 1, 0);  // top
                drawVLine(g, 0, 2, trackBounds.height - 2); // left
                drawHLine(g, 0, trackBounds.width - 1, trackBounds.height - 2 ); // bottom
                drawVLine(g, trackBounds.width - 1, 2,  trackBounds.height - 1 ); // right

                g.setColor( shadowColor );
                //      g.setColor( Color.red);
                drawHLine(g, 1, trackBounds.width - 2, 1 );  // top
                drawVLine(g, 1, 1, trackBounds.height - 3 ); // left
                drawHLine(g, 0, trackBounds.width - 1, trackBounds.height - 1 ); // bottom
                if (scrollbar.getValue() != scrollbar.getMaximum()) {  // thumb shadow
                    int x = thumbRect.x + thumbRect.width - trackBounds.x;
                    drawVLine(g, x, 1, trackBounds.height-1);
                }
            } else {
                javax.swing.plaf.metal.MetalUtils.drawDisabledBorder(g, 0, 0, trackBounds.width, trackBounds.height );
            }

            if ( !isFreeStanding ) {
                trackBounds.height -= 2;
            }
        }

        g.translate( -trackBounds.x, -trackBounds.y );
    }

    protected void paintThumb( Graphics g, JComponent c, Rectangle thumbBounds )
    {
        if (!c.isEnabled()) {
            return;
        }

        if (javax.swing.plaf.metal.MetalLookAndFeel.usingOcean()) {
            oceanPaintThumb(g, c, thumbBounds);
            return;
        }

        boolean leftToRight = javax.swing.plaf.metal.MetalUtils.isLeftToRight(c);

        g.translate( thumbBounds.x, thumbBounds.y );

        if ( scrollbar.getOrientation() == JScrollBar.VERTICAL )
        {
            if ( !isFreeStanding ) {
                thumbBounds.width += 2;
                if ( !leftToRight ) {
                    g.translate( -1, 0 );
                }
            }

            g.setColor( thumbColor );
            g.fillRect( 0, 0, thumbBounds.width - 2, thumbBounds.height - 1 );

            g.setColor( thumbShadow );
            drawRect(g, 0, 0, thumbBounds.width - 2, thumbBounds.height - 1);

            g.setColor( thumbHighlightColor );
            drawHLine(g, 1, thumbBounds.width - 3, 1);
            drawVLine(g, 1, 1, thumbBounds.height - 2);

            bumps.setBumpArea( thumbBounds.width - 6, thumbBounds.height - 7 );
            bumps.paintIcon( c, g, 3, 4 );

            if ( !isFreeStanding ) {
                thumbBounds.width -= 2;
                if ( !leftToRight ) {
                    g.translate( 1, 0 );
                }
            }
        }
        else  // HORIZONTAL
        {
            if ( !isFreeStanding ) {
                thumbBounds.height += 2;
            }

            g.setColor( thumbColor );
            g.fillRect( 0, 0, thumbBounds.width - 1, thumbBounds.height - 2 );

            g.setColor( thumbShadow );
            drawRect(g, 0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

            g.setColor( thumbHighlightColor );
            drawHLine(g, 1, thumbBounds.width - 3, 1);
            drawVLine(g, 1, 1, thumbBounds.height - 3);

            bumps.setBumpArea( thumbBounds.width - 7, thumbBounds.height - 6 );
            bumps.paintIcon( c, g, 4, 3 );

            if ( !isFreeStanding ) {
                thumbBounds.height -= 2;
            }
        }

        g.translate( -thumbBounds.x, -thumbBounds.y );
    }

    private void oceanPaintThumb(Graphics g, JComponent c,
                                   Rectangle thumbBounds) {
        boolean leftToRight = javax.swing.plaf.metal.MetalUtils.isLeftToRight(c);

        g.translate(thumbBounds.x, thumbBounds.y);

        if (scrollbar.getOrientation() == JScrollBar.VERTICAL) {
            if (!isFreeStanding) {
                thumbBounds.width += 2;
                if (!leftToRight) {
                    g.translate(-1, 0);
                }
            }

            if (thumbColor != null) {
                g.setColor(thumbColor);
                g.fillRect(0, 0, thumbBounds.width - 2,thumbBounds.height - 1);
            }

            g.setColor(thumbShadow);
            drawRect(g, 0, 0, thumbBounds.width - 2, thumbBounds.height - 1);

            g.setColor(thumbHighlightColor);
            drawHLine(g, 1, thumbBounds.width - 3, 1);
            drawVLine(g, 1, 1, thumbBounds.height - 2);

            javax.swing.plaf.metal.MetalUtils.drawGradient(c, g, "ScrollBar.gradient", 2, 2,
                                    thumbBounds.width - 4,
                                    thumbBounds.height - 3, false);

            int gripSize = thumbBounds.width - 8;
            if (gripSize > 2 && thumbBounds.height >= 10) {
                g.setColor(javax.swing.plaf.metal.MetalLookAndFeel.getPrimaryControlDarkShadow());
                int gripY = thumbBounds.height / 2 - 2;
                for (int counter = 0; counter < 6; counter += 2) {
                    g.fillRect(4, counter + gripY, gripSize, 1);
                }

                g.setColor(javax.swing.plaf.metal.MetalLookAndFeel.getWhite());
                gripY++;
                for (int counter = 0; counter < 6; counter += 2) {
                    g.fillRect(5, counter + gripY, gripSize, 1);
                }
            }
            if (!isFreeStanding) {
                thumbBounds.width -= 2;
                if (!leftToRight) {
                    g.translate(1, 0);
                }
            }
        }
        else { // HORIZONTAL
            if (!isFreeStanding) {
                thumbBounds.height += 2;
            }

            if (thumbColor != null) {
                g.setColor(thumbColor);
                g.fillRect(0, 0, thumbBounds.width - 1,thumbBounds.height - 2);
            }

            g.setColor(thumbShadow);
            drawRect(g, 0, 0, thumbBounds.width - 1, thumbBounds.height - 2);

            g.setColor(thumbHighlightColor);
            drawHLine(g, 1, thumbBounds.width - 2, 1);
            drawVLine(g, 1, 1, thumbBounds.height - 3);

            javax.swing.plaf.metal.MetalUtils.drawGradient(c, g, "ScrollBar.gradient", 2, 2,
                                    thumbBounds.width - 3,
                                    thumbBounds.height - 4, true);

            int gripSize = thumbBounds.height - 8;
            if (gripSize > 2 && thumbBounds.width >= 10) {
                g.setColor(javax.swing.plaf.metal.MetalLookAndFeel.getPrimaryControlDarkShadow());
                int gripX = thumbBounds.width / 2 - 2;
                for (int counter = 0; counter < 6; counter += 2) {
                    g.fillRect(gripX + counter, 4, 1, gripSize);
                }

                g.setColor(MetalLookAndFeel.getWhite());
                gripX++;
                for (int counter = 0; counter < 6; counter += 2) {
                    g.fillRect(gripX + counter, 5, 1, gripSize);
                }
            }

            if (!isFreeStanding) {
                thumbBounds.height -= 2;
            }
        }

        g.translate( -thumbBounds.x, -thumbBounds.y );
    }

    protected Dimension getMinimumThumbSize()
    {
        return new Dimension( scrollBarWidth, scrollBarWidth );
    }

    /**
      * This is overridden only to increase the invalid area.  This
      * ensures that the "Shadow" below the thumb is invalidated
      */
    protected void setThumbBounds(int x, int y, int width, int height)
    {
        /* If the thumbs bounds haven't changed, we're done.
         */
        if ((thumbRect.x == x) &&
            (thumbRect.y == y) &&
            (thumbRect.width == width) &&
            (thumbRect.height == height)) {
            return;
        }

        /* Update thumbRect, and repaint the union of x,y,w,h and
         * the old thumbRect.
         */
        int minX = Math.min(x, thumbRect.x);
        int minY = Math.min(y, thumbRect.y);
        int maxX = Math.max(x + width, thumbRect.x + thumbRect.width);
        int maxY = Math.max(y + height, thumbRect.y + thumbRect.height);

        thumbRect.setBounds(x, y, width, height);
        scrollbar.repaint(minX, minY, (maxX - minX)+1, (maxY - minY)+1);
    }



    class ScrollBarListener extends PropertyChangeHandler
    {
        public void propertyChange(PropertyChangeEvent e)
        {
            String name = e.getPropertyName();
            if ( name.equals( FREE_STANDING_PROP ) )
            {
                handlePropertyChange( e.getNewValue() );
            }
            else {
                super.propertyChange( e );
            }
        }

        public void handlePropertyChange( Object newValue )
        {
            if ( newValue != null )
            {
                boolean temp = ((Boolean)newValue).booleanValue();
                boolean becameFlush = temp == false && isFreeStanding == true;
                boolean becameNormal = temp == true && isFreeStanding == false;

                isFreeStanding = temp;

                if ( becameFlush ) {
                    toFlush();
                }
                else if ( becameNormal ) {
                    toFreeStanding();
                }
            }
            else
            {

                if ( !isFreeStanding ) {
                    isFreeStanding = true;
                    toFreeStanding();
                }

                // This commented-out block is used for testing flush scrollbars.
/*
                if ( isFreeStanding ) {
                    isFreeStanding = false;
                    toFlush();
                }
*/
            }

            if ( increaseButton != null )
            {
                increaseButton.setFreeStanding( isFreeStanding );
            }
            if ( decreaseButton != null )
            {
                decreaseButton.setFreeStanding( isFreeStanding );
            }
        }

        protected void toFlush() {
            scrollBarWidth -= 2;
        }

        protected void toFreeStanding() {
            scrollBarWidth += 2;
        }
    } // end class ScrollBarListener
}