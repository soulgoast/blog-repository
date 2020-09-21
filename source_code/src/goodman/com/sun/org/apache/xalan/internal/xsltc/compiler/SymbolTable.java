/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
 */
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * $Id: SymbolTable.java,v 1.5 2005/09/28 13:48:16 pvedula Exp $
 */

package goodman.com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.*;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Key;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Param;
import com.sun.org.apache.xalan.internal.xsltc.compiler.QName;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Variable;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import goodman.java.util.HashMap;
import goodman.java.util.Map;
import goodman.java.util.Stack;
import goodman.java.util.StringTokenizer;
import goodman.java.util.Vector;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 * @author Morten Jorgensen
 */
final class SymbolTable {

    // These maps are used for all stylesheets
    private final Map<String, com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet> _stylesheets = new HashMap<>();
    private final Map<String, Vector> _primops     = new HashMap<>();

    // These maps are used for some stylesheets
    private Map<String, VariableBase> _variables = null;
    private Map<String, com.sun.org.apache.xalan.internal.xsltc.compiler.Template> _templates = null;
    private Map<String, AttributeSet> _attributeSets = null;
    private Map<String, String> _aliases = null;
    private Map<String, Integer> _excludedURI = null;
    private Stack<Map<String, Integer>>     _excludedURIStack = null;
    private Map<String, DecimalFormatting> _decimalFormats = null;
    private Map<String, com.sun.org.apache.xalan.internal.xsltc.compiler.Key> _keys = null;

    public DecimalFormatting getDecimalFormatting(com.sun.org.apache.xalan.internal.xsltc.compiler.QName name) {
        if (_decimalFormats == null) return null;
        return(_decimalFormats.get(name.getStringRep()));
    }

    public void addDecimalFormatting(com.sun.org.apache.xalan.internal.xsltc.compiler.QName name, DecimalFormatting symbols) {
        if (_decimalFormats == null) _decimalFormats = new HashMap<>();
        _decimalFormats.put(name.getStringRep(), symbols);
    }

    public com.sun.org.apache.xalan.internal.xsltc.compiler.Key getKey(com.sun.org.apache.xalan.internal.xsltc.compiler.QName name) {
        if (_keys == null) return null;
        return _keys.get(name.getStringRep());
    }

    public void addKey(com.sun.org.apache.xalan.internal.xsltc.compiler.QName name, com.sun.org.apache.xalan.internal.xsltc.compiler.Key key) {
        if (_keys == null) _keys = new HashMap<>();
        _keys.put(name.getStringRep(), key);
    }

    public com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet addStylesheet(com.sun.org.apache.xalan.internal.xsltc.compiler.QName name, com.sun.org.apache.xalan.internal.xsltc.compiler.Stylesheet node) {
        return _stylesheets.put(name.getStringRep(), node);
    }

    public Stylesheet lookupStylesheet(com.sun.org.apache.xalan.internal.xsltc.compiler.QName name) {
        return _stylesheets.get(name.getStringRep());
    }

    public com.sun.org.apache.xalan.internal.xsltc.compiler.Template addTemplate(com.sun.org.apache.xalan.internal.xsltc.compiler.Template template) {
        final com.sun.org.apache.xalan.internal.xsltc.compiler.QName name = template.getName();
        if (_templates == null) _templates = new HashMap<>();
        return _templates.put(name.getStringRep(), template);
    }

    public Template lookupTemplate(com.sun.org.apache.xalan.internal.xsltc.compiler.QName name) {
        if (_templates == null) return null;
        return _templates.get(name.getStringRep());
    }

    public com.sun.org.apache.xalan.internal.xsltc.compiler.Variable addVariable(com.sun.org.apache.xalan.internal.xsltc.compiler.Variable variable) {
        if (_variables == null) _variables = new HashMap<>();
        final String name = variable.getName().getStringRep();
        return (com.sun.org.apache.xalan.internal.xsltc.compiler.Variable)_variables.put(name, variable);
    }

    public com.sun.org.apache.xalan.internal.xsltc.compiler.Param addParam(com.sun.org.apache.xalan.internal.xsltc.compiler.Param parameter) {
        if (_variables == null) _variables = new HashMap<>();
        final String name = parameter.getName().getStringRep();
        return (com.sun.org.apache.xalan.internal.xsltc.compiler.Param)_variables.put(name, parameter);
    }

    public com.sun.org.apache.xalan.internal.xsltc.compiler.Variable lookupVariable(com.sun.org.apache.xalan.internal.xsltc.compiler.QName qname) {
        if (_variables == null) return null;
        final String name = qname.getStringRep();
        final VariableBase obj = _variables.get(name);
        return obj instanceof com.sun.org.apache.xalan.internal.xsltc.compiler.Variable ? (com.sun.org.apache.xalan.internal.xsltc.compiler.Variable)obj : null;
    }

    public com.sun.org.apache.xalan.internal.xsltc.compiler.Param lookupParam(com.sun.org.apache.xalan.internal.xsltc.compiler.QName qname) {
        if (_variables == null) return null;
        final String name = qname.getStringRep();
        final VariableBase obj = _variables.get(name);
        return obj instanceof com.sun.org.apache.xalan.internal.xsltc.compiler.Param ? (com.sun.org.apache.xalan.internal.xsltc.compiler.Param)obj : null;
    }

    public SyntaxTreeNode lookupName(com.sun.org.apache.xalan.internal.xsltc.compiler.QName qname) {
        if (_variables == null) return null;
        final String name = qname.getStringRep();
        return (SyntaxTreeNode)_variables.get(name);
    }

