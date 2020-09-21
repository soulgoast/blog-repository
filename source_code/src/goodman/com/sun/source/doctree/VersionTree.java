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

/**
 *
 * A tree node for an @version block tag.
 *
 * <p>
 * &#064;version version-text
 *
 * @since 1.8
 */
@jdk.Exported
public interface VersionTree extends BlockTagTree {
    List<? extends DocTree> getBody();
}
