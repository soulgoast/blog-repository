/*
 * Copyright (c) 1999, 2003, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.sound.sampled;

import goodman.javax.sound.sampled.Control;

/**
 * A <code>CompoundControl</code>, such as a graphic equalizer, provides control
 * over two or more related properties, each of which is itself represented as
 * a <code>Control</code>.
 *
 * @author Kara Kytle
 * @since 1.3
 */
public abstract class CompoundControl extends javax.sound.sampled.Control {


    // TYPE DEFINES


    // INSTANCE VARIABLES


    /**
     * The set of member controls.
     */
    private javax.sound.sampled.Control[] controls;



    // CONSTRUCTORS


    /**
     * Constructs a new compound control object with the given parameters.
     *
     * @param type the type of control represented this compound control object
     * @param memberControls the set of member controls
     */
    protected CompoundControl(Type type, javax.sound.sampled.Control[] memberControls) {

        super(type);
        this.controls = memberControls;
    }



    // METHODS


    /**
     * Returns the set of member controls that comprise the compound control.
     * @return the set of member controls.
     */
    public javax.sound.sampled.Control[] getMemberControls() {

        javax.sound.sampled.Control[] localArray = new javax.sound.sampled.Control[controls.length];

        for (int i = 0; i < controls.length; i++) {
            localArray[i] = controls[i];
        }

        return localArray;
    }


    // ABSTRACT METHOD IMPLEMENTATIONS: CONTROL


    /**
     * Provides a string representation of the control
     * @return a string description
     */
    public String toString() {

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < controls.length; i++) {
            if (i != 0) {
                buf.append(", ");
                if ((i + 1) == controls.length) {
                    buf.append("and ");
                }
            }
            buf.append(controls[i].getType());
        }

        return new String(getType() + " Control containing " + buf + " Controls.");
    }


    // INNER CLASSES


    /**
     * An instance of the <code>CompoundControl.Type</code> inner class identifies one kind of
     * compound control.  Static instances are provided for the
     * common types.
     *
     * @author Kara Kytle
     * @since 1.3
     */
    public static class Type extends Control.Type {


        // TYPE DEFINES

        // CONSTRUCTOR


        /**
         * Constructs a new compound control type.
         * @param name  the name of the new compound control type
         */
        protected Type(String name) {
            super(name);
        }
    } // class Type

} // class CompoundControl
