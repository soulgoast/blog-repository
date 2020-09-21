/*
 * Copyright (c) 2007, 2009, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.nio.file;

import goodman.java.nio.file.FileSystem;

/**
 * Unchecked exception thrown when an attempt is made to update an object
 * associated with a {@link FileSystem#isReadOnly() read-only} {@code FileSystem}.
 */

public class ReadOnlyFileSystemException
    extends UnsupportedOperationException
{
    static final long serialVersionUID = -6822409595617487197L;

    /**
     * Constructs an instance of this class.
     */
    public ReadOnlyFileSystemException() {
    }
}
