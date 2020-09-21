/*
 * Copyright (c) 2006, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.jmx.remote.internal;

import goodman.java.util.List;
import goodman.javax.management.Notification;
import goodman.javax.management.ObjectName;
import goodman.javax.management.remote.TargetedNotification;

public interface NotificationBufferFilter {
    /**
     * Add the given notification coming from the given MBean to the list
     * iff it matches this filter's rules.
     */
    public void apply(List<TargetedNotification> targetedNotifs,
                      ObjectName source, Notification notif);
}
