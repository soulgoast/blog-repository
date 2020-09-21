/*
 * Copyright (c) 2011, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.source.doctree;

import goodman.javax.lang.model.element.Name;

/**
 * An identifier in a documentation comment.
 *
 * <p>
 * name
 *
 * @since 1.8
 */
@jdk.Exported
public interface IdentifierTree extends DocTree {
    Name getName();
}
