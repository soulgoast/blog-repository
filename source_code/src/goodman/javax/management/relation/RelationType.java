/*
 * Copyright (c) 2000, 2005, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.management.relation;

import goodman.javax.management.relation.RoleInfo;
import goodman.javax.management.relation.RoleInfoNotFoundException;
import goodman.java.util.ArrayList; // for Javadoc
import goodman.java.util.List;

import goodman.java.io.Serializable;

/**
 * The RelationType interface has to be implemented by any class expected to
 * represent a relation type.
 *
 * @since 1.5
 */
public interface RelationType extends Serializable {

    //
    // Accessors
    //

    /**
     * Returns the relation type name.
     *
     * @return the relation type name.
     */
    public String getRelationTypeName();

    /**
     * Returns the list of role definitions (ArrayList of RoleInfo objects).
     *
     * @return an {@link ArrayList} of {@link javax.management.relation.RoleInfo}.
     */
    public List<javax.management.relation.RoleInfo> getRoleInfos();

    /**
     * Returns the role info (RoleInfo object) for the given role info name
     * (null if not found).
     *
     * @param roleInfoName  role info name
     *
     * @return RoleInfo object providing role definition
     * does not exist
     *
     * @exception IllegalArgumentException  if null parameter
     * @exception RoleInfoNotFoundException  if no role info with that name in
     * relation type.
     */
    public RoleInfo getRoleInfo(String roleInfoName)
        throws IllegalArgumentException,
               RoleInfoNotFoundException;
}
