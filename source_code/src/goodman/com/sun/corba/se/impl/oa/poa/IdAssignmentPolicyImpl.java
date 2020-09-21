/*
 * Copyright (c) 1997, 2003, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.impl.oa.poa;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;

final class IdAssignmentPolicyImpl
extends LocalObject
implements IdAssignmentPolicy {

    public IdAssignmentPolicyImpl(IdAssignmentPolicyValue value) {
        this.value = value;
    }

    public IdAssignmentPolicyValue value() {
        return value;
    }

    public int policy_type()
    {
        return ID_ASSIGNMENT_POLICY_ID.value ;
    }

    public Policy copy() {
        return new IdAssignmentPolicyImpl(value);
    }

    public void destroy() {
        value = null;
    }

    private IdAssignmentPolicyValue value;

    public String toString()
    {
        return "IdAssignmentPolicy[" +
            ((value.value() == IdAssignmentPolicyValue._USER_ID) ?
                "USER_ID" : "SYSTEM_ID" + "]") ;
    }
}