/*
 * Copyright (c) 2005, 2012, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.lang.model.type;

import goodman.java.io.ObjectInputStream;
import goodman.java.io.IOException;
import goodman.javax.lang.model.element.Element;
import goodman.javax.lang.model.type.MirroredTypesException;
import goodman.javax.lang.model.type.TypeMirror;


/**
 * Thrown when an application attempts to access the {@link Class} object
 * corresponding to a {@link javax.lang.model.type.TypeMirror}.
 *
 * @author Joseph D. Darcy
 * @author Scott Seligman
 * @author Peter von der Ah&eacute;
 * @see MirroredTypesException
 * @see Element#getAnnotation(Class)
 * @since 1.6
 */
public class MirroredTypeException extends MirroredTypesException {

    private static final long serialVersionUID = 269;

    private transient javax.lang.model.type.TypeMirror type;          // cannot be serialized

    /**
     * Constructs a new MirroredTypeException for the specified type.
     *
     * @param type  the type being accessed
     */
    public MirroredTypeException(javax.lang.model.type.TypeMirror type) {
        super("Attempt to access Class object for TypeMirror " + type.toString(), type);
        this.type = type;
    }

    /**
     * Returns the type mirror corresponding to the type being accessed.
     * The type mirror may be unavailable if this exception has been
     * serialized and then read back in.
     *
     * @return the type mirror, or {@code null} if unavailable
     */
    public TypeMirror getTypeMirror() {
        return type;
    }

    /**
     * Explicitly set all transient fields.
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        type = null;
        types = null;
    }
}
