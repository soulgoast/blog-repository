/*
 * Copyright (c) 2005, 2008, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.jmx.mbeanserver;

import com.sun.jmx.mbeanserver.MBeanIntrospector;
import com.sun.jmx.mbeanserver.MBeanSupport;
import com.sun.jmx.mbeanserver.StandardMBeanIntrospector;

import goodman.java.lang.reflect.Method;

import goodman.javax.management.MBeanInfo;
import goodman.javax.management.MBeanServer;
import goodman.javax.management.NotCompliantMBeanException;
import goodman.javax.management.ObjectName;

/**
 * Base class for Standard MBeans.
 *
 * @since 1.6
 */
public class StandardMBeanSupport extends MBeanSupport<Method> {

    /**
     * <p>Construct a Standard MBean that wraps the given resource using the
     * given Standard MBean interface.</p>
     *
     * @param resource the underlying resource for the new MBean.
     * @param mbeanInterfaceType the class or interface to be used to determine
     *       the MBean's management interface.  An interface if this is a
     *       classic Standard MBean; a class if this is a {@code @ManagedResource}.
     * @param <T> a type parameter that allows the compiler to check
     *       that {@code resource} implements {@code mbeanInterfaceType},
     *       provided that {@code mbeanInterfaceType} is a class constant like
     *       {@code SomeMBean.class}.
     * @throws IllegalArgumentException if {@code resource} is null or
     *       if it does not implement the class {@code mbeanInterfaceType} or if
     *       that class is not a valid Standard MBean interface.
     */
    public <T> StandardMBeanSupport(T resource, Class<T> mbeanInterfaceType)
            throws NotCompliantMBeanException {
        super(resource, mbeanInterfaceType);
    }

    @Override
    com.sun.jmx.mbeanserver.MBeanIntrospector<Method> getMBeanIntrospector() {
        return StandardMBeanIntrospector.getInstance();
    }

    @Override
    Object getCookie() {
        return null;
    }

    @Override
    public void register(MBeanServer mbs, ObjectName name) {}

    @Override
    public void unregister() {}

    /* Standard MBeans that are NotificationBroadcasters can return a different
     * MBeanNotificationInfo[] every time getMBeanInfo() is called, so we have
     * to reconstruct this MBeanInfo if necessary.
     */
    @Override
    public MBeanInfo getMBeanInfo() {
        MBeanInfo mbi = super.getMBeanInfo();
        Class<?> resourceClass = getResource().getClass();
        if (StandardMBeanIntrospector.isDefinitelyImmutableInfo(resourceClass))
            return mbi;
        return new MBeanInfo(mbi.getClassName(), mbi.getDescription(),
                mbi.getAttributes(), mbi.getConstructors(),
                mbi.getOperations(),
                com.sun.jmx.mbeanserver.MBeanIntrospector.findNotifications(getResource()),
                mbi.getDescriptor());
    }
}
