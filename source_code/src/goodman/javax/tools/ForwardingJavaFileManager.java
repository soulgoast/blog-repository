/*
 * Copyright (c) 2005, 2012, Oracle and/or its affiliates. All rights reserved.
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

package goodman.javax.tools;

import goodman.java.io.IOException;
import goodman.java.util.Iterator;
import goodman.java.util.Set;
import goodman.javax.tools.FileObject;
import goodman.javax.tools.JavaFileManager;
import goodman.javax.tools.JavaFileObject;
import goodman.javax.tools.JavaFileObject.Kind;

/**
 * Forwards calls to a given file manager.  Subclasses of this class
 * might override some of these methods and might also provide
 * additional fields and methods.
 *
 * @param <M> the kind of file manager forwarded to by this object
 * @author Peter von der Ah&eacute;
 * @since 1.6
 */
public class ForwardingJavaFileManager<M extends javax.tools.JavaFileManager> implements JavaFileManager {

    /**
     * The file manager which all methods are delegated to.
     */
    protected final M fileManager;

    /**
     * Creates a new instance of ForwardingJavaFileManager.
     * @param fileManager delegate to this file manager
     */
    protected ForwardingJavaFileManager(M fileManager) {
        fileManager.getClass(); // null check
        this.fileManager = fileManager;
    }

    /**
     * @throws SecurityException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    public ClassLoader getClassLoader(Location location) {
        return fileManager.getClassLoader(location);
    }

    /**
     * @throws IOException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    public Iterable<javax.tools.JavaFileObject> list(Location location,
                                                     String packageName,
                                                     Set<Kind> kinds,
                                                     boolean recurse)
        throws IOException
    {
        return fileManager.list(location, packageName, kinds, recurse);
    }

    /**
     * @throws IllegalStateException {@inheritDoc}
     */
    public String inferBinaryName(Location location, javax.tools.JavaFileObject file) {
        return fileManager.inferBinaryName(location, file);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     */
    public boolean isSameFile(javax.tools.FileObject a, javax.tools.FileObject b) {
        return fileManager.isSameFile(a, b);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    public boolean handleOption(String current, Iterator<String> remaining) {
        return fileManager.handleOption(current, remaining);
    }

    public boolean hasLocation(Location location) {
        return fileManager.hasLocation(location);
    }

    public int isSupportedOption(String option) {
        return fileManager.isSupportedOption(option);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    public javax.tools.JavaFileObject getJavaFileForInput(Location location,
                                                          String className,
                                                          Kind kind)
        throws IOException
    {
        return fileManager.getJavaFileForInput(location, className, kind);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    public JavaFileObject getJavaFileForOutput(Location location,
                                               String className,
                                               Kind kind,
                                               javax.tools.FileObject sibling)
        throws IOException
    {
        return fileManager.getJavaFileForOutput(location, className, kind, sibling);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    public javax.tools.FileObject getFileForInput(Location location,
                                                  String packageName,
                                                  String relativeName)
        throws IOException
    {
        return fileManager.getFileForInput(location, packageName, relativeName);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    public javax.tools.FileObject getFileForOutput(Location location,
                                                   String packageName,
                                                   String relativeName,
                                                   FileObject sibling)
        throws IOException
    {
        return fileManager.getFileForOutput(location, packageName, relativeName, sibling);
    }

    public void flush() throws IOException {
        fileManager.flush();
    }

    public void close() throws IOException {
        fileManager.close();
    }
}
