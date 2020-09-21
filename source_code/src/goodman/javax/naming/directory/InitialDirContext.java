/*
 * Copyright (c) 1999, 2009, Oracle and/or its affiliates. All rights reserved.
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


package goodman.javax.naming.directory;

import goodman.java.util.Hashtable;
import goodman.javax.naming.directory.Attributes;
import goodman.javax.naming.directory.DirContext;
import goodman.javax.naming.directory.ModificationItem;
import goodman.javax.naming.directory.SearchControls;
import goodman.javax.naming.directory.SearchResult;
import goodman.javax.naming.spi.NamingManager;
import goodman.javax.naming.*;

/**
 * This class is the starting context for performing
 * directory operations. The documentation in the class description
 * of InitialContext (including those for synchronization) apply here.
 *
 *
 * @author Rosanna Lee
 * @author Scott Seligman
 *
 * @see InitialContext
 * @since 1.3
 */

public class InitialDirContext extends InitialContext implements javax.naming.directory.DirContext {

    /**
     * Constructs an initial DirContext with the option of not
     * initializing it.  This may be used by a constructor in
     * a subclass when the value of the environment parameter
     * is not yet known at the time the <tt>InitialDirContext</tt>
     * constructor is called.  The subclass's constructor will
     * call this constructor, compute the value of the environment,
     * and then call <tt>init()</tt> before returning.
     *
     * @param lazy
     *          true means do not initialize the initial DirContext; false
     *          is equivalent to calling <tt>new InitialDirContext()</tt>
     * @throws  NamingException if a naming exception is encountered
     *
     * @see InitialContext#init(Hashtable)
     * @since 1.3
     */
    protected InitialDirContext(boolean lazy) throws NamingException {
        super(lazy);
    }

    /**
     * Constructs an initial DirContext.
     * No environment properties are supplied.
     * Equivalent to <tt>new InitialDirContext(null)</tt>.
     *
     * @throws  NamingException if a naming exception is encountered
     *
     * @see #InitialDirContext(Hashtable)
     */
    public InitialDirContext() throws NamingException {
        super();
    }

    /**
     * Constructs an initial DirContext using the supplied environment.
     * Environment properties are discussed in the
     * <tt>javax.naming.InitialContext</tt> class description.
     *
     * <p> This constructor will not modify <tt>environment</tt>
     * or save a reference to it, but may save a clone.
     * Caller should not modify mutable keys and values in
     * <tt>environment</tt> after it has been passed to the constructor.
     *
     * @param environment
     *          environment used to create the initial DirContext.
     *          Null indicates an empty environment.
     *
     * @throws  NamingException if a naming exception is encountered
     */
    public InitialDirContext(Hashtable<?,?> environment)
        throws NamingException
    {
        super(environment);
    }

    private javax.naming.directory.DirContext getURLOrDefaultInitDirCtx(String name)
            throws NamingException {
        Context answer = getURLOrDefaultInitCtx(name);
        if (!(answer instanceof javax.naming.directory.DirContext)) {
            if (answer == null) {
                throw new NoInitialContextException();
            } else {
                throw new NotContextException(
                    "Not an instance of DirContext");
            }
        }
        return (javax.naming.directory.DirContext)answer;
    }

    private javax.naming.directory.DirContext getURLOrDefaultInitDirCtx(Name name)
            throws NamingException {
        Context answer = getURLOrDefaultInitCtx(name);
        if (!(answer instanceof javax.naming.directory.DirContext)) {
            if (answer == null) {
                throw new NoInitialContextException();
            } else {
                throw new NotContextException(
                    "Not an instance of DirContext");
            }
        }
        return (javax.naming.directory.DirContext)answer;
    }

// DirContext methods
// Most Javadoc is deferred to the DirContext interface.

    public javax.naming.directory.Attributes getAttributes(String name)
            throws NamingException {
        return getAttributes(name, null);
    }

    public javax.naming.directory.Attributes getAttributes(String name, String[] attrIds)
            throws NamingException {
        return getURLOrDefaultInitDirCtx(name).getAttributes(name, attrIds);
    }

    public javax.naming.directory.Attributes getAttributes(Name name)
            throws NamingException {
        return getAttributes(name, null);
    }

    public javax.naming.directory.Attributes getAttributes(Name name, String[] attrIds)
            throws NamingException {
        return getURLOrDefaultInitDirCtx(name).getAttributes(name, attrIds);
    }

    public void modifyAttributes(String name, int mod_op, javax.naming.directory.Attributes attrs)
            throws NamingException {
        getURLOrDefaultInitDirCtx(name).modifyAttributes(name, mod_op, attrs);
    }

    public void modifyAttributes(Name name, int mod_op, javax.naming.directory.Attributes attrs)
            throws NamingException  {
        getURLOrDefaultInitDirCtx(name).modifyAttributes(name, mod_op, attrs);
    }

