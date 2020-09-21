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

import goodman.java.nio.file.FileVisitor;
import goodman.java.nio.file.Files;

/**
 * The result type of a {@link java.nio.file.FileVisitor FileVisitor}.
 *
 * @since 1.7
 *
 * @see Files#walkFileTree
 */

public enum FileVisitResult {
    /**
     * Continue. When returned from a {@link java.nio.file.FileVisitor#preVisitDirectory
     * preVisitDirectory} method then the entries in the directory should also
     * be visited.
     */
    CONTINUE,
    /**
     * Terminate.
     */
    TERMINATE,
    /**
     * Continue without visiting the entries in this directory. This result
     * is only meaningful when returned from the {@link
     * java.nio.file.FileVisitor#preVisitDirectory preVisitDirectory} method; otherwise
     * this result type is the same as returning {@link #CONTINUE}.
     */
    SKIP_SUBTREE,
    /**
     * Continue without visiting the <em>siblings</em> of this file or directory.
     * If returned from the {@link java.nio.file.FileVisitor#preVisitDirectory
     * preVisitDirectory} method then the entries in the directory are also
     * skipped and the {@link FileVisitor#postVisitDirectory postVisitDirectory}
     * method is not invoked.
     */
    SKIP_SIBLINGS;
}
