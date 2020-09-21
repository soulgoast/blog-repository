/*
 * Copyright (c) 2003, Oracle and/or its affiliates. All rights reserved.
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

package goodman.com.sun.corba.se.impl.resolver;

import org.omg.CORBA.ORBPackage.InvalidName;

import com.sun.corba.se.spi.resolver.Resolver ;

import goodman.java.util.Enumeration;
import goodman.java.util.Properties;
import goodman.java.util.Set;
import goodman.java.util.HashSet;

import goodman.java.io.File;
import goodman.java.io.FileInputStream;

import com.sun.corba.se.spi.orb.ORB ;

import com.sun.corba.se.impl.orbutil.CorbaResourceUtil ;

public class FileResolverImpl implements Resolver
{
    private ORB orb ;
    private File file ;
    private Properties savedProps ;
    private long fileModified = 0 ;

    public FileResolverImpl( ORB orb, File file )
    {
        this.orb = orb ;
        this.file = file ;
        savedProps = new Properties() ;
    }

    public org.omg.CORBA.Object resolve( String name )
    {
        check() ;
        String stringifiedObject = savedProps.getProperty( name ) ;
        if (stringifiedObject == null) {
            return null;
        }
        return orb.string_to_object( stringifiedObject ) ;
    }

    public Set list()
    {
        check() ;

        Set result = new HashSet() ;

        // Obtain all the keys from the property object
        Enumeration theKeys = savedProps.propertyNames();
        while (theKeys.hasMoreElements()) {
            result.add( theKeys.nextElement() ) ;
        }

        return result ;
    }

    /**
    * Checks the lastModified() timestamp of the file and optionally
    * re-reads the Properties object from the file if newer.
    */
    private void check()
    {
        if (file == null)
            return;

        long lastMod = file.lastModified();
        if (lastMod > fileModified) {
            try {
                FileInputStream fileIS = new FileInputStream(file);
                savedProps.clear();
                savedProps.load(fileIS);
                fileIS.close();
                fileModified = lastMod;
            } catch (java.io.FileNotFoundException e) {
                System.err.println( CorbaResourceUtil.getText(
                    "bootstrap.filenotfound", file.getAbsolutePath()));
            } catch (java.io.IOException e) {
                System.err.println( CorbaResourceUtil.getText(
                    "bootstrap.exception",
                    file.getAbsolutePath(), e.toString()));
            }
        }
    }
}
