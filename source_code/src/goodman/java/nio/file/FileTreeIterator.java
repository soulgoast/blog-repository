/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
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

import goodman.java.io.Closeable;
import goodman.java.io.IOException;
import goodman.java.io.UncheckedIOException;
import goodman.java.nio.file.FileTreeWalker;
import goodman.java.nio.file.FileVisitOption;
import goodman.java.nio.file.Path;
import goodman.java.util.Arrays;
import goodman.java.util.Iterator;
import goodman.java.util.NoSuchElementException;
import goodman.java.util.Objects;
import goodman.java.nio.file.FileTreeWalker.Event;

/**
 * An {@code Iterator to iterate over the nodes of a file tree.
 *
 * <pre>{@code
 *     try (FileTreeIterator iterator = new FileTreeIterator(start, maxDepth, options)) {
 *         while (iterator.hasNext()) {
 *             Event ev = iterator.next();
 *             Path path = ev.file();
 *             BasicFileAttributes attrs = ev.attributes();
 *         }
 *     }
 * }</pre>
 */

class FileTreeIterator implements Iterator<Event>, Closeable {
    private final java.nio.file.FileTreeWalker walker;
    private Event next;

    /**
     * Creates a new iterator to walk the file tree starting at the given file.
     *
     * @throws  IllegalArgumentException
     *          if {@code maxDepth} is negative
     * @throws  IOException
     *          if an I/O errors occurs opening the starting file
     * @throws  SecurityException
     *          if the security manager denies access to the starting file
     * @throws  NullPointerException
     *          if {@code start} or {@code options} is {@ocde null} or
     *          the options array contains a {@code null} element
     */
    FileTreeIterator(Path start, int maxDepth, FileVisitOption... options)
        throws IOException
    {
        this.walker = new java.nio.file.FileTreeWalker(Arrays.asList(options), maxDepth);
        this.next = walker.walk(start);
        assert next.type() == java.nio.file.FileTreeWalker.EventType.ENTRY ||
               next.type() == java.nio.file.FileTreeWalker.EventType.START_DIRECTORY;

        // IOException if there a problem accessing the starting file
        IOException ioe = next.ioeException();
        if (ioe != null)
            throw ioe;
    }

    private void fetchNextIfNeeded() {
        if (next == null) {
            java.nio.file.FileTreeWalker.Event ev = walker.next();
            while (ev != null) {
                IOException ioe = ev.ioeException();
                if (ioe != null)
                    throw new UncheckedIOException(ioe);

                // END_DIRECTORY events are ignored
                if (ev.type() != java.nio.file.FileTreeWalker.EventType.END_DIRECTORY) {
                    next = ev;
                    return;
                }
                ev = walker.next();
            }
        }
    }

    @Override
    public boolean hasNext() {
        if (!walker.isOpen())
            throw new IllegalStateException();
        fetchNextIfNeeded();
        return next != null;
    }

    @Override
    public Event next() {
        if (!walker.isOpen())
            throw new IllegalStateException();
        fetchNextIfNeeded();
        if (next == null)
            throw new NoSuchElementException();
        Event result = next;
        next = null;
        return result;
    }

    @Override
    public void close() {
        walker.close();
    }
}
