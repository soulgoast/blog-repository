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


package goodman.com.sun.org.apache.xml.internal.serialize;

import com.sun.org.apache.xerces.internal.utils.ObjectFactory;
import com.sun.org.apache.xerces.internal.utils.SecuritySupport;
import com.sun.org.apache.xml.internal.serialize.Method;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.Serializer;
import com.sun.org.apache.xml.internal.serialize.SerializerFactoryImpl;

import goodman.java.io.OutputStream;
import goodman.java.io.UnsupportedEncodingException;
import goodman.java.io.Writer;
import goodman.java.util.Collections;
import goodman.java.util.HashMap;
import goodman.java.util.Map;
import goodman.java.util.StringTokenizer;

/**
 *
 *
 * @version $Revision: 1.6 $ $Date: 2010-11-01 04:40:36 $
 * @author <a href="mailto:Scott_Boag/CAM/Lotus@lotus.com">Scott Boag</a>
 * @author <a href="mailto:arkin@intalio.com">Assaf Arkin</a>
 */
public abstract class SerializerFactory
{


    public static final String FactoriesProperty = "com.sun.org.apache.xml.internal.serialize.factories";


    private static final Map<String, SerializerFactory>  _factories = Collections.synchronizedMap(new HashMap());


    static
    {
        SerializerFactory factory;
        String            list;
        StringTokenizer   token;
        String            className;

        // The default factories are always registered first,
        // any factory specified in the properties file and supporting
        // the same method will override the default factory.
        factory =  new SerializerFactoryImpl( com.sun.org.apache.xml.internal.serialize.Method.XML );
        registerSerializerFactory( factory );
        factory =  new SerializerFactoryImpl( com.sun.org.apache.xml.internal.serialize.Method.HTML );
        registerSerializerFactory( factory );
        factory =  new SerializerFactoryImpl( com.sun.org.apache.xml.internal.serialize.Method.XHTML );
        registerSerializerFactory( factory );
        factory =  new SerializerFactoryImpl( Method.TEXT );
        registerSerializerFactory( factory );

        list = SecuritySupport.getSystemProperty( FactoriesProperty );
        if ( list != null ) {
            token = new StringTokenizer( list, " ;,:" );
            while ( token.hasMoreTokens() ) {
                className = token.nextToken();
                try {
                    factory = (SerializerFactory) ObjectFactory.newInstance( className, true);
                    if ( _factories.containsKey( factory.getSupportedMethod() ) )
                        _factories.put( factory.getSupportedMethod(), factory );
                } catch ( Exception except ) { }
            }
        }
    }


    /**
     * Register a serializer factory, keyed by the given
     * method string.
     */
    public static void registerSerializerFactory( SerializerFactory factory )
    {
        String method;

        synchronized ( _factories ) {
        method = factory.getSupportedMethod();
        _factories.put( method, factory );
    }
    }


    /**
     * Register a serializer factory, keyed by the given
     * method string.
     */
    public static SerializerFactory getSerializerFactory( String method )
    {
        return _factories.get( method );
    }


    /**
     * Returns the method supported by this factory and used to register
     * the factory. This call is required so factories can be added from
     * a properties file by knowing only the class name. This method is
     * protected, it is only required by this class but must be implemented
     * in derived classes.
     */
    protected abstract String getSupportedMethod();


    /**
     * Create a new serializer based on the {@link com.sun.org.apache.xml.internal.serialize.OutputFormat}.
     * If this method is used to create the serializer, the {@link
     * com.sun.org.apache.xml.internal.serialize.Serializer#setOutputByteStream} or {@link com.sun.org.apache.xml.internal.serialize.Serializer#setOutputCharStream}
     * methods must be called before serializing a document.
     */
    public abstract com.sun.org.apache.xml.internal.serialize.Serializer makeSerializer(com.sun.org.apache.xml.internal.serialize.OutputFormat format);


    /**
     * Create a new serializer, based on the {@link com.sun.org.apache.xml.internal.serialize.OutputFormat} and
     * using the writer as the output character stream.  If this
     * method is used, the encoding property will be ignored.
     */
    public abstract com.sun.org.apache.xml.internal.serialize.Serializer makeSerializer(Writer writer,
                                                                                        com.sun.org.apache.xml.internal.serialize.OutputFormat format );


    /**
     * Create a new serializer, based on the {@link com.sun.org.apache.xml.internal.serialize.OutputFormat} and
     * using the output byte stream and the encoding specified in the
     * output format.
     *
     * @throws UnsupportedEncodingException The specified encoding is
     *   not supported
     */
    public abstract Serializer makeSerializer(OutputStream output,
                                              OutputFormat format )
        throws UnsupportedEncodingException;


}