/*
 * Copyright (c) 2004, 2006, Oracle and/or its affiliates. All rights reserved.
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

package goodman.org.omg.CORBA;

import org.omg.CORBA.CompletionStatus;
import org.omg.CORBA.SystemException;

/**
 * The <code>INVALID_ACTIVITY</code> system exception may be raised on the
 * Activity or Transaction services' resume methods if a transaction or
 * Activity is resumed in a context different to that from which it was
 * suspended. It is also raised when an attempted invocation is made that
 * is incompatible with the Activity's current state.
 *
 * @see <A href="../../../../technotes/guides/idl/jidlExceptions.html">documentation on
 *      Java&nbsp;IDL exceptions</A>
 * @since   J2SE 1.5
 */

public final class INVALID_ACTIVITY extends SystemException {

    /**
     * Constructs an <code>INVALID_ACTIVITY</code> exception with
     * minor code set to 0 and CompletionStatus set to COMPLETED_NO.
     */
    public INVALID_ACTIVITY() {
        this("");
    }

    /**
     * Constructs an <code>INVALID_ACTIVITY</code> exception with the
     * specified message.
     *
     * @param detailMessage string containing a detailed message.
     */
    public INVALID_ACTIVITY(String detailMessage) {
        this(detailMessage, 0, org.omg.CORBA.CompletionStatus.COMPLETED_NO);
    }

    /**
     * Constructs an <code>INVALID_ACTIVITY</code> exception with the
     * specified minor code and completion status.
     *
     * @param minorCode minor code.
     * @param completionStatus completion status.
     */
    public INVALID_ACTIVITY(int minorCode,
                            org.omg.CORBA.CompletionStatus completionStatus) {
        this("", minorCode, completionStatus);
    }

    /**
     * Constructs an <code>INVALID_ACTIVITY</code> exception with the
     * specified message, minor code, and completion status.
     *
     * @param detailMessage string containing a detailed message.
     * @param minorCode minor code.
     * @param completionStatus completion status.
     */
    public INVALID_ACTIVITY(String detailMessage,
                            int minorCode,
                            CompletionStatus completionStatus) {
        super(detailMessage, minorCode, completionStatus);
    }
}
