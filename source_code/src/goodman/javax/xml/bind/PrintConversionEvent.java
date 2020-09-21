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

import goodman.javax.xml.bind.Marshaller;
import goodman.javax.xml.bind.ValidationEvent;
import goodman.javax.xml.bind.ValidationEventHandler;

/**
 * This event indicates that a problem was encountered while converting data
 * from the Java content tree into its lexical representation.
 *
 * @author <ul><li>Ryan Shoemaker, Sun Microsystems, Inc.</li><li>Kohsuke Kawaguchi, Sun Microsystems, Inc.</li><li>Joe Fialli, Sun Microsystems, Inc.</li></ul>
 * @see javax.xml.bind.ValidationEvent
 * @see ValidationEventHandler
 * @see Marshaller
 * @since JAXB1.0
 */
public interface PrintConversionEvent extends ValidationEvent {

}
