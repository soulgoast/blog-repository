/*
 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.xml.bind;

import goodman.javax.xml.bind.ValidationEvent;
import goodman.javax.xml.bind.ValidationEventHandler;
import goodman.javax.xml.bind.Validator;

/**
 * This event indicates that a problem was encountered resolving an ID/IDREF.
 *
 *
 * @author <ul><li>Ryan Shoemaker, Sun Microsystems, Inc.</li><li>Kohsuke Kawaguchi, Sun Microsystems, Inc.</li><li>Joe Fialli, Sun Microsystems, Inc.</li></ul>
 * @see Validator
 * @see ValidationEventHandler
 * @since JAXB1.0
 */
public interface NotIdentifiableEvent extends ValidationEvent {

}
