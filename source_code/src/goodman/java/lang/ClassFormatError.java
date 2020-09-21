/*
 * Copyright (c) 1994, 2008, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.lang;

/**
 * Thrown when the Java Virtual Machine attempts to read a class
 * file and determines that the file is malformed or otherwise cannot
 * be interpreted as a class file.
 *
 * @author  unascribed
 * @since   JDK1.0
 */
public
class ClassFormatError extends java.lang.LinkageError {
    private static final long serialVersionUID = -8420114879011949195L;

    /**
     * Constructs a <code>ClassFormatError</code> with no detail message.
     */
    public ClassFormatError() {
        super();
    }

    /**
     * Constructs a <code>ClassFormatError</code> with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public ClassFormatError(java.lang.String s) {
        super(s);
    }
}
