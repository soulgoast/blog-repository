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

package goodman.javax.management;


import goodman.javax.management.ValueExp;

/**
 * Thrown when an invalid expression is passed to a method for
 * constructing a query.  This exception is used internally by JMX
 * during the evaluation of a query.  User code does not usually see
 * it.
 *
 * @since 1.5
 */
public class BadBinaryOpValueExpException extends Exception   {


    /* Serial version */
    private static final long serialVersionUID = 5068475589449021227L;

    /**
     * @serial the {@link javax.management.ValueExp} that originated this exception
     */
    private javax.management.ValueExp exp;


    /**
     * Constructs a <CODE>BadBinaryOpValueExpException</CODE> with the specified <CODE>ValueExp</CODE>.
     *
     * @param exp the expression whose value was inappropriate.
     */
    public BadBinaryOpValueExpException(javax.management.ValueExp exp) {
        this.exp = exp;
    }


    /**
     * Returns the <CODE>ValueExp</CODE> that originated the exception.
     *
     * @return the problematic {@link javax.management.ValueExp}.
     */
    public ValueExp getExp()  {
        return exp;
    }

    /**
     * Returns the string representing the object.
     */
    public String toString()  {
        return "BadBinaryOpValueExpException: " + exp;
    }

 }
