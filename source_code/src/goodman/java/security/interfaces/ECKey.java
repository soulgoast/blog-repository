/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
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
package goodman.java.security.interfaces;

import goodman.java.security.spec.ECParameterSpec;

/**
 * The interface to an elliptic curve (EC) key.
 *
 * @author Valerie Peng
 *
 * @since 1.5
 */
public interface ECKey {
    /**
     * Returns the domain parameters associated
     * with this key. The domain parameters are
     * either explicitly specified or implicitly
     * created during key generation.
     * @return the associated domain parameters.
     */
    ECParameterSpec getParams();
}
