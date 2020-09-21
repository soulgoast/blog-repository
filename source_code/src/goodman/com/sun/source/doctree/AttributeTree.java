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

import goodman.java.util.List;
import goodman.javax.lang.model.element.Name;

/**
 * A tree node for an attribute in an HTML element.
 *
 * @since 1.8
 */
@jdk.Exported
public interface AttributeTree extends DocTree {
    @jdk.Exported
    enum ValueKind { EMPTY, UNQUOTED, SINGLE, DOUBLE };

    Name getName();
    ValueKind getValueKind();
    List<? extends DocTree> getValue();
}
