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
 * A tree node for an HTML entity.
 *
 * <p>
 * &amp; name ;
 *
 * @since 1.8
 */
@jdk.Exported
public interface EntityTree extends DocTree {
    Name getName();
}
