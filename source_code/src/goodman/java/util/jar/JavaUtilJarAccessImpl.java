/*
 * Copyright (c) 2002, 2018, Oracle and/or its affiliates. All rights reserved.
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

package goodman.java.util.jar;

import goodman.java.io.IOException;
import goodman.java.net.URL;
import goodman.java.security.CodeSource;
import goodman.java.util.Enumeration;
import goodman.java.util.List;
import goodman.java.util.jar.Attributes;
import goodman.java.util.jar.JarEntry;
import goodman.java.util.jar.JarFile;
import goodman.java.util.jar.Manifest;

import sun.misc.JavaUtilJarAccess;

class JavaUtilJarAccessImpl implements JavaUtilJarAccess {
    public boolean jarFileHasClassPathAttribute(java.util.jar.JarFile jar) throws IOException {
        return jar.hasClassPathAttribute();
    }

    public CodeSource[] getCodeSources(java.util.jar.JarFile jar, URL url) {
        return jar.getCodeSources(url);
    }

    public CodeSource getCodeSource(java.util.jar.JarFile jar, URL url, String name) {
        return jar.getCodeSource(url, name);
    }

    public Enumeration<String> entryNames(java.util.jar.JarFile jar, CodeSource[] cs) {
        return jar.entryNames(cs);
    }

    public Enumeration<JarEntry> entries2(java.util.jar.JarFile jar) {
        return jar.entries2();
    }

    public void setEagerValidation(java.util.jar.JarFile jar, boolean eager) {
        jar.setEagerValidation(eager);
    }

    public List<Object> getManifestDigests(java.util.jar.JarFile jar) {
        return jar.getManifestDigests();
    }

    public Attributes getTrustedAttributes(Manifest man, String name) {
        return man.getTrustedAttributes(name);
    }

    public void ensureInitialization(JarFile jar) {
        jar.ensureInitialization();
    }
}
