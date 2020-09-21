/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright 2001, 2002,2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package goodman.com.sun.org.apache.xerces.internal.xni;

import com.sun.org.apache.xerces.internal.xni.Augmentations;
import com.sun.org.apache.xerces.internal.xni.NamespaceContext;
import com.sun.org.apache.xerces.internal.xni.QName;
import com.sun.org.apache.xerces.internal.xni.XMLAttributes;
import com.sun.org.apache.xerces.internal.xni.XMLDocumentHandler;
import com.sun.org.apache.xerces.internal.xni.XMLLocator;
import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.org.apache.xerces.internal.xni.XMLString;
import com.sun.org.apache.xerces.internal.xni.XNIException;

/**
 * This handler interface contains methods necessary to receive
 * information about document elements and content.
 * <p>
 * <strong>Note:</strong> Some of these methods overlap methods
 * found in the XMLDocumentHandler interface.
 *
 * @see XMLDocumentHandler
 *
 * @author Andy Clark, IBM
 */
public interface XMLDocumentFragmentHandler {

    //
    // XMLDocumentFragmentHandler methods
    //

    /**
     * The start of the document fragment.
     *
     * @param locator          The document locator, or null if the
     *                         document location cannot be reported
     *                         during the parsing of this fragment.
     *                         However, it is <em>strongly</em>
     *                         recommended that a locator be supplied
     *                         that can at least report the base
     *                         system identifier.
     * @param namespaceContext The namespace context in effect at the
     *                         start of this document fragment. This
     *                         object only represents the current context.
     *                         Implementors of this class are responsible
     *                         for copying the namespace bindings from the
     *                         the current context (and its parent contexts)
     *                         if that information is important.
     * @param augmentations    Additional information that may include infoset
     *                         augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void startDocumentFragment(XMLLocator locator,
                                      NamespaceContext namespaceContext,
                                      com.sun.org.apache.xerces.internal.xni.Augmentations augmentations)
        throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * This method notifies the start of a general entity.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param name     The name of the general entity.
     * @param identifier The resource identifier.
     * @param encoding The auto-detected IANA encoding name of the entity
     *                 stream. This value will be null in those situations
     *                 where the entity encoding is not auto-detected (e.g.
     *                 internal entities or a document entity that is
     *                 parsed from a java.io.Reader).
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void startGeneralEntity(String name,
                                   XMLResourceIdentifier identifier,
                                   String encoding,
                                   com.sun.org.apache.xerces.internal.xni.Augmentations augmentations) throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * Notifies of the presence of a TextDecl line in an entity. If present,
     * this method will be called immediately following the startEntity call.
     * <p>
     * <strong>Note:</strong> This method will never be called for the
     * document entity; it is only called for external general entities
     * referenced in document content.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param version  The XML version, or null if not specified.
     * @param encoding The IANA encoding name of the entity.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void textDecl(String version, String encoding,
                         com.sun.org.apache.xerces.internal.xni.Augmentations augmentations) throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * This method notifies the end of a general entity.
     * <p>
     * <strong>Note:</strong> This method is not called for entity references
     * appearing as part of attribute values.
     *
     * @param name The name of the general entity.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void endGeneralEntity(String name, com.sun.org.apache.xerces.internal.xni.Augmentations augmentations)
        throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * A comment.
     *
     * @param text The text in the comment.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by application to signal an error.
     */
    public void comment(com.sun.org.apache.xerces.internal.xni.XMLString text, com.sun.org.apache.xerces.internal.xni.Augmentations augmentations)
        throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * A processing instruction. Processing instructions consist of a
     * target name and, optionally, text data. The data is only meaningful
     * to the application.
     * <p>
     * Typically, a processing instruction's data will contain a series
     * of pseudo-attributes. These pseudo-attributes follow the form of
     * element attributes but are <strong>not</strong> parsed or presented
     * to the application as anything other than text. The application is
     * responsible for parsing the data.
     *
     * @param target The target.
     * @param data   The data or null if none specified.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void processingInstruction(String target, com.sun.org.apache.xerces.internal.xni.XMLString data,
                                      com.sun.org.apache.xerces.internal.xni.Augmentations augmentations)
        throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * The start of an element.
     *
     * @param element    The name of the element.
     * @param attributes The element attributes.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void startElement(com.sun.org.apache.xerces.internal.xni.QName element, com.sun.org.apache.xerces.internal.xni.XMLAttributes attributes,
                             com.sun.org.apache.xerces.internal.xni.Augmentations augmentations) throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * An empty element.
     *
     * @param element    The name of the element.
     * @param attributes The element attributes.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void emptyElement(com.sun.org.apache.xerces.internal.xni.QName element, XMLAttributes attributes,
                             com.sun.org.apache.xerces.internal.xni.Augmentations augmentations) throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * Character content.
     *
     * @param text The content.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void characters(com.sun.org.apache.xerces.internal.xni.XMLString text, com.sun.org.apache.xerces.internal.xni.Augmentations augmentations)
        throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * Ignorable whitespace. For this method to be called, the document
     * source must have some way of determining that the text containing
     * only whitespace characters should be considered ignorable. For
     * example, the validator can determine if a length of whitespace
     * characters in the document are ignorable based on the element
     * content model.
     *
     * @param text The ignorable whitespace.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void ignorableWhitespace(XMLString text,
                                    com.sun.org.apache.xerces.internal.xni.Augmentations augmentations)
        throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * The end of an element.
     *
     * @param element The name of the element.
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void endElement(QName element, com.sun.org.apache.xerces.internal.xni.Augmentations augmentations)
        throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * The start of a CDATA section.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void startCDATA(com.sun.org.apache.xerces.internal.xni.Augmentations augmentations) throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * The end of a CDATA section.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void endCDATA(com.sun.org.apache.xerces.internal.xni.Augmentations augmentations) throws com.sun.org.apache.xerces.internal.xni.XNIException;

    /**
     * The end of the document fragment.
     *
     * @param augmentations Additional information that may include infoset
     *                      augmentations.
     *
     * @throws com.sun.org.apache.xerces.internal.xni.XNIException Thrown by handler to signal an error.
     */
    public void endDocumentFragment(Augmentations augmentations)
        throws XNIException;

} // interface XMLDocumentFragmentHandler
