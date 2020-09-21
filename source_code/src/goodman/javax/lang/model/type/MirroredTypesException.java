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

import goodman.java.util.ArrayList;
import goodman.java.util.List;
import goodman.java.util.Collections;
import goodman.java.io.ObjectInputStream;
import goodman.java.io.IOException;
import goodman.javax.lang.model.element.Element;
import goodman.javax.lang.model.type.MirroredTypeException;
import goodman.javax.lang.model.type.TypeMirror;


/**
 * Thrown when an application attempts to access a sequence of {@link
 * Class} objects each corresponding to a {@link javax.lang.model.type.TypeMirror}.
 *
 * @author Joseph D. Darcy
 * @author Scott Seligman
 * @author Peter von der Ah&eacute;
 * @see MirroredTypeException
 * @see Element#getAnnotation(Class)
 * @since 1.6
 */
public class MirroredTypesException extends RuntimeException {

    private static final long serialVersionUID = 269;

    transient List<? extends javax.lang.model.type.TypeMirror> types; // cannot be serialized

    /*
     * Trusted constructor to be called by MirroredTypeException.
     */
    MirroredTypesException(String message, javax.lang.model.type.TypeMirror type) {
        super(message);
        List<javax.lang.model.type.TypeMirror> tmp = (new ArrayList<javax.lang.model.type.TypeMirror>());
        tmp.add(type);
        types = Collections.unmodifiableList(tmp);
    }

    /**
     * Constructs a new MirroredTypesException for the specified types.
     *
     * @param types  the types being accessed
     */
    public MirroredTypesException(List<? extends javax.lang.model.type.TypeMirror> types) {
        super("Attempt to access Class objects for TypeMirrors " +
              (types = // defensive copy
               new ArrayList<javax.lang.model.type.TypeMirror>(types)).toString() );
        this.types = Collections.unmodifiableList(types);
    }

    /**
     * Returns the type mirrors corresponding to the types being accessed.
     * The type mirrors may be unavailable if this exception has been
     * serialized and then read back in.
     *
     * @return the type mirrors in construction order, or {@code null} if unavailable
     */
    public List<? extends TypeMirror> getTypeMirrors() {
        return types;
    }

    /**
     * Explicitly set all transient fields.
     */
    private void readObject(ObjectInputStream s)
        throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        types = null;
    }
}
