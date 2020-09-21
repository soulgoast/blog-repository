/*
 * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.xml.bind.annotation;

import goodman.javax.xml.bind.annotation.XmlAccessOrder;
import goodman.javax.xml.bind.annotation.XmlAccessorType;
import goodman.javax.xml.bind.annotation.XmlRootElement;
import goodman.javax.xml.bind.annotation.XmlSchema;
import goodman.javax.xml.bind.annotation.XmlSchemaType;
import goodman.javax.xml.bind.annotation.XmlSchemaTypes;
import goodman.javax.xml.bind.annotation.XmlType;
import goodman.javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import goodman.java.lang.annotation.Target;
import goodman.java.lang.annotation.Retention;
import goodman.java.lang.annotation.Inherited;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * <p> Controls the ordering of fields and properties in a class. </p>
 *
 * <h3>Usage </h3>
 *
 * <p> <tt> @XmlAccessorOrder </tt> annotation can be used with the following
 * program elements:</p>
 *
 * <ul>
 *   <li> package</li>
 *   <li> a top level class </li>
 * </ul>
 *
 * <p> See "Package Specification" in <tt>javax.xml.bind</tt> package javadoc for
 * additional common information.</p>
 *
 * <p>The effective {@link javax.xml.bind.annotation.XmlAccessOrder} on a class is determined
 * as follows:
 *
 * <ul>
 *   <li> If there is a <tt>@XmlAccessorOrder</tt> on a class, then
 *        it is used. </li>
 *   <li> Otherwise, if a <tt>@XmlAccessorOrder </tt> exists on one of
 *        its super classes, then it is inherited (by the virtue of
 *        {@link Inherited})
 *   <li> Otherwise, the <tt>@XmlAccessorOrder</tt> on the package
 *        of the class is used, if it's there.
 *   <li> Otherwise {@link javax.xml.bind.annotation.XmlAccessOrder#UNDEFINED}.
 * </ul>
 *
 * <p>This annotation can be used with the following annotations:
 *    {@link XmlType}, {@link XmlRootElement}, {@link XmlAccessorType},
 *    {@link XmlSchema}, {@link XmlSchemaType}, {@link XmlSchemaTypes},
 *    , {@link XmlJavaTypeAdapter}. It can also be used with the
 *    following annotations at the package level: {@link XmlJavaTypeAdapter}.
 *
 * @author Sekhar Vajjhala, Sun Microsystems, Inc.
 * @since JAXB2.0
 * @see javax.xml.bind.annotation.XmlAccessOrder
 */

@Inherited @Retention(RUNTIME) @Target({PACKAGE, TYPE})
public @interface XmlAccessorOrder {
        javax.xml.bind.annotation.XmlAccessOrder value() default XmlAccessOrder.UNDEFINED;
}