    public void modifyAttributes(String name, javax.naming.directory.ModificationItem[] mods)
            throws NamingException  {
        getURLOrDefaultInitDirCtx(name).modifyAttributes(name, mods);
    }

    public void modifyAttributes(Name name, ModificationItem[] mods)
            throws NamingException  {
        getURLOrDefaultInitDirCtx(name).modifyAttributes(name, mods);
    }

    public void bind(String name, Object obj, javax.naming.directory.Attributes attrs)
            throws NamingException  {
        getURLOrDefaultInitDirCtx(name).bind(name, obj, attrs);
    }

    public void bind(Name name, Object obj, javax.naming.directory.Attributes attrs)
            throws NamingException  {
        getURLOrDefaultInitDirCtx(name).bind(name, obj, attrs);
    }

    public void rebind(String name, Object obj, javax.naming.directory.Attributes attrs)
            throws NamingException  {
        getURLOrDefaultInitDirCtx(name).rebind(name, obj, attrs);
    }

    public void rebind(Name name, Object obj, javax.naming.directory.Attributes attrs)
            throws NamingException  {
        getURLOrDefaultInitDirCtx(name).rebind(name, obj, attrs);
    }

    public javax.naming.directory.DirContext createSubcontext(String name, javax.naming.directory.Attributes attrs)
            throws NamingException  {
        return getURLOrDefaultInitDirCtx(name).createSubcontext(name, attrs);
    }

    public javax.naming.directory.DirContext createSubcontext(Name name, javax.naming.directory.Attributes attrs)
            throws NamingException  {
        return getURLOrDefaultInitDirCtx(name).createSubcontext(name, attrs);
    }

    public javax.naming.directory.DirContext getSchema(String name) throws NamingException {
        return getURLOrDefaultInitDirCtx(name).getSchema(name);
    }

    public javax.naming.directory.DirContext getSchema(Name name) throws NamingException {
        return getURLOrDefaultInitDirCtx(name).getSchema(name);
    }

    public javax.naming.directory.DirContext getSchemaClassDefinition(String name)
            throws NamingException {
        return getURLOrDefaultInitDirCtx(name).getSchemaClassDefinition(name);
    }

    public DirContext getSchemaClassDefinition(Name name)
            throws NamingException {
        return getURLOrDefaultInitDirCtx(name).getSchemaClassDefinition(name);
    }

// -------------------- search operations

    public NamingEnumeration<javax.naming.directory.SearchResult>
        search(String name, javax.naming.directory.Attributes matchingAttributes)
        throws NamingException
    {
        return getURLOrDefaultInitDirCtx(name).search(name, matchingAttributes);
    }

    public NamingEnumeration<javax.naming.directory.SearchResult>
        search(Name name, javax.naming.directory.Attributes matchingAttributes)
        throws NamingException
    {
        return getURLOrDefaultInitDirCtx(name).search(name, matchingAttributes);
    }

    public NamingEnumeration<javax.naming.directory.SearchResult>
        search(String name,
               javax.naming.directory.Attributes matchingAttributes,
               String[] attributesToReturn)
        throws NamingException
    {
        return getURLOrDefaultInitDirCtx(name).search(name,
                                                      matchingAttributes,
                                                      attributesToReturn);
    }

    public NamingEnumeration<javax.naming.directory.SearchResult>
        search(Name name,
               Attributes matchingAttributes,
               String[] attributesToReturn)
        throws NamingException
    {
        return getURLOrDefaultInitDirCtx(name).search(name,
                                            matchingAttributes,
                                            attributesToReturn);
    }

    public NamingEnumeration<javax.naming.directory.SearchResult>
        search(String name,
               String filter,
               javax.naming.directory.SearchControls cons)
        throws NamingException
    {
        return getURLOrDefaultInitDirCtx(name).search(name, filter, cons);
    }

    public NamingEnumeration<javax.naming.directory.SearchResult>
        search(Name name,
               String filter,
               javax.naming.directory.SearchControls cons)
        throws NamingException
    {
        return getURLOrDefaultInitDirCtx(name).search(name, filter, cons);
    }

    public NamingEnumeration<javax.naming.directory.SearchResult>
        search(String name,
               String filterExpr,
               Object[] filterArgs,
               javax.naming.directory.SearchControls cons)
        throws NamingException
    {
        return getURLOrDefaultInitDirCtx(name).search(name, filterExpr,
                                                      filterArgs, cons);
    }

    public NamingEnumeration<SearchResult>
        search(Name name,
               String filterExpr,
               Object[] filterArgs,
               SearchControls cons)
        throws NamingException
    {
        return getURLOrDefaultInitDirCtx(name).search(name, filterExpr,
                                                      filterArgs, cons);
    }
}
