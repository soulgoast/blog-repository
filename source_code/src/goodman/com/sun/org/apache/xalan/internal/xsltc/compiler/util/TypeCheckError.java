/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright 2001-2004 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
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
 * $Id: TypeCheckError.java,v 1.2.4.1 2005/09/05 11:42:57 pvedula Exp $
 */

package goodman.com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;

/**
 * @author Jacek Ambroziak
 * @author Santiago Pericas-Geertsen
 */
public class TypeCheckError extends Exception {
    static final long serialVersionUID = 3246224233917854640L;
    com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg _error = null;
    SyntaxTreeNode _node = null;

    public TypeCheckError(SyntaxTreeNode node) {
        super();
        _node = node;
    }

    public TypeCheckError(com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg error) {
        super();
        _error = error;
    }

    public TypeCheckError(String code, Object param) {
        super();
        _error = new com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg(code, param);
    }

    public TypeCheckError(String code, Object param1, Object param2) {
        super();
        _error = new com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg(code, param1, param2);
    }

    public com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg getErrorMsg() {
        return _error;
    }

    public String getMessage() {
        return toString();
    }

    public String toString() {
        String result;

        if (_error == null) {
            if (_node != null) {
                _error = new com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg(com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg.TYPE_CHECK_ERR,
                                      _node.toString());
            } else {
                _error = new com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg(ErrorMsg.TYPE_CHECK_UNK_LOC_ERR);
            }
        }

        return _error.toString();
    }
}
