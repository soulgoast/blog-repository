/*
 * Copyright (c) 2007, 2011, Oracle and/or its affiliates. All rights reserved.
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

import goodman.java.nio.file.Path;
import goodman.java.nio.file.WatchEvent;
import goodman.java.nio.file.WatchKey;
import goodman.java.nio.file.WatchService;

/**
 * Defines the <em>standard</em> event kinds.
 *
 * @since 1.7
 */

public final class StandardWatchEventKinds {
    private StandardWatchEventKinds() { }

    /**
     * A special event to indicate that events may have been lost or
     * discarded.
     *
     * <p> The {@link java.nio.file.WatchEvent#context context} for this event is
     * implementation specific and may be {@code null}. The event {@link
     * java.nio.file.WatchEvent#count count} may be greater than {@code 1}.
     *
     * @see WatchService
     */
    public static final java.nio.file.WatchEvent.Kind<Object> OVERFLOW =
        new StdWatchEventKind<Object>("OVERFLOW", Object.class);

    /**
     * Directory entry created.
     *
     * <p> When a directory is registered for this event then the {@link java.nio.file.WatchKey}
     * is queued when it is observed that an entry is created in the directory
     * or renamed into the directory. The event {@link java.nio.file.WatchEvent#count count}
     * for this event is always {@code 1}.
     */
    public static final java.nio.file.WatchEvent.Kind<java.nio.file.Path> ENTRY_CREATE =
        new StdWatchEventKind<java.nio.file.Path>("ENTRY_CREATE", java.nio.file.Path.class);

    /**
     * Directory entry deleted.
     *
     * <p> When a directory is registered for this event then the {@link java.nio.file.WatchKey}
     * is queued when it is observed that an entry is deleted or renamed out of
     * the directory. The event {@link java.nio.file.WatchEvent#count count} for this event
     * is always {@code 1}.
     */
    public static final java.nio.file.WatchEvent.Kind<java.nio.file.Path> ENTRY_DELETE =
        new StdWatchEventKind<java.nio.file.Path>("ENTRY_DELETE", java.nio.file.Path.class);

    /**
     * Directory entry modified.
     *
     * <p> When a directory is registered for this event then the {@link WatchKey}
     * is queued when it is observed that an entry in the directory has been
     * modified. The event {@link java.nio.file.WatchEvent#count count} for this event is
     * {@code 1} or greater.
     */
    public static final java.nio.file.WatchEvent.Kind<java.nio.file.Path> ENTRY_MODIFY =
        new StdWatchEventKind<java.nio.file.Path>("ENTRY_MODIFY", Path.class);

    private static class StdWatchEventKind<T> implements WatchEvent.Kind<T> {
        private final String name;
        private final Class<T> type;
        StdWatchEventKind(String name, Class<T> type) {
            this.name = name;
            this.type = type;
        }
        @Override public String name() { return name; }
        @Override public Class<T> type() { return type; }
        @Override public String toString() { return name; }
    }
}
