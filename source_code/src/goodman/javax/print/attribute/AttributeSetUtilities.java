/*
 * Copyright (c) 2000, 2004, Oracle and/or its affiliates. All rights reserved.
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


package goodman.javax.print.attribute;

import goodman.javax.print.attribute.Attribute;
import goodman.javax.print.attribute.AttributeSet;
import goodman.javax.print.attribute.DocAttributeSet;
import goodman.javax.print.attribute.PrintJobAttributeSet;
import goodman.javax.print.attribute.PrintRequestAttributeSet;
import goodman.javax.print.attribute.PrintServiceAttributeSet;
import goodman.javax.print.attribute.UnmodifiableSetException;
import goodman.java.io.Serializable;

/**
 * Class AttributeSetUtilities provides static methods for manipulating
 * AttributeSets.
 * <ul>
 * <li>Methods for creating unmodifiable and synchronized views of attribute
 * sets.
 * <li>operations useful for building
 * implementations of interface {@link javax.print.attribute.AttributeSet AttributeSet}
 * </ul>
 * <P>
 * An <B>unmodifiable view</B> <I>U</I> of an AttributeSet <I>S</I> provides a
 * client with "read-only" access to <I>S</I>. Query operations on <I>U</I>
 * "read through" to <I>S</I>; thus, changes in <I>S</I> are reflected in
 * <I>U</I>. However, any attempt to modify <I>U</I>,
 *  results in an UnmodifiableSetException.
 * The unmodifiable view object <I>U</I> will be serializable if the
 * attribute set object <I>S</I> is serializable.
 * <P>
 * A <B>synchronized view</B> <I>V</I> of an attribute set <I>S</I> provides a
 * client with synchronized (multiple thread safe) access to <I>S</I>. Each
 * operation of <I>V</I> is synchronized using <I>V</I> itself as the lock
 * object and then merely invokes the corresponding operation of <I>S</I>. In
 * order to guarantee mutually exclusive access, it is critical that all
 * access to <I>S</I> is accomplished through <I>V</I>. The synchronized view
 * object <I>V</I> will be serializable if the attribute set object <I>S</I>
 * is serializable.
 * <P>
 * As mentioned in the package description of javax.print, a null reference
 * parameter to methods is
 * incorrect unless explicitly documented on the method as having a meaningful
 * interpretation.  Usage to the contrary is incorrect coding and may result in
 * a run time exception either immediately
 * or at some later time. IllegalArgumentException and NullPointerException
 * are examples of typical and acceptable run time exceptions for such cases.
 *
 * @author  Alan Kaminsky
 */
public final class AttributeSetUtilities {

    /* Suppress default constructor, ensuring non-instantiability.
     */
    private AttributeSetUtilities() {
    }

    /**
      * @serial include
      */
    private static class UnmodifiableAttributeSet
        implements javax.print.attribute.AttributeSet, Serializable {

        private javax.print.attribute.AttributeSet attrset;

        /* Unmodifiable view of the underlying attribute set.
         */
        public UnmodifiableAttributeSet(javax.print.attribute.AttributeSet attributeSet) {

            attrset = attributeSet;
        }

        public javax.print.attribute.Attribute get(Class<?> key) {
            return attrset.get(key);
        }

        public boolean add(javax.print.attribute.Attribute attribute) {
            throw new UnmodifiableSetException();
        }

        public synchronized boolean remove(Class<?> category) {
            throw new UnmodifiableSetException();
        }

        public boolean remove(javax.print.attribute.Attribute attribute) {
            throw new UnmodifiableSetException();
        }

        public boolean containsKey(Class<?> category) {
            return attrset.containsKey(category);
        }

        public boolean containsValue(javax.print.attribute.Attribute attribute) {
            return attrset.containsValue(attribute);
        }

        public boolean addAll(javax.print.attribute.AttributeSet attributes) {
            throw new UnmodifiableSetException();
        }

        public int size() {
            return attrset.size();
        }

        public javax.print.attribute.Attribute[] toArray() {
            return attrset.toArray();
        }

        public void clear() {
            throw new UnmodifiableSetException();
        }

        public boolean isEmpty() {
            return attrset.isEmpty();
        }

        public boolean equals(Object o) {
            return attrset.equals (o);
        }

        public int hashCode() {
            return attrset.hashCode();
        }

    }

    /**
      * @serial include
      */
    private static class UnmodifiableDocAttributeSet
        extends UnmodifiableAttributeSet
        implements javax.print.attribute.DocAttributeSet, Serializable {

        public UnmodifiableDocAttributeSet(javax.print.attribute.DocAttributeSet attributeSet) {

            super (attributeSet);
        }
    }

    /**
      * @serial include
      */
    private static class UnmodifiablePrintRequestAttributeSet
        extends UnmodifiableAttributeSet
        implements PrintRequestAttributeSet, Serializable
    {
        public UnmodifiablePrintRequestAttributeSet
            (PrintRequestAttributeSet attributeSet) {

            super (attributeSet);
        }
    }

