/*
 * Copyright (c) 2004, 2013, Oracle and/or its affiliates. All rights reserved.
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

import goodman.javax.xml.bind.Unmarshaller;
import goodman.javax.xml.bind.ValidationEvent;
import goodman.javax.xml.bind.ValidationEventHandler;

/**
 * This event indicates that a problem was encountered while converting a
 * string from the XML data into a value of the target Java data type.
 *
 * @author <ul><li>Ryan Shoemaker, Sun Microsystems, Inc.</li><li>Kohsuke Kawaguchi, Sun Microsystems, Inc.</li><li>Joe Fialli, Sun Microsystems, Inc.</li></ul>
 * @see javax.xml.bind.ValidationEvent
 * @see ValidationEventHandler
 * @see Unmarshaller
 * @since JAXB1.0
 */
public interface ParseConversionEvent extends ValidationEvent {

}
