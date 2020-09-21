/*
 * Copyright (c) 2007, 2018, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
/*
 * Copyright 1999-2002,2004 The Apache Software Foundation.
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


package goodman.com.sun.org.apache.xml.internal.serialize;


import goodman.java.io.OutputStream;
import goodman.java.io.Writer;
import goodman.java.io.UnsupportedEncodingException;
import com.sun.org.apache.xerces.internal.dom.DOMMessageFormatter;
import com.sun.org.apache.xml.internal.serialize.HTMLSerializer;
import com.sun.org.apache.xml.internal.serialize.Method;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.Serializer;
import com.sun.org.apache.xml.internal.serialize.SerializerFactory;
import com.sun.org.apache.xml.internal.serialize.TextSerializer;
import com.sun.org.apache.xml.internal.serialize.XHTMLSerializer;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * Default serializer factory can construct serializers for the three
 * markup serializers (XML, HTML, XHTML ).
 *
 *
 * @author <a href="mailto:Scott_Boag/CAM/Lotus@lotus.com">Scott Boag</a>
 * @author <a href="mailto:arkin@intalio.com">Assaf Arkin</a>
 */
final class SerializerFactoryImpl
    extends SerializerFactory
{


    private String _method;


    SerializerFactoryImpl( String method )
    {
        _method = method;
        if ( ! _method.equals( com.sun.org.apache.xml.internal.serialize.Method.XML ) &&
             ! _method.equals( com.sun.org.apache.xml.internal.serialize.Method.HTML ) &&
             ! _method.equals( com.sun.org.apache.xml.internal.serialize.Method.XHTML ) &&
             ! _method.equals( com.sun.org.apache.xml.internal.serialize.Method.TEXT ) ) {
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "MethodNotSupported", new Object[]{method});
            throw new IllegalArgumentException(msg);
        }
    }


    public com.sun.org.apache.xml.internal.serialize.Serializer makeSerializer(com.sun.org.apache.xml.internal.serialize.OutputFormat format )
    {
        com.sun.org.apache.xml.internal.serialize.Serializer serializer;

        serializer = getSerializer( format );
        serializer.setOutputFormat( format );
        return serializer;
    }



    public com.sun.org.apache.xml.internal.serialize.Serializer makeSerializer(Writer writer,
                                                                               com.sun.org.apache.xml.internal.serialize.OutputFormat format )
    {
        com.sun.org.apache.xml.internal.serialize.Serializer serializer;

        serializer = getSerializer( format );
        serializer.setOutputCharStream( writer );
        return serializer;
    }


    public com.sun.org.apache.xml.internal.serialize.Serializer makeSerializer(OutputStream output,
                                                                               com.sun.org.apache.xml.internal.serialize.OutputFormat format )
        throws UnsupportedEncodingException
    {
        com.sun.org.apache.xml.internal.serialize.Serializer serializer;

        serializer = getSerializer( format );
        serializer.setOutputByteStream( output );
        return serializer;
    }


    private Serializer getSerializer(OutputFormat format )
    {
        if ( _method.equals( com.sun.org.apache.xml.internal.serialize.Method.XML ) ) {
            return new XMLSerializer( format );
        } else if ( _method.equals( com.sun.org.apache.xml.internal.serialize.Method.HTML ) ) {
            return new HTMLSerializer( format );
        }  else if ( _method.equals( com.sun.org.apache.xml.internal.serialize.Method.XHTML ) ) {
            return new XHTMLSerializer( format );
        }  else if ( _method.equals( Method.TEXT ) ) {
            return new TextSerializer();
        } else {
            String msg = DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "MethodNotSupported", new Object[]{_method});
            throw new IllegalStateException(msg);
        }
    }


    protected String getSupportedMethod()
    {
        return _method;
    }


}
