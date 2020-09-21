/*
 * Copyright (c) 2005, 2009, Oracle and/or its affiliates. All rights reserved.
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

import goodman.javax.lang.model.UnknownEntityException;
import goodman.javax.lang.model.element.AnnotationValue;
import goodman.javax.lang.model.element.AnnotationValueVisitor;

/**
 * Indicates that an unknown kind of annotation value was encountered.
 * This can occur if the language evolves and new kinds of annotation
 * values can be stored in an annotation.  May be thrown by an
 * {@linkplain javax.lang.model.element.AnnotationValueVisitor annotation value visitor} to
 * indicate that the visitor was created for a prior version of the
 * language.
 *
 * @author Joseph D. Darcy
 * @author Scott Seligman
 * @author Peter von der Ah&eacute;
 * @see javax.lang.model.element.AnnotationValueVisitor#visitUnknown
 * @since 1.6
 */
public class UnknownAnnotationValueException extends UnknownEntityException {

    private static final long serialVersionUID = 269L;

    private transient javax.lang.model.element.AnnotationValue av;
    private transient Object parameter;

    /**
     * Creates a new {@code UnknownAnnotationValueException}.  The
     * {@code p} parameter may be used to pass in an additional
     * argument with information about the context in which the
     * unknown annotation value was encountered; for example, the
     * visit methods of {@link AnnotationValueVisitor} may pass in
     * their additional parameter.
     *
     * @param av the unknown annotation value, may be {@code null}
     * @param p an additional parameter, may be {@code null}
     */
    public UnknownAnnotationValueException(javax.lang.model.element.AnnotationValue av, Object p) {
        super("Unknown annotation value: " + av);
        this.av = av;
        this.parameter = p;
    }

    /**
     * Returns the unknown annotation value.
     * The value may be unavailable if this exception has been
     * serialized and then read back in.
     *
     * @return the unknown element, or {@code null} if unavailable
     */
    public AnnotationValue getUnknownAnnotationValue() {
        return av;
    }

    /**
     * Returns the additional argument.
     *
     * @return the additional argument
     */
    public Object getArgument() {
        return parameter;
    }
}
