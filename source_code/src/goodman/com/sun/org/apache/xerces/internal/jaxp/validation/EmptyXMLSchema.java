/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright 2005 The Apache Software Foundation.
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

package goodman.com.sun.org.apache.xerces.internal.jaxp.validation;

import com.sun.org.apache.xerces.internal.jaxp.validation.AbstractXMLSchema;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarDescription;
import com.sun.org.apache.xerces.internal.xni.grammars.XMLGrammarPool;

/**
 * <p>Implementation of Schema for W3C XML Schemas
 * which contains no schema components.</p>
 *
 * @author Michael Glavassevich, IBM
 * @version $Id: EmptyXMLSchema.java,v 1.6 2010-11-01 04:40:08 joehw Exp $
 */
final class EmptyXMLSchema extends AbstractXMLSchema implements XMLGrammarPool {

    /** Zero length grammar array. */
    private static final Grammar [] ZERO_LENGTH_GRAMMAR_ARRAY = new Grammar [0];

    public EmptyXMLSchema() {}

    /*
     * XMLGrammarPool methods
     */

    public Grammar[] retrieveInitialGrammarSet(String grammarType) {
        return ZERO_LENGTH_GRAMMAR_ARRAY;
    }

    public void cacheGrammars(String grammarType, Grammar[] grammars) {}

    public Grammar retrieveGrammar(XMLGrammarDescription desc) {
        return null;
    }

    public void lockPool() {}

    public void unlockPool() {}

    public void clear() {}

    /*
     * XSGrammarPoolContainer methods
     */

    public XMLGrammarPool getGrammarPool() {
        return this;
    }

    public boolean isFullyComposed() {
        return true;
    }

} // EmptyXMLSchema
