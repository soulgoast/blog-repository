/*
 * Copyright (c) 2002, Oracle and/or its affiliates. All rights reserved.
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
package goodman.com.sun.corba.se.spi.orb;

import com.sun.corba.se.impl.orb.NormalParserData ;
import com.sun.corba.se.impl.orb.PrefixParserData ;
import com.sun.corba.se.spi.orb.Operation;
import com.sun.corba.se.spi.orb.ParserData;
import com.sun.corba.se.spi.orb.StringPair;

public class ParserDataFactory {
    public static com.sun.corba.se.spi.orb.ParserData make(String  propertyName,
                                                           com.sun.corba.se.spi.orb.Operation operation, String fieldName, Object defaultValue,
                                                           Object testValue, String testData )
    {
        return new NormalParserData( propertyName, operation, fieldName,
            defaultValue, testValue, testData ) ;
    }

    public static ParserData make(String  propertyName,
                                  Operation operation, String fieldName, Object defaultValue,
                                  Object testValue, StringPair[] testData, Class componentType )
    {
        return new PrefixParserData( propertyName, operation, fieldName,
            defaultValue, testValue, testData, componentType ) ;
    }
}
