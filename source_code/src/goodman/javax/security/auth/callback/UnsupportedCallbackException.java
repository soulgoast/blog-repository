/*
 * Copyright (c) 1999, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.security.auth.callback;

import goodman.javax.security.auth.callback.Callback;

/**
 * Signals that a {@code CallbackHandler} does not
 * recognize a particular {@code Callback}.
 *
 */
public class UnsupportedCallbackException extends Exception {

    private static final long serialVersionUID = -6873556327655666839L;

    /**
     * @serial
     */
    private javax.security.auth.callback.Callback callback;

    /**
     * Constructs a {@code UnsupportedCallbackException}
     * with no detail message.
     *
     * <p>
     *
     * @param callback the unrecognized {@code Callback}.
     */
    public UnsupportedCallbackException(javax.security.auth.callback.Callback callback) {
        super();
        this.callback = callback;
    }

    /**
     * Constructs a UnsupportedCallbackException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * <p>
     *
     * @param callback the unrecognized {@code Callback}. <p>
     *
     * @param msg the detail message.
     */
    public UnsupportedCallbackException(javax.security.auth.callback.Callback callback, String msg) {
        super(msg);
        this.callback = callback;
    }

    /**
     * Get the unrecognized {@code Callback}.
     *
     * <p>
     *
     * @return the unrecognized {@code Callback}.
     */
    public Callback getCallback() {
        return callback;
    }
}
