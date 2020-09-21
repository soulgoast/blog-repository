/*
 * Copyright (c) 2009, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.lang.model.element;

import goodman.javax.lang.model.element.Element;
import goodman.javax.lang.model.element.TypeParameterElement;
import goodman.java.util.List;

/**
 * A mixin interface for an element that has type parameters.
 *
 * @author Joseph D. Darcy
 * @since 1.7
 */
public interface Parameterizable extends Element {
    /**
     * Returns the formal type parameters of the type element in
     * declaration order.
     *
     * @return the formal type parameters, or an empty list
     * if there are none
     */
    List<? extends TypeParameterElement> getTypeParameters();
}