    /**
      * @serial include
      */
    private static class UnmodifiablePrintJobAttributeSet
        extends UnmodifiableAttributeSet
        implements javax.print.attribute.PrintJobAttributeSet, Serializable
    {
        public UnmodifiablePrintJobAttributeSet
            (javax.print.attribute.PrintJobAttributeSet attributeSet) {

            super (attributeSet);
        }
    }

    /**
      * @serial include
      */
    private static class UnmodifiablePrintServiceAttributeSet
        extends UnmodifiableAttributeSet
        implements PrintServiceAttributeSet, Serializable
    {
        public UnmodifiablePrintServiceAttributeSet
            (PrintServiceAttributeSet attributeSet) {

            super (attributeSet);
        }
    }

    /**
     * Creates an unmodifiable view of the given attribute set.
     *
     * @param  attributeSet  Underlying attribute set.
     *
     * @return  Unmodifiable view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null. Null is never a
     */
    public static javax.print.attribute.AttributeSet unmodifiableView(javax.print.attribute.AttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }

        return new UnmodifiableAttributeSet(attributeSet);
    }

    /**
     * Creates an unmodifiable view of the given doc attribute set.
     *
     * @param  attributeSet  Underlying doc attribute set.
     *
     * @return  Unmodifiable view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null.
     */
    public static javax.print.attribute.DocAttributeSet unmodifiableView
        (javax.print.attribute.DocAttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new UnmodifiableDocAttributeSet(attributeSet);
    }

    /**
     * Creates an unmodifiable view of the given print request attribute set.
     *
     * @param  attributeSet  Underlying print request attribute set.
     *
     * @return  Unmodifiable view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null.
     */
    public static PrintRequestAttributeSet
        unmodifiableView(PrintRequestAttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new UnmodifiablePrintRequestAttributeSet(attributeSet);
    }

    /**
     * Creates an unmodifiable view of the given print job attribute set.
     *
     * @param  attributeSet  Underlying print job attribute set.
     *
     * @return  Unmodifiable view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null.
     */
    public static javax.print.attribute.PrintJobAttributeSet
        unmodifiableView(javax.print.attribute.PrintJobAttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new UnmodifiablePrintJobAttributeSet(attributeSet);
    }

    /**
     * Creates an unmodifiable view of the given print service attribute set.
     *
     * @param  attributeSet  Underlying print service attribute set.
     *
     * @return  Unmodifiable view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null.
     */
    public static PrintServiceAttributeSet
        unmodifiableView(PrintServiceAttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new UnmodifiablePrintServiceAttributeSet (attributeSet);
    }

    /**
      * @serial include
      */
    private static class SynchronizedAttributeSet
                        implements javax.print.attribute.AttributeSet, Serializable {

        private javax.print.attribute.AttributeSet attrset;

        public SynchronizedAttributeSet(javax.print.attribute.AttributeSet attributeSet) {
            attrset = attributeSet;
        }

        public synchronized javax.print.attribute.Attribute get(Class<?> category) {
            return attrset.get(category);
        }

        public synchronized boolean add(javax.print.attribute.Attribute attribute) {
            return attrset.add(attribute);
        }

        public synchronized boolean remove(Class<?> category) {
            return attrset.remove(category);
        }

        public synchronized boolean remove(javax.print.attribute.Attribute attribute) {
            return attrset.remove(attribute);
        }

        public synchronized boolean containsKey(Class<?> category) {
            return attrset.containsKey(category);
        }

        public synchronized boolean containsValue(javax.print.attribute.Attribute attribute) {
            return attrset.containsValue(attribute);
        }

        public synchronized boolean addAll(javax.print.attribute.AttributeSet attributes) {
            return attrset.addAll(attributes);
        }

        public synchronized int size() {
            return attrset.size();
        }

        public synchronized javax.print.attribute.Attribute[] toArray() {
            return attrset.toArray();
        }

        public synchronized void clear() {
            attrset.clear();
        }

        public synchronized boolean isEmpty() {
            return attrset.isEmpty();
        }

        public synchronized boolean equals(Object o) {
            return attrset.equals (o);
        }

        public synchronized int hashCode() {
            return attrset.hashCode();
        }
    }

    /**
      * @serial include
      */
    private static class SynchronizedDocAttributeSet
        extends SynchronizedAttributeSet
        implements javax.print.attribute.DocAttributeSet, Serializable {

        public SynchronizedDocAttributeSet(javax.print.attribute.DocAttributeSet attributeSet) {
            super(attributeSet);
        }
    }

    /**
      * @serial include
      */
    private static class SynchronizedPrintRequestAttributeSet
        extends SynchronizedAttributeSet
        implements PrintRequestAttributeSet, Serializable {

        public SynchronizedPrintRequestAttributeSet
            (PrintRequestAttributeSet attributeSet) {
            super(attributeSet);
        }
    }

    /**
      * @serial include
      */
    private static class SynchronizedPrintJobAttributeSet
        extends SynchronizedAttributeSet
        implements javax.print.attribute.PrintJobAttributeSet, Serializable {

        public SynchronizedPrintJobAttributeSet
            (javax.print.attribute.PrintJobAttributeSet attributeSet) {
            super(attributeSet);
        }
    }

    /**
      * @serial include
      */
    private static class SynchronizedPrintServiceAttributeSet
        extends SynchronizedAttributeSet
        implements PrintServiceAttributeSet, Serializable {
        public SynchronizedPrintServiceAttributeSet
            (PrintServiceAttributeSet attributeSet) {
            super(attributeSet);
        }
    }

    /**
     * Creates a synchronized view of the given attribute set.
     *
     * @param  attributeSet  Underlying attribute set.
     *
     * @return  Synchronized view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null.
     */
    public static javax.print.attribute.AttributeSet synchronizedView
        (AttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedAttributeSet(attributeSet);
    }

    /**
     * Creates a synchronized view of the given doc attribute set.
     *
     * @param  attributeSet  Underlying doc attribute set.
     *
     * @return  Synchronized view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null.
     */
    public static javax.print.attribute.DocAttributeSet
        synchronizedView(DocAttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedDocAttributeSet(attributeSet);
    }

    /**
     * Creates a synchronized view of the given print request attribute set.
     *
     * @param  attributeSet  Underlying print request attribute set.
     *
     * @return  Synchronized view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null.
     */
    public static PrintRequestAttributeSet
        synchronizedView(PrintRequestAttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedPrintRequestAttributeSet(attributeSet);
    }

    /**
     * Creates a synchronized view of the given print job attribute set.
     *
     * @param  attributeSet  Underlying print job attribute set.
     *
     * @return  Synchronized view of <CODE>attributeSet</CODE>.
     *
     * @exception  NullPointerException
     *     Thrown if <CODE>attributeSet</CODE> is null.
     */
    public static javax.print.attribute.PrintJobAttributeSet
        synchronizedView(PrintJobAttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedPrintJobAttributeSet(attributeSet);
    }

    /**
     * Creates a synchronized view of the given print service attribute set.
     *
     * @param  attributeSet  Underlying print service attribute set.
     *
     * @return  Synchronized view of <CODE>attributeSet</CODE>.
     */
    public static PrintServiceAttributeSet
        synchronizedView(PrintServiceAttributeSet attributeSet) {
        if (attributeSet == null) {
            throw new NullPointerException();
        }
        return new SynchronizedPrintServiceAttributeSet(attributeSet);
    }


    /**
     * Verify that the given object is a {@link Class Class} that
     * implements the given interface, which is assumed to be interface {@link
     * javax.print.attribute.Attribute Attribute} or a subinterface thereof.
     *
     * @param  object     Object to test.
     * @param  interfaceName  Interface the object must implement.
     *
     * @return  If <CODE>object</CODE> is a {@link Class Class}
     *          that implements <CODE>interfaceName</CODE>,
     *          <CODE>object</CODE> is returned downcast to type {@link
     *          Class Class}; otherwise an exception is thrown.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>object</CODE> is null.
     * @exception  ClassCastException
     *     (unchecked exception) Thrown if <CODE>object</CODE> is not a
     *     {@link Class Class} that implements
     *     <CODE>interfaceName</CODE>.
     */
    public static Class<?>
        verifyAttributeCategory(Object object, Class<?> interfaceName) {

        Class result = (Class) object;
        if (interfaceName.isAssignableFrom (result)) {
            return result;
        }
        else {
            throw new ClassCastException();
        }
    }

    /**
     * Verify that the given object is an instance of the given interface, which
     * is assumed to be interface {@link javax.print.attribute.Attribute Attribute} or a subinterface
     * thereof.
     *
     * @param  object     Object to test.
     * @param  interfaceName  Interface of which the object must be an instance.
     *
     * @return  If <CODE>object</CODE> is an instance of
     *          <CODE>interfaceName</CODE>, <CODE>object</CODE> is returned
     *          downcast to type {@link javax.print.attribute.Attribute Attribute}; otherwise an
     *          exception is thrown.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>object</CODE> is null.
     * @exception  ClassCastException
     *     (unchecked exception) Thrown if <CODE>object</CODE> is not an
     *     instance of <CODE>interfaceName</CODE>.
     */
    public static javax.print.attribute.Attribute
        verifyAttributeValue(Object object, Class<?> interfaceName) {

        if (object == null) {
            throw new NullPointerException();
        }
        else if (interfaceName.isInstance (object)) {
            return (javax.print.attribute.Attribute) object;
        } else {
            throw new ClassCastException();
        }
    }

    /**
     * Verify that the given attribute category object is equal to the
     * category of the given attribute value object. If so, this method
     * returns doing nothing. If not, this method throws an exception.
     *
     * @param  category   Attribute category to test.
     * @param  attribute  Attribute value to test.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if the <CODE>category</CODE> is
     *     null or if the <CODE>attribute</CODE> is null.
     * @exception  IllegalArgumentException
     *     (unchecked exception) Thrown if the <CODE>category</CODE> is not
     *     equal to the category of the <CODE>attribute</CODE>.
     */
    public static void
        verifyCategoryForValue(Class<?> category, Attribute attribute) {

        if (!category.equals (attribute.getCategory())) {
            throw new IllegalArgumentException();
        }
    }
}
