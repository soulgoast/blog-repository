/*
 * Copyright (c) 2003, 2006, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.accessibility;


import goodman.java.util.*;
import goodman.java.awt.*;
import goodman.javax.accessibility.Accessible;
import goodman.javax.accessibility.AccessibleContext;
import goodman.javax.accessibility.AccessibleText;
import goodman.javax.accessibility.AccessibleTextSequence;
import goodman.javax.swing.text.*;


/**
 * <P>The AccessibleExtendedText interface contains additional methods
 * not provided by the AccessibleText interface
 *
 * Applications can determine if an object supports the AccessibleExtendedText
 * interface by first obtaining its AccessibleContext (see {@link javax.accessibility.Accessible})
 * and then calling the {@link javax.accessibility.AccessibleContext#getAccessibleText} method of
 * AccessibleContext.  If the return value is an instance of
 * AccessibleExtendedText, the object supports this interface.
 *
 * @see javax.accessibility.Accessible
 * @see Accessible#getAccessibleContext
 * @see javax.accessibility.AccessibleContext
 * @see AccessibleContext#getAccessibleText
 *
 * @author       Peter Korn
 * @author       Lynn Monsanto
 * @since 1.5
 */
public interface AccessibleExtendedText {

    /**
     * Constant used to indicate that the part of the text that should be
     * retrieved is a line of text.
     *
     * @see javax.accessibility.AccessibleText#getAtIndex
     * @see javax.accessibility.AccessibleText#getAfterIndex
     * @see javax.accessibility.AccessibleText#getBeforeIndex
     */
    public static final int LINE = 4; // BugID: 4849720

    /**
     * Constant used to indicate that the part of the text that should be
     * retrieved is contiguous text with the same text attributes.
     *
     * @see javax.accessibility.AccessibleText#getAtIndex
     * @see javax.accessibility.AccessibleText#getAfterIndex
     * @see javax.accessibility.AccessibleText#getBeforeIndex
     */
    public static final int ATTRIBUTE_RUN = 5; // BugID: 4849720

    /**
     * Returns the text between two indices
     *
     * @param startIndex the start index in the text
     * @param endIndex the end index in the text
     * @return the text string if the indices are valid.
     * Otherwise, null is returned.
     */
    public String getTextRange(int startIndex, int endIndex);

    /**
     * Returns the <code>AccessibleTextSequence</code> at a given index.
     *
     * @param part the <code>CHARACTER</code>, <code>WORD</code>,
     * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code>
     * to retrieve
     * @param index an index within the text
     * @return an <code>AccessibleTextSequence</code> specifying the text
     * if part and index are valid.  Otherwise, null is returned.
     *
     * @see javax.accessibility.AccessibleText#CHARACTER
     * @see javax.accessibility.AccessibleText#WORD
     * @see javax.accessibility.AccessibleText#SENTENCE
     */
    public AccessibleTextSequence getTextSequenceAt(int part, int index);

    /**
     * Returns the <code>AccessibleTextSequence</code> after a given index.
     *
     * @param part the <code>CHARACTER</code>, <code>WORD</code>,
     * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code>
     * to retrieve
     * @param index an index within the text
     * @return an <code>AccessibleTextSequence</code> specifying the text
     * if part and index are valid.  Otherwise, null is returned.
     *
     * @see javax.accessibility.AccessibleText#CHARACTER
     * @see javax.accessibility.AccessibleText#WORD
     * @see javax.accessibility.AccessibleText#SENTENCE
     */
    public AccessibleTextSequence getTextSequenceAfter(int part, int index);

    /**
     * Returns the <code>AccessibleTextSequence</code> before a given index.
     *
     * @param part the <code>CHARACTER</code>, <code>WORD</code>,
     * <code>SENTENCE</code>, <code>LINE</code> or <code>ATTRIBUTE_RUN</code>
     * to retrieve
     * @param index an index within the text
     * @return an <code>AccessibleTextSequence</code> specifying the text
     * if part and index are valid.  Otherwise, null is returned.
     *
     * @see javax.accessibility.AccessibleText#CHARACTER
     * @see javax.accessibility.AccessibleText#WORD
     * @see AccessibleText#SENTENCE
     */
    public AccessibleTextSequence getTextSequenceBefore(int part, int index);

    /**
     * Returns the bounding rectangle of the text between two indices.
     *
     * @param startIndex the start index in the text
     * @param endIndex the end index in the text
     * @return the bounding rectangle of the text if the indices are valid.
     * Otherwise, null is returned.
     */
    public Rectangle getTextBounds(int startIndex, int endIndex);
}
