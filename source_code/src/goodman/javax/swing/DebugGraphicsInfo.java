/*
 * Copyright (c) 1997, 2008, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.swing;

import goodman.javax.swing.JComponent;
import goodman.javax.swing.JFrame;
import goodman.java.awt.*;
import goodman.java.util.*;

/** Class used by DebugGraphics for maintaining information about how
  * to render graphics calls.
  *
  * @author Dave Karlton
  */
class DebugGraphicsInfo {
    Color                flashColor = Color.red;
    int                  flashTime = 100;
    int                  flashCount = 2;
    Hashtable<javax.swing.JComponent, Integer> componentToDebug;
    JFrame debugFrame = null;
    java.io.PrintStream  stream = System.out;

    void setDebugOptions(javax.swing.JComponent component, int debug) {
        if (debug == 0) {
            return;
        }
        if (componentToDebug == null) {
            componentToDebug = new Hashtable<javax.swing.JComponent, Integer>();
        }
        if (debug > 0) {
            componentToDebug.put(component, Integer.valueOf(debug));
        } else {
            componentToDebug.remove(component);
        }
    }

    int getDebugOptions(JComponent component) {
        if (componentToDebug == null) {
            return 0;
        } else {
            Integer integer = componentToDebug.get(component);

            return integer == null ? 0 : integer.intValue();
        }
    }

    void log(String string) {
        stream.println(string);
    }
}
