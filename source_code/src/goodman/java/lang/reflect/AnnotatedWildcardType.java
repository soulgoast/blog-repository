/*
 * Copyright (c) 2012, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.lang.reflect;

import goodman.java.lang.reflect.AnnotatedType;

/**
 * {@code AnnotatedWildcardType} represents the potentially annotated use of a
 * wildcard type argument, whose upper or lower bounds may themselves represent
 * annotated uses of types.
 *
 * @since 1.8
 */
public interface AnnotatedWildcardType extends java.lang.reflect.AnnotatedType {

    /**
     * Returns the potentially annotated lower bounds of this wildcard type.
     *
     * @return the potentially annotated lower bounds of this wildcard type
     */
    java.lang.reflect.AnnotatedType[] getAnnotatedLowerBounds();

    /**
     * Returns the potentially annotated upper bounds of this wildcard type.
     *
     * @return the potentially annotated upper bounds of this wildcard type
     */
    AnnotatedType[] getAnnotatedUpperBounds();
}
