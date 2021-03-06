/*
 * Copyright (c) 2005, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.management;


import goodman.javax.management.*;
import goodman.javax.management.ObjectName;
import goodman.javax.management.QueryEval;
import goodman.javax.management.QueryExp;
import goodman.javax.management.StringValueExp;

/**
 * This class is used by the query building mechanism for isInstanceOf expressions.
 * @serial include
 *
 * @since 1.6
 */
class InstanceOfQueryExp extends QueryEval implements QueryExp {

    /* Serial version */
    private static final long serialVersionUID = -1081892073854801359L;

    /**
     * @serial The {@link javax.management.StringValueExp} returning the name of the class
     *         of which selected MBeans should be instances.
     */
    private javax.management.StringValueExp classNameValue;

    /**
     * Creates a new InstanceOfExp with a specific class name.
     * @param classNameValue The {@link javax.management.StringValueExp} returning the name of
     *        the class of which selected MBeans should be instances.
     */
    // We are using StringValueExp here to be consistent with other queries,
    // although we should actually either use a simple string (the classname)
    // or a ValueExp - which would allow more complex queries - like for
    // instance evaluating the class name from an AttributeValueExp.
    // As it stands - using StringValueExp instead of a simple constant string
    // doesn't serve any useful purpose besides offering a consistent
    // look & feel.
    public InstanceOfQueryExp(javax.management.StringValueExp classNameValue) {
        if (classNameValue == null) {
            throw new IllegalArgumentException("Null class name.");
        }

        this.classNameValue = classNameValue;
    }

    /**
     * Returns the class name.
     * @returns The {@link javax.management.StringValueExp} returning the name of
     *        the class of which selected MBeans should be instances.
     */
    public javax.management.StringValueExp getClassNameValue()  {
        return classNameValue;
    }

    /**
     * Applies the InstanceOf on a MBean.
     *
     * @param name The name of the MBean on which the InstanceOf will be applied.
     *
     * @return  True if the MBean specified by the name is instance of the class.
     * @exception BadAttributeValueExpException
     * @exception InvalidApplicationException
     * @exception BadStringOperationException
     * @exception BadBinaryOpValueExpException
     */
    public boolean apply(ObjectName name)
        throws BadStringOperationException,
        BadBinaryOpValueExpException,
        BadAttributeValueExpException,
        InvalidApplicationException {

        // Get the class name value
        final javax.management.StringValueExp val;
        try {
            val = (StringValueExp) classNameValue.apply(name);
        } catch (ClassCastException x) {
            // Should not happen - unless someone wrongly implemented
            // StringValueExp.apply().
            final BadStringOperationException y =
                    new BadStringOperationException(x.toString());
            y.initCause(x);
            throw y;
        }

        // Test whether the MBean is an instance of that class.
        try {
            return getMBeanServer().isInstanceOf(name, val.getValue());
        } catch (InstanceNotFoundException infe) {
            return false;
        }
    }

    /**
     * Returns a string representation of this InstanceOfQueryExp.
     * @return a string representation of this InstanceOfQueryExp.
     */
    public String toString() {
       return "InstanceOf " + classNameValue.toString();
   }
}