    public AttributeSet addAttributeSet(AttributeSet atts) {
        if (_attributeSets == null) _attributeSets = new HashMap<>();
        return _attributeSets.put(atts.getName().getStringRep(), atts);
    }

    public AttributeSet lookupAttributeSet(com.sun.org.apache.xalan.internal.xsltc.compiler.QName name) {
        if (_attributeSets == null) return null;
        return _attributeSets.get(name.getStringRep());
    }

    /**
     * Add a primitive operator or function to the symbol table. To avoid
     * name clashes with user-defined names, the prefix <tt>PrimopPrefix</tt>
     * is prepended.
     */
    public void addPrimop(String name, MethodType mtype) {
        Vector methods = _primops.get(name);
        if (methods == null) {
            _primops.put(name, methods = new Vector());
        }
        methods.addElement(mtype);
    }

    /**
     * Lookup a primitive operator or function in the symbol table by
     * prepending the prefix <tt>PrimopPrefix</tt>.
     */
    public Vector lookupPrimop(String name) {
        return _primops.get(name);
    }

    /**
     * This is used for xsl:attribute elements that have a "namespace"
     * attribute that is currently not defined using xmlns:
     */
    private int _nsCounter = 0;

    public String generateNamespacePrefix() {
        return("ns"+(_nsCounter++));
    }

    /**
     * Use a namespace prefix to lookup a namespace URI
     */
    private SyntaxTreeNode _current = null;

    public void setCurrentNode(SyntaxTreeNode node) {
        _current = node;
    }

    public String lookupNamespace(String prefix) {
        if (_current == null) return(com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.EMPTYSTRING);
        return(_current.lookupNamespace(prefix));
    }

    /**
     * Adds an alias for a namespace prefix
     */
    public void addPrefixAlias(String prefix, String alias) {
        if (_aliases == null) _aliases = new HashMap<>();
        _aliases.put(prefix,alias);
    }

    /**
     * Retrieves any alias for a given namespace prefix
     */
    public String lookupPrefixAlias(String prefix) {
        if (_aliases == null) return null;
        return _aliases.get(prefix);
    }

    /**
     * Register a namespace URI so that it will not be declared in the output
     * unless it is actually referenced in the output.
     */
    public void excludeURI(String uri) {
        // The null-namespace cannot be excluded
        if (uri == null) return;

        // Create a new map of exlcuded URIs if none exists
        if (_excludedURI == null) _excludedURI = new HashMap<>();

        // Register the namespace URI
        Integer refcnt = _excludedURI.get(uri);
        if (refcnt == null)
            refcnt = 1;
        else
            refcnt = refcnt + 1;
        _excludedURI.put(uri,refcnt);
    }

    /**
     * Exclude a series of namespaces given by a list of whitespace
     * separated namespace prefixes.
     */
    public void excludeNamespaces(String prefixes) {
        if (prefixes != null) {
            StringTokenizer tokens = new StringTokenizer(prefixes);
            while (tokens.hasMoreTokens()) {
                final String prefix = tokens.nextToken();
                final String uri;
                if (prefix.equals("#default"))
                    uri = lookupNamespace(com.sun.org.apache.xalan.internal.xsltc.compiler.Constants.EMPTYSTRING);
                else
                    uri = lookupNamespace(prefix);
                if (uri != null) excludeURI(uri);
            }
        }
    }

    /**
     * Check if a namespace should not be declared in the output (unless used)
     */
    public boolean isExcludedNamespace(String uri) {
        if (uri != null && _excludedURI != null) {
            final Integer refcnt = _excludedURI.get(uri);
            return (refcnt != null && refcnt > 0);
        }
        return false;
    }

    /**
     * Turn of namespace declaration exclusion
     */
    public void unExcludeNamespaces(String prefixes) {
        if (_excludedURI == null) return;
        if (prefixes != null) {
            StringTokenizer tokens = new StringTokenizer(prefixes);
            while (tokens.hasMoreTokens()) {
                final String prefix = tokens.nextToken();
                final String uri;
                if (prefix.equals("#default"))
                    uri = lookupNamespace(Constants.EMPTYSTRING);
                else
                    uri = lookupNamespace(prefix);
                Integer refcnt = _excludedURI.get(uri);
                if (refcnt != null)
                    _excludedURI.put(uri, refcnt - 1);
            }
        }
    }
    /**
     * Exclusion of namespaces by a stylesheet does not extend to any stylesheet
     * imported or included by the stylesheet.  Upon entering the context of a
     * new stylesheet, a call to this method is needed to clear the current set
     * of excluded namespaces temporarily.  Every call to this method requires
     * a corresponding call to {@link #popExcludedNamespacesContext()}.
     */
    public void pushExcludedNamespacesContext() {
        if (_excludedURIStack == null) {
            _excludedURIStack = new Stack();
        }
        _excludedURIStack.push(_excludedURI);
        _excludedURI = null;
    }

    /**
     * Exclusion of namespaces by a stylesheet does not extend to any stylesheet
     * imported or included by the stylesheet.  Upon exiting the context of a
     * stylesheet, a call to this method is needed to restore the set of
     * excluded namespaces that was in effect prior to entering the context of
     * the current stylesheet.
     */
    public void popExcludedNamespacesContext() {
        _excludedURI = _excludedURIStack.pop();
        if (_excludedURIStack.isEmpty()) {
            _excludedURIStack = null;
        }
    }

}
